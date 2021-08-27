package com.forgerock.connector.beam;
import com.google.auto.value.AutoValue;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.beam.sdk.annotations.Experimental;
import org.apache.beam.sdk.coders.Coder;
import org.apache.beam.sdk.options.ValueProvider;
import org.apache.beam.sdk.schemas.NoSuchSchemaException;
import org.apache.beam.sdk.schemas.Schema;
import org.apache.beam.sdk.schemas.SchemaRegistry;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.transforms.display.DisplayData;
import org.apache.beam.sdk.transforms.display.HasDisplayData;
import org.apache.beam.sdk.values.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.sql.DataSource;

import static com.google.common.base.Preconditions.checkArgument;

public class JdbcIO {
    private JdbcIO() {
    }

    @FunctionalInterface
    public interface RowMapper<T> extends Serializable {
        T mapRow(ResultSet resultSet) throws Exception;
    }

    @AutoValue
    public abstract static class DataSourceConfiguration implements Serializable {

        abstract @Nullable
        ValueProvider<String> getDriverClassName();

        abstract @Nullable
        ValueProvider<String> getUrl();

        abstract @Nullable
        ValueProvider<String> getUsername();

        abstract @Nullable
        ValueProvider<String> getPassword();

        abstract @Nullable
        ValueProvider<String> getConnectionProperties();

        abstract @Nullable
        ValueProvider<Collection<String>> getConnectionInitSqls();

        abstract @Nullable
        DataSource getDataSource();

        abstract Builder builder();

        @AutoValue.Builder
        abstract static class Builder {
            abstract Builder setDriverClassName(ValueProvider<String> driverClassName);

            abstract Builder setUrl(ValueProvider<String> url);

            abstract Builder setUsername(ValueProvider<String> username);

            abstract Builder setPassword(ValueProvider<String> password);

            abstract Builder setConnectionProperties(ValueProvider<String> connectionProperties);

            abstract Builder setConnectionInitSqls(ValueProvider<Collection<String>> connectionInitSqls);

            abstract Builder setDataSource(DataSource dataSource);

            abstract DataSourceConfiguration build();
        }

        public static DataSourceConfiguration create(DataSource dataSource) {
            return new com.forgerock.connector.beam.AutoValue_JdbcIO_DataSourceConfiguration.Builder()
                    .setDataSource(dataSource)
                    .build();
        }

        public static DataSourceConfiguration create(ValueProvider<String> driverClassName, ValueProvider<String> url) {
            return new com.forgerock.connector.beam.AutoValue_JdbcIO_DataSourceConfiguration.Builder()
                    .setDriverClassName(driverClassName)
                    .setUrl(url)
                    .build();
        }

        public DataSourceConfiguration withUsername(String username) {
            return withUsername(ValueProvider.StaticValueProvider.of(username));
        }

        public DataSourceConfiguration withUsername(ValueProvider<String> username) {
            return builder().setUsername(username).build();
        }

        public DataSourceConfiguration withPassword(String password) {
            return withPassword(ValueProvider.StaticValueProvider.of(password));
        }

        public DataSourceConfiguration withPassword(ValueProvider<String> password) {
            return builder().setPassword(password).build();
        }

        public DataSourceConfiguration withConnectionProperties(String connectionProperties) {
            return withUsername(ValueProvider.StaticValueProvider.of(connectionProperties));
        }

        public DataSourceConfiguration withConnectionProperties(
                ValueProvider<String> connectionProperties) {
            checkArgument(connectionProperties != null, "connectionProperties can not be null");
            return builder().setConnectionProperties(connectionProperties).build();
        }

        public DataSourceConfiguration withConnectionInitSqls(
                ValueProvider<Collection<String>> connectionInitSqls) {
            checkArgument(connectionInitSqls != null, "connectionInitSqls can not be null");
            checkArgument(!connectionInitSqls.get().isEmpty(), "connectionInitSqls can not be empty");
            return builder().setConnectionInitSqls(connectionInitSqls).build();
        }

        void populateDisplayData(DisplayData.Builder builder) {
            if (getDataSource() != null) {
                builder.addIfNotNull(DisplayData.item("dataSource", getDataSource().getClass().getName()));
            } else {
                builder.addIfNotNull(DisplayData.item("jdbcDriverClassName", getDriverClassName()));
                builder.addIfNotNull(DisplayData.item("jdbcUrl", getUrl()));
                builder.addIfNotNull(DisplayData.item("username", getUsername()));
            }
        }

        DataSource buildDatasource() {
            if (getDataSource() == null) {
                BasicDataSource basicDataSource = new BasicDataSource();
                if (getDriverClassName() != null) {
                    basicDataSource.setDriverClassName(getDriverClassName().get());
                }
                if (getUrl() != null) {
                    basicDataSource.setUrl(getUrl().get());
                }
                if (getUsername() != null) {
                    basicDataSource.setUsername(getUsername().get());
                }
                if (getPassword() != null) {
                    basicDataSource.setPassword(getPassword().get());
                }
                if (getConnectionProperties() != null && getConnectionProperties().get() != null) {
                    basicDataSource.setConnectionProperties(getConnectionProperties().get());
                }
                if (getConnectionInitSqls() != null
                        && getConnectionInitSqls().get() != null
                        && !getConnectionInitSqls().get().isEmpty()) {
                    basicDataSource.setConnectionInitSqls(getConnectionInitSqls().get());
                }

                return basicDataSource;
            }
            return getDataSource();
        }
    }

    @FunctionalInterface
    public interface StatementPreparator extends Serializable {
        void setParameters(PreparedStatement preparedStatement) throws Exception;
    }
    @FunctionalInterface
    public interface PreparedStatementSetter<T> extends Serializable {
        void setParameters(T element, PreparedStatement preparedStatement) throws Exception;
    }
/*
    @AutoValue
    @Experimental(Experimental.Kind.SCHEMAS)
    public abstract static class ReadRows extends PTransform<PBegin, PCollection<Row>> {

        abstract @Nullable SerializableFunction<Void, DataSource> getDataSourceProviderFn();

        abstract @Nullable ValueProvider<String> getQuery();

        abstract @Nullable StatementPreparator getStatementPreparator();

        abstract int getFetchSize();

        abstract boolean getOutputParallelization();

        abstract Builder toBuilder();

        @AutoValue.Builder
        abstract static class Builder {
            abstract Builder setDataSourceProviderFn(
                    SerializableFunction<Void, DataSource> dataSourceProviderFn);

            abstract Builder setQuery(ValueProvider<String> query);

            abstract Builder setStatementPreparator(StatementPreparator statementPreparator);

            abstract Builder setFetchSize(int fetchSize);

            abstract Builder setOutputParallelization(boolean outputParallelization);

            abstract ReadRows build();
        }
    }
    */
@AutoValue
    public abstract static class Read<T> extends PTransform<PBegin, PCollection<T>> {
        abstract @Nullable SerializableFunction<Void, DataSource> getDataSourceProviderFn();
        abstract @Nullable ValueProvider<String> getQuery();
        abstract @Nullable StatementPreparator getStatementPreparator();
        abstract @Nullable RowMapper<T> getRowMapper();
        abstract @Nullable Coder<T> getCoder();
        abstract int getFetchSize();
        abstract boolean getOutputParallelization();
        abstract Builder<T> toBuilder();

        @AutoValue.Builder
        abstract static class Builder<T> {
            abstract Builder<T> setDataSourceProviderFn(SerializableFunction<Void, DataSource> dataSourceProviderFn);
            abstract Builder<T> setQuery(ValueProvider<String> query);
            abstract Builder<T> setStatementPreparator(StatementPreparator statementPreparator);
            abstract Builder<T> setRowMapper(RowMapper<T> rowMapper);
            abstract Builder<T> setCoder(Coder<T> coder);
            abstract Builder<T> setFetchSize(int fetchSize);
            abstract Builder<T> setOutputParallelization(boolean outputParallelization);
            abstract Read<T> build();
        }

        @Override
        public PCollection<T> expand(PBegin input) {
            checkArgument(getQuery() != null, "withQuery() is required");
            checkArgument(getRowMapper() != null, "withRowMapper() is required");
            checkArgument(getCoder() != null, "withCoder() is required");
            checkArgument((getDataSourceProviderFn() != null), "withDataSourceConfiguration() or withDataSourceProviderFn() is required");

            return null;
              /*
                    input.apply(Create.of((Void) null)).apply(JdbcIO.<Void, T>readAll()
                .withDataSourceProviderFn(getDataSourceProviderFn())
                .withQuery(getQuery())
                .withCoder(getCoder())
                .withRowMapper(getRowMapper())
                .withFetchSize(getFetchSize())
                .withOutputParallelization(getOutputParallelization())
                .withParameterSetter((element, preparedStatement) -> {
                                            if (getStatementPreparator() != null) {
                                                getStatementPreparator().setParameters(preparedStatement);
                                            }
                                        }));*/
        }

        @Override
        public void populateDisplayData(DisplayData.Builder builder) {
            super.populateDisplayData(builder);
            builder.add(DisplayData.item("query", getQuery()));
            builder.add(DisplayData.item("rowMapper", getRowMapper().getClass().getName()));
            builder.add(DisplayData.item("coder", getCoder().getClass().getName()));
            if (getDataSourceProviderFn() instanceof HasDisplayData) {
                ((HasDisplayData) getDataSourceProviderFn()).populateDisplayData(builder);
            }
        }
    }
    @AutoValue
    public abstract static class ReadAll<ParameterT, OutputT>
            extends PTransform<PCollection<ParameterT>, PCollection<OutputT>> {

        abstract @Nullable SerializableFunction<Void, DataSource> getDataSourceProviderFn();
        abstract @Nullable ValueProvider<String> getQuery();
        abstract @Nullable PreparedStatementSetter<ParameterT> getParameterSetter();
        abstract @Nullable RowMapper<OutputT> getRowMapper();
        abstract @Nullable Coder<OutputT> getCoder();
        abstract int getFetchSize();
        abstract boolean getOutputParallelization();
        abstract Builder<ParameterT, OutputT> toBuilder();

        @AutoValue.Builder
        abstract static class Builder<ParameterT, OutputT> {
            abstract Builder<ParameterT, OutputT> setDataSourceProviderFn(SerializableFunction<Void, DataSource> dataSourceProviderFn);
            abstract Builder<ParameterT, OutputT> setQuery(ValueProvider<String> query);
            abstract Builder<ParameterT, OutputT> setParameterSetter(PreparedStatementSetter<ParameterT> parameterSetter);
            abstract Builder<ParameterT, OutputT> setRowMapper(RowMapper<OutputT> rowMapper);
            abstract Builder<ParameterT, OutputT> setCoder(Coder<OutputT> coder);
            abstract Builder<ParameterT, OutputT> setFetchSize(int fetchSize);
            abstract Builder<ParameterT, OutputT> setOutputParallelization(boolean outputParallelization);
            abstract ReadAll<ParameterT, OutputT> build();
        }

        public ReadAll<ParameterT, OutputT> withDataSourceConfiguration(
                DataSourceConfiguration config) {
            return withDataSourceProviderFn(new DataSourceProviderFromDataSourceConfiguration(config));
        }

        public ReadAll<ParameterT, OutputT> withDataSourceProviderFn(
                SerializableFunction<Void, DataSource> dataSourceProviderFn) {
            return toBuilder().setDataSourceProviderFn(dataSourceProviderFn).build();
        }

        public ReadAll<ParameterT, OutputT> withQuery(String query) {
            checkArgument(query != null, "JdbcIO.readAll().withQuery(query) called with null query");
            return withQuery(ValueProvider.StaticValueProvider.of(query));
        }

        public ReadAll<ParameterT, OutputT> withQuery(ValueProvider<String> query) {
            checkArgument(query != null, "JdbcIO.readAll().withQuery(query) called with null query");
            return toBuilder().setQuery(query).build();
        }

        public ReadAll<ParameterT, OutputT> withParameterSetter(
                PreparedStatementSetter<ParameterT> parameterSetter) {
            checkArgument(
                    parameterSetter != null,
                    "JdbcIO.readAll().withParameterSetter(parameterSetter) called "
                            + "with null statementPreparator");
            return toBuilder().setParameterSetter(parameterSetter).build();
        }

        public ReadAll<ParameterT, OutputT> withRowMapper(RowMapper<OutputT> rowMapper) {
            checkArgument(
                    rowMapper != null,
                    "JdbcIO.readAll().withRowMapper(rowMapper) called with null rowMapper");
            return toBuilder().setRowMapper(rowMapper).build();
        }

        public ReadAll<ParameterT, OutputT> withCoder(Coder<OutputT> coder) {
            checkArgument(coder != null, "JdbcIO.readAll().withCoder(coder) called with null coder");
            return toBuilder().setCoder(coder).build();
        }

        /**
         * This method is used to set the size of the data that is going to be fetched and loaded in
         * memory per every database call. Please refer to: {@link java.sql.Statement#setFetchSize(int)}
         * It should ONLY be used if the default value throws memory errors.
         */
        public ReadAll<ParameterT, OutputT> withFetchSize(int fetchSize) {
            checkArgument(fetchSize > 0, "fetch size must be >0");
            return toBuilder().setFetchSize(fetchSize).build();
        }

        /**
         * Whether to reshuffle the resulting PCollection so results are distributed to all workers. The
         * default is to parallelize and should only be changed if this is known to be unnecessary.
         */
        public ReadAll<ParameterT, OutputT> withOutputParallelization(boolean outputParallelization) {
            return toBuilder().setOutputParallelization(outputParallelization).build();
        }

        @Override
        public PCollection<OutputT> expand(PCollection<ParameterT> input) {
            PCollection<OutputT> output = input.apply(ParDo.of(
                                            new ReadFn<>(
                                                    getDataSourceProviderFn(),
                                                    getQuery(),
                                                    getParameterSetter(),
                                                    getRowMapper(),
                                                    getFetchSize()))).setCoder(getCoder());

            if (getOutputParallelization()) {
                output = output.apply(new Reparallelize<>());
            }

            try {
                TypeDescriptor<OutputT> typeDesc = getCoder().getEncodedTypeDescriptor();
                SchemaRegistry registry = input.getPipeline().getSchemaRegistry();
                Schema schema = registry.getSchema(typeDesc);
                output.setSchema(
                        schema,
                        typeDesc,
                        registry.getToRowFunction(typeDesc),
                        registry.getFromRowFunction(typeDesc));
            } catch (NoSuchSchemaException e) {
                // ignore
            }
            return output;
        }

        @Override
        public void populateDisplayData(DisplayData.Builder builder) {
            super.populateDisplayData(builder);
            builder.add(DisplayData.item("query", getQuery()));
            builder.add(DisplayData.item("rowMapper", getRowMapper().getClass().getName()));
            builder.add(DisplayData.item("coder", getCoder().getClass().getName()));
            if (getDataSourceProviderFn() instanceof HasDisplayData) {
                ((HasDisplayData) getDataSourceProviderFn()).populateDisplayData(builder);
            }
        }

    }
    private static class ReadFn<ParameterT, OutputT> extends DoFn<ParameterT, OutputT> {
        private final SerializableFunction<Void, DataSource> dataSourceProviderFn;
        private final ValueProvider<String> query;
        private final PreparedStatementSetter<ParameterT> parameterSetter;
        private final RowMapper<OutputT> rowMapper;
        private final int fetchSize;

        private DataSource dataSource;
        private Connection connection;

        private ReadFn(
                SerializableFunction<Void, DataSource> dataSourceProviderFn,
                ValueProvider<String> query,
                PreparedStatementSetter<ParameterT> parameterSetter,
                RowMapper<OutputT> rowMapper,
                int fetchSize) {
            this.dataSourceProviderFn = dataSourceProviderFn;
            this.query = query;
            this.parameterSetter = parameterSetter;
            this.rowMapper = rowMapper;
            this.fetchSize = fetchSize;
        }

        @Setup
        public void setup() throws Exception {
            dataSource = dataSourceProviderFn.apply(null);
        }

        @ProcessElement
        public void processElement(ProcessContext context) throws Exception {
            // Only acquire the connection if we need to perform a read.
            if (connection == null) {
                connection = dataSource.getConnection();
            }
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(query.get(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                statement.setFetchSize(fetchSize);
                parameterSetter.setParameters(context.element(), statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        context.output(rowMapper.mapRow(resultSet));
                    }
                }
            }
        }

        @FinishBundle
        public void finishBundle() throws Exception {
            cleanUpConnection();
        }

        @Override
        protected void finalize() throws Throwable {
            cleanUpConnection();
        }

        private void cleanUpConnection() throws Exception {
            if (connection != null) {
                try {
                    connection.close();
                } finally {
                    connection = null;
                }
            }
        }
    }
    private static class Reparallelize<T> extends PTransform<PCollection<T>, PCollection<T>> {
        @Override
        public PCollection<T> expand(PCollection<T> input) {
            PCollectionView<Iterable<T>> empty = input.apply("Consume", Filter.by(SerializableFunctions.constant(false))).apply(View.asIterable());
            PCollection<T> materialized =
                    input.apply(
                            "Identity",
                            ParDo.of(new DoFn<T, T>() {
                                                @ProcessElement
                                                public void process(ProcessContext c) {
                                                    c.output(c.element());
                                                }
                                            }).withSideInputs(empty));
            return materialized.apply(Reshuffle.viaRandomKey());
        }
    }
    public static class DataSourceProviderFromDataSourceConfiguration
            implements SerializableFunction<Void, DataSource>, HasDisplayData {
        private static final ConcurrentHashMap<DataSourceConfiguration, DataSource> instances =
                new ConcurrentHashMap<>();
        private final DataSourceConfiguration config;

        private DataSourceProviderFromDataSourceConfiguration(DataSourceConfiguration config) {
            this.config = config;
        }

        public static SerializableFunction<Void, DataSource> of(DataSourceConfiguration config) {
            return new DataSourceProviderFromDataSourceConfiguration(config);
        }

        @Override
        public DataSource apply(Void input) {
            return instances.computeIfAbsent(config, DataSourceConfiguration::buildDatasource);
        }

        @Override
        public void populateDisplayData(DisplayData.Builder builder) {
            config.populateDisplayData(builder);
        }
    }
}