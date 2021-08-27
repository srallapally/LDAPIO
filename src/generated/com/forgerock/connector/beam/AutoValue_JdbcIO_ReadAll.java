

package com.forgerock.connector.beam;

import javax.annotation.Generated;
import javax.sql.DataSource;
import org.apache.beam.sdk.coders.Coder;
import org.apache.beam.sdk.options.ValueProvider;
import org.apache.beam.sdk.transforms.SerializableFunction;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_JdbcIO_ReadAll<ParameterT, OutputT> extends JdbcIO.ReadAll<ParameterT, OutputT> {

  private final SerializableFunction<Void, DataSource> dataSourceProviderFn;
  private final ValueProvider<String> query;
  private final JdbcIO.PreparedStatementSetter<ParameterT> parameterSetter;
  private final JdbcIO.RowMapper<OutputT> rowMapper;
  private final Coder<OutputT> coder;
  private final int fetchSize;
  private final boolean outputParallelization;

  private AutoValue_JdbcIO_ReadAll(
       SerializableFunction<Void, DataSource> dataSourceProviderFn,
       ValueProvider<String> query,
       JdbcIO.PreparedStatementSetter<ParameterT> parameterSetter,
       JdbcIO.RowMapper<OutputT> rowMapper,
       Coder<OutputT> coder,
      int fetchSize,
      boolean outputParallelization) {
    this.dataSourceProviderFn = dataSourceProviderFn;
    this.query = query;
    this.parameterSetter = parameterSetter;
    this.rowMapper = rowMapper;
    this.coder = coder;
    this.fetchSize = fetchSize;
    this.outputParallelization = outputParallelization;
  }

  @Override
  SerializableFunction<Void, DataSource> getDataSourceProviderFn() {
    return dataSourceProviderFn;
  }

  @Override
  ValueProvider<String> getQuery() {
    return query;
  }

  @Override
  JdbcIO.PreparedStatementSetter<ParameterT> getParameterSetter() {
    return parameterSetter;
  }

  @Override
  JdbcIO.RowMapper<OutputT> getRowMapper() {
    return rowMapper;
  }

  @Override
  Coder<OutputT> getCoder() {
    return coder;
  }

  @Override
  int getFetchSize() {
    return fetchSize;
  }

  @Override
  boolean getOutputParallelization() {
    return outputParallelization;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof JdbcIO.ReadAll) {
      JdbcIO.ReadAll<?, ?> that = (JdbcIO.ReadAll<?, ?>) o;
      return ((this.dataSourceProviderFn == null) ? (that.getDataSourceProviderFn() == null) : this.dataSourceProviderFn.equals(that.getDataSourceProviderFn()))
           && ((this.query == null) ? (that.getQuery() == null) : this.query.equals(that.getQuery()))
           && ((this.parameterSetter == null) ? (that.getParameterSetter() == null) : this.parameterSetter.equals(that.getParameterSetter()))
           && ((this.rowMapper == null) ? (that.getRowMapper() == null) : this.rowMapper.equals(that.getRowMapper()))
           && ((this.coder == null) ? (that.getCoder() == null) : this.coder.equals(that.getCoder()))
           && (this.fetchSize == that.getFetchSize())
           && (this.outputParallelization == that.getOutputParallelization());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= (dataSourceProviderFn == null) ? 0 : dataSourceProviderFn.hashCode();
    h$ *= 1000003;
    h$ ^= (query == null) ? 0 : query.hashCode();
    h$ *= 1000003;
    h$ ^= (parameterSetter == null) ? 0 : parameterSetter.hashCode();
    h$ *= 1000003;
    h$ ^= (rowMapper == null) ? 0 : rowMapper.hashCode();
    h$ *= 1000003;
    h$ ^= (coder == null) ? 0 : coder.hashCode();
    h$ *= 1000003;
    h$ ^= fetchSize;
    h$ *= 1000003;
    h$ ^= outputParallelization ? 1231 : 1237;
    return h$;
  }

  @Override
  JdbcIO.ReadAll.Builder<ParameterT, OutputT> toBuilder() {
    return new Builder<ParameterT, OutputT>(this);
  }

  static final class Builder<ParameterT, OutputT> extends JdbcIO.ReadAll.Builder<ParameterT, OutputT> {
    private SerializableFunction<Void, DataSource> dataSourceProviderFn;
    private ValueProvider<String> query;
    private JdbcIO.PreparedStatementSetter<ParameterT> parameterSetter;
    private JdbcIO.RowMapper<OutputT> rowMapper;
    private Coder<OutputT> coder;
    private Integer fetchSize;
    private Boolean outputParallelization;
    Builder() {
    }
    private Builder(JdbcIO.ReadAll<ParameterT, OutputT> source) {
      this.dataSourceProviderFn = source.getDataSourceProviderFn();
      this.query = source.getQuery();
      this.parameterSetter = source.getParameterSetter();
      this.rowMapper = source.getRowMapper();
      this.coder = source.getCoder();
      this.fetchSize = source.getFetchSize();
      this.outputParallelization = source.getOutputParallelization();
    }
    @Override
    JdbcIO.ReadAll.Builder<ParameterT, OutputT> setDataSourceProviderFn(SerializableFunction<Void, DataSource> dataSourceProviderFn) {
      this.dataSourceProviderFn = dataSourceProviderFn;
      return this;
    }
    @Override
    JdbcIO.ReadAll.Builder<ParameterT, OutputT> setQuery(ValueProvider<String> query) {
      this.query = query;
      return this;
    }
    @Override
    JdbcIO.ReadAll.Builder<ParameterT, OutputT> setParameterSetter(JdbcIO.PreparedStatementSetter<ParameterT> parameterSetter) {
      this.parameterSetter = parameterSetter;
      return this;
    }
    @Override
    JdbcIO.ReadAll.Builder<ParameterT, OutputT> setRowMapper(JdbcIO.RowMapper<OutputT> rowMapper) {
      this.rowMapper = rowMapper;
      return this;
    }
    @Override
    JdbcIO.ReadAll.Builder<ParameterT, OutputT> setCoder(Coder<OutputT> coder) {
      this.coder = coder;
      return this;
    }
    @Override
    JdbcIO.ReadAll.Builder<ParameterT, OutputT> setFetchSize(int fetchSize) {
      this.fetchSize = fetchSize;
      return this;
    }
    @Override
    JdbcIO.ReadAll.Builder<ParameterT, OutputT> setOutputParallelization(boolean outputParallelization) {
      this.outputParallelization = outputParallelization;
      return this;
    }
    @Override
    JdbcIO.ReadAll<ParameterT, OutputT> build() {
      String missing = "";
      if (this.fetchSize == null) {
        missing += " fetchSize";
      }
      if (this.outputParallelization == null) {
        missing += " outputParallelization";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_JdbcIO_ReadAll<ParameterT, OutputT>(
          this.dataSourceProviderFn,
          this.query,
          this.parameterSetter,
          this.rowMapper,
          this.coder,
          this.fetchSize,
          this.outputParallelization);
    }
  }

}
