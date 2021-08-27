package com.forgerock.connector.beam;

import com.google.auto.value.AutoValue;
import org.apache.beam.sdk.annotations.Experimental;
import org.apache.beam.sdk.coders.Coder;
import org.apache.beam.sdk.coders.RowCoder;
import org.apache.beam.sdk.options.ValueProvider;
import org.apache.beam.sdk.schemas.Schema;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PBegin;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.Row;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.apache.beam.vendor.guava.v26_0_jre.com.google.common.base.Preconditions.checkArgument;

@Experimental(Experimental.Kind.SOURCE_SINK)
@SuppressWarnings({
        "rawtypes", // TODO(https://issues.apache.org/jira/browse/BEAM-10556)
        "nullness" // TODO(https://issues.apache.org/jira/browse/BEAM-10402)
})
public class DummyIO {
    private static final Logger LOG = LoggerFactory.getLogger(DummyIO.class);

    private DummyIO(){}

    public static <T> Read<T> read(){
        return new AutoValue_DummyIO_Read.Builder<T>()
                .setConfiguration(null)
                .build();
    }
    public static ReadRows readRows(){
        return new AutoValue_DummyIO_ReadRows.Builder()
                .setFetchSize(100)
                .setOutputParallelization(false)
                .build();
    }
    @FunctionalInterface
    public interface RowMapper<T> extends Serializable {
        T mapRow() throws Exception;
    }
    @AutoValue
    @Experimental(Experimental.Kind.SCHEMAS)
    public abstract static class ReadRows<T> extends PTransform<PBegin, PCollection<Row>> {
        abstract int getFetchSize();
        abstract boolean getOutputParallelization();
        abstract Builder<T> builder();

        @AutoValue.Builder
        abstract static class Builder<T> {
            abstract Builder<T> setFetchSize(int fetchSize);
            abstract Builder<T> setOutputParallelization(boolean outputParallelization);
            abstract ReadRows<T> build();
        }

        public ReadRows withFetchSize(int fetchSize) {
            checkArgument(fetchSize > 0, "fetch size must be > 0");
            return builder().setFetchSize(fetchSize).build();
        }

        /**
         * Whether to reshuffle the resulting PCollection so results are distributed to all workers. The
         * default is to parallelize and should only be changed if this is known to be unnecessary.
         */
        public ReadRows withOutputParallelization(boolean outputParallelization) {
            return builder().setOutputParallelization(outputParallelization).build();
        }

        public PCollection<Row> expand(PBegin input) {
            Schema schema = SchemaUtils.toBeamSchema(null);
            return null;
            /*
            PCollection<Row> rows = input.apply(
                            DummyIO.<Row>read()
                                    .withCoder(RowCoder.of(schema))
                                    .withRowMapper(SchemaUtils.BeamRowMapper.of(schema))
                                    .withConfiguration(null));

             */


        }
    }
    @AutoValue
    public abstract static class Read<T> extends PTransform<PBegin, PCollection<T>> {
        abstract @Nullable ValueProvider<KV<String,String>> getConfiguration();
        abstract @Nullable RowMapper<T> getRowMapper();
        abstract @Nullable Coder<T> getCoder();
        abstract Builder<T> builder();

        @AutoValue.Builder
        abstract static class Builder<T> {
            abstract Builder<T> setConfiguration(ValueProvider<KV<String,String>> config);
            abstract Builder<T> setRowMapper(RowMapper<T> rowMapper);
            abstract Builder<T> setCoder(Coder<T> coder);
            abstract Read<T> build();
        }

        public Read<T> withConfiguration(ValueProvider<KV<String,String>> config){
            checkArgument(config != null, "Configuration can not be null");
            return builder().setConfiguration(config).build();
        }

        public Read<T> withRowMapper(RowMapper<T> rowMapper) {
            checkArgument(rowMapper != null, "Row Mapper can not be null");
            return builder().setRowMapper(rowMapper).build();
        }

        public Read<T> withCoder(Coder<T> coder) {
            checkArgument(coder != null, "coder can not be null");
            return builder().setCoder(coder).build();
        }

        public PCollection<T> expand(PBegin input) {
            ArrayList<String> attributes = null;
            Schema schema = SchemaUtils.toBeamSchema(attributes);
            PCollection<Row> rows = null;
            return null;
            //return input
            //        .apply(Create.of((Void)null))
            //        .apply(
            //                DummyIO.
             //       )
        }

    }
    @AutoValue
    public abstract static class ReadAll<ParameterT, OutputT>
            extends PTransform<PCollection<ParameterT>, PCollection<OutputT>> {
        abstract @Nullable RowMapper<OutputT> getRowMapper();
        abstract @Nullable Coder<OutputT> getCoder();
        abstract int getFetchSize();
        abstract boolean getOutputParallelization();
        abstract Builder<ParameterT, OutputT> toBuilder();
        @AutoValue.Builder
        abstract static class Builder<ParameterT, OutputT> {
            abstract Builder<ParameterT, OutputT> setRowMapper(RowMapper<OutputT> rowMapper);
            abstract Builder<ParameterT, OutputT> setCoder(Coder<OutputT> coder);
            abstract Builder<ParameterT, OutputT> setFetchSize(int fetchSize);
            abstract Builder<ParameterT, OutputT> setOutputParallelization(boolean outputParallelization);
            abstract ReadAll<ParameterT, OutputT> build();
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
            PCollection<OutputT> output =
                    input.apply(ParDo.of(new ReadFn<>(null, getRowMapper()))).setCoder(getCoder());
            return output;
        }
    }
    private static class ReadFn<ParameterT, OutputT> extends DoFn<ParameterT, OutputT> {
        private final ValueProvider<KV<String,String>> config;
        private final RowMapper<OutputT> rowMapper;

        private ReadFn(ValueProvider<KV<String, String>> config, RowMapper<OutputT> rowMapper){
            this.config = config;
            this.rowMapper = rowMapper;
        }

        @Setup
        public void setup() throws Exception {
            //do something here like get a connection
        }

        @ProcessElement
        public void processElement(ProcessContext context) throws Exception {
            List<ArrayList> res = null;
            context.output(rowMapper.mapRow());
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
            //do close and clean up
        }
    }
}
