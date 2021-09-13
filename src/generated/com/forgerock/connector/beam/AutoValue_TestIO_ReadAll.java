

package com.forgerock.connector.beam;

import javax.annotation.Generated;
import org.apache.beam.sdk.coders.Coder;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_TestIO_ReadAll<ParameterT, OutputT> extends TestIO.ReadAll<ParameterT, OutputT> {

  private final TestIO.RowMapper<OutputT> rowMapper;
  private final Coder<OutputT> coder;

  private AutoValue_TestIO_ReadAll(
       TestIO.RowMapper<OutputT> rowMapper,
       Coder<OutputT> coder) {
    this.rowMapper = rowMapper;
    this.coder = coder;
  }

  @Override
  TestIO.RowMapper<OutputT> getRowMapper() {
    return rowMapper;
  }

  @Override
  Coder<OutputT> getCoder() {
    return coder;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof TestIO.ReadAll) {
      TestIO.ReadAll<?, ?> that = (TestIO.ReadAll<?, ?>) o;
      return ((this.rowMapper == null) ? (that.getRowMapper() == null) : this.rowMapper.equals(that.getRowMapper()))
           && ((this.coder == null) ? (that.getCoder() == null) : this.coder.equals(that.getCoder()));
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
    return h$;
  }

  @Override
  TestIO.ReadAll.Builder<ParameterT, OutputT> toBuilder() {
    return new Builder<ParameterT, OutputT>(this);
  }

  static final class Builder<ParameterT, OutputT> extends TestIO.ReadAll.Builder<ParameterT, OutputT> {
    private TestIO.RowMapper<OutputT> rowMapper;
    private Coder<OutputT> coder;
    Builder() {
    }
    private Builder(TestIO.ReadAll<ParameterT, OutputT> source) {
      this.rowMapper = source.getRowMapper();
      this.coder = source.getCoder();
    }
    @Override
    TestIO.ReadAll.Builder<ParameterT, OutputT> setRowMapper(TestIO.RowMapper<OutputT> rowMapper) {
      this.rowMapper = rowMapper;
      return this;
    }
    @Override
    TestIO.ReadAll.Builder<ParameterT, OutputT> setCoder(Coder<OutputT> coder) {
      this.coder = coder;
      return this;
    }
    @Override
    TestIO.ReadAll<ParameterT, OutputT> build() {
      return new AutoValue_TestIO_ReadAll<ParameterT, OutputT>(
          this.rowMapper,
          this.coder);
    }
  }

}
