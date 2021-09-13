

package com.forgerock.connector.beam;

import javax.annotation.Generated;
import org.apache.beam.sdk.coders.Coder;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_TestIO_Read<T> extends TestIO.Read<T> {

  private final TestIO.RowMapper<T> rowMapper;
  private final Coder<T> coder;

  private AutoValue_TestIO_Read(
       TestIO.RowMapper<T> rowMapper,
       Coder<T> coder) {
    this.rowMapper = rowMapper;
    this.coder = coder;
  }

  @Override
  TestIO.RowMapper<T> getRowMapper() {
    return rowMapper;
  }

  @Override
  Coder<T> getCoder() {
    return coder;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof TestIO.Read) {
      TestIO.Read<?> that = (TestIO.Read<?>) o;
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
  TestIO.Read.Builder<T> toBuilder() {
    return new Builder<T>(this);
  }

  static final class Builder<T> extends TestIO.Read.Builder<T> {
    private TestIO.RowMapper<T> rowMapper;
    private Coder<T> coder;
    Builder() {
    }
    private Builder(TestIO.Read<T> source) {
      this.rowMapper = source.getRowMapper();
      this.coder = source.getCoder();
    }
    @Override
    TestIO.Read.Builder<T> setRowMapper(TestIO.RowMapper<T> rowMapper) {
      this.rowMapper = rowMapper;
      return this;
    }
    @Override
    TestIO.Read.Builder<T> setCoder(Coder<T> coder) {
      this.coder = coder;
      return this;
    }
    @Override
    TestIO.Read<T> build() {
      return new AutoValue_TestIO_Read<T>(
          this.rowMapper,
          this.coder);
    }
  }

}
