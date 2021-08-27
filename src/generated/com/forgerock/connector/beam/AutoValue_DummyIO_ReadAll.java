

package com.forgerock.connector.beam;

import javax.annotation.Generated;
import org.apache.beam.sdk.coders.Coder;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_DummyIO_ReadAll<ParameterT, OutputT> extends DummyIO.ReadAll<ParameterT, OutputT> {

  private final DummyIO.RowMapper<OutputT> rowMapper;
  private final Coder<OutputT> coder;
  private final int fetchSize;
  private final boolean outputParallelization;

  private AutoValue_DummyIO_ReadAll(
       DummyIO.RowMapper<OutputT> rowMapper,
       Coder<OutputT> coder,
      int fetchSize,
      boolean outputParallelization) {
    this.rowMapper = rowMapper;
    this.coder = coder;
    this.fetchSize = fetchSize;
    this.outputParallelization = outputParallelization;
  }

  @Override
  DummyIO.RowMapper<OutputT> getRowMapper() {
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
    if (o instanceof DummyIO.ReadAll) {
      DummyIO.ReadAll<?, ?> that = (DummyIO.ReadAll<?, ?>) o;
      return ((this.rowMapper == null) ? (that.getRowMapper() == null) : this.rowMapper.equals(that.getRowMapper()))
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
  DummyIO.ReadAll.Builder<ParameterT, OutputT> toBuilder() {
    return new Builder<ParameterT, OutputT>(this);
  }

  static final class Builder<ParameterT, OutputT> extends DummyIO.ReadAll.Builder<ParameterT, OutputT> {
    private DummyIO.RowMapper<OutputT> rowMapper;
    private Coder<OutputT> coder;
    private Integer fetchSize;
    private Boolean outputParallelization;
    Builder() {
    }
    private Builder(DummyIO.ReadAll<ParameterT, OutputT> source) {
      this.rowMapper = source.getRowMapper();
      this.coder = source.getCoder();
      this.fetchSize = source.getFetchSize();
      this.outputParallelization = source.getOutputParallelization();
    }
    @Override
    DummyIO.ReadAll.Builder<ParameterT, OutputT> setRowMapper(DummyIO.RowMapper<OutputT> rowMapper) {
      this.rowMapper = rowMapper;
      return this;
    }
    @Override
    DummyIO.ReadAll.Builder<ParameterT, OutputT> setCoder(Coder<OutputT> coder) {
      this.coder = coder;
      return this;
    }
    @Override
    DummyIO.ReadAll.Builder<ParameterT, OutputT> setFetchSize(int fetchSize) {
      this.fetchSize = fetchSize;
      return this;
    }
    @Override
    DummyIO.ReadAll.Builder<ParameterT, OutputT> setOutputParallelization(boolean outputParallelization) {
      this.outputParallelization = outputParallelization;
      return this;
    }
    @Override
    DummyIO.ReadAll<ParameterT, OutputT> build() {
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
      return new AutoValue_DummyIO_ReadAll<ParameterT, OutputT>(
          this.rowMapper,
          this.coder,
          this.fetchSize,
          this.outputParallelization);
    }
  }

}
