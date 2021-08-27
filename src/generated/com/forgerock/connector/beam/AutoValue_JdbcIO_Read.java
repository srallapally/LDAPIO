

package com.forgerock.connector.beam;

import javax.annotation.Generated;
import javax.sql.DataSource;
import org.apache.beam.sdk.coders.Coder;
import org.apache.beam.sdk.options.ValueProvider;
import org.apache.beam.sdk.transforms.SerializableFunction;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_JdbcIO_Read<T> extends JdbcIO.Read<T> {

  private final SerializableFunction<Void, DataSource> dataSourceProviderFn;
  private final ValueProvider<String> query;
  private final JdbcIO.StatementPreparator statementPreparator;
  private final JdbcIO.RowMapper<T> rowMapper;
  private final Coder<T> coder;
  private final int fetchSize;
  private final boolean outputParallelization;

  private AutoValue_JdbcIO_Read(
       SerializableFunction<Void, DataSource> dataSourceProviderFn,
       ValueProvider<String> query,
       JdbcIO.StatementPreparator statementPreparator,
       JdbcIO.RowMapper<T> rowMapper,
       Coder<T> coder,
      int fetchSize,
      boolean outputParallelization) {
    this.dataSourceProviderFn = dataSourceProviderFn;
    this.query = query;
    this.statementPreparator = statementPreparator;
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
  JdbcIO.StatementPreparator getStatementPreparator() {
    return statementPreparator;
  }

  @Override
  JdbcIO.RowMapper<T> getRowMapper() {
    return rowMapper;
  }

  @Override
  Coder<T> getCoder() {
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
    if (o instanceof JdbcIO.Read) {
      JdbcIO.Read<?> that = (JdbcIO.Read<?>) o;
      return ((this.dataSourceProviderFn == null) ? (that.getDataSourceProviderFn() == null) : this.dataSourceProviderFn.equals(that.getDataSourceProviderFn()))
           && ((this.query == null) ? (that.getQuery() == null) : this.query.equals(that.getQuery()))
           && ((this.statementPreparator == null) ? (that.getStatementPreparator() == null) : this.statementPreparator.equals(that.getStatementPreparator()))
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
    h$ ^= (statementPreparator == null) ? 0 : statementPreparator.hashCode();
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
  JdbcIO.Read.Builder<T> toBuilder() {
    return new Builder<T>(this);
  }

  static final class Builder<T> extends JdbcIO.Read.Builder<T> {
    private SerializableFunction<Void, DataSource> dataSourceProviderFn;
    private ValueProvider<String> query;
    private JdbcIO.StatementPreparator statementPreparator;
    private JdbcIO.RowMapper<T> rowMapper;
    private Coder<T> coder;
    private Integer fetchSize;
    private Boolean outputParallelization;
    Builder() {
    }
    private Builder(JdbcIO.Read<T> source) {
      this.dataSourceProviderFn = source.getDataSourceProviderFn();
      this.query = source.getQuery();
      this.statementPreparator = source.getStatementPreparator();
      this.rowMapper = source.getRowMapper();
      this.coder = source.getCoder();
      this.fetchSize = source.getFetchSize();
      this.outputParallelization = source.getOutputParallelization();
    }
    @Override
    JdbcIO.Read.Builder<T> setDataSourceProviderFn(SerializableFunction<Void, DataSource> dataSourceProviderFn) {
      this.dataSourceProviderFn = dataSourceProviderFn;
      return this;
    }
    @Override
    JdbcIO.Read.Builder<T> setQuery(ValueProvider<String> query) {
      this.query = query;
      return this;
    }
    @Override
    JdbcIO.Read.Builder<T> setStatementPreparator(JdbcIO.StatementPreparator statementPreparator) {
      this.statementPreparator = statementPreparator;
      return this;
    }
    @Override
    JdbcIO.Read.Builder<T> setRowMapper(JdbcIO.RowMapper<T> rowMapper) {
      this.rowMapper = rowMapper;
      return this;
    }
    @Override
    JdbcIO.Read.Builder<T> setCoder(Coder<T> coder) {
      this.coder = coder;
      return this;
    }
    @Override
    JdbcIO.Read.Builder<T> setFetchSize(int fetchSize) {
      this.fetchSize = fetchSize;
      return this;
    }
    @Override
    JdbcIO.Read.Builder<T> setOutputParallelization(boolean outputParallelization) {
      this.outputParallelization = outputParallelization;
      return this;
    }
    @Override
    JdbcIO.Read<T> build() {
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
      return new AutoValue_JdbcIO_Read<T>(
          this.dataSourceProviderFn,
          this.query,
          this.statementPreparator,
          this.rowMapper,
          this.coder,
          this.fetchSize,
          this.outputParallelization);
    }
  }

}
