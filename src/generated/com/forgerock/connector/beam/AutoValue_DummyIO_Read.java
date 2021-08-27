

package com.forgerock.connector.beam;

import javax.annotation.Generated;
import org.apache.beam.sdk.coders.Coder;
import org.apache.beam.sdk.options.ValueProvider;
import org.apache.beam.sdk.values.KV;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_DummyIO_Read<T> extends DummyIO.Read<T> {

  private final ValueProvider<KV<String, String>> configuration;
  private final DummyIO.RowMapper<T> rowMapper;
  private final Coder<T> coder;

  private AutoValue_DummyIO_Read(
       ValueProvider<KV<String, String>> configuration,
       DummyIO.RowMapper<T> rowMapper,
       Coder<T> coder) {
    this.configuration = configuration;
    this.rowMapper = rowMapper;
    this.coder = coder;
  }

  @Override
  ValueProvider<KV<String, String>> getConfiguration() {
    return configuration;
  }

  @Override
  DummyIO.RowMapper<T> getRowMapper() {
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
    if (o instanceof DummyIO.Read) {
      DummyIO.Read<?> that = (DummyIO.Read<?>) o;
      return ((this.configuration == null) ? (that.getConfiguration() == null) : this.configuration.equals(that.getConfiguration()))
           && ((this.rowMapper == null) ? (that.getRowMapper() == null) : this.rowMapper.equals(that.getRowMapper()))
           && ((this.coder == null) ? (that.getCoder() == null) : this.coder.equals(that.getCoder()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= (configuration == null) ? 0 : configuration.hashCode();
    h$ *= 1000003;
    h$ ^= (rowMapper == null) ? 0 : rowMapper.hashCode();
    h$ *= 1000003;
    h$ ^= (coder == null) ? 0 : coder.hashCode();
    return h$;
  }

  @Override
  DummyIO.Read.Builder<T> builder() {
    return new Builder<T>(this);
  }

  static final class Builder<T> extends DummyIO.Read.Builder<T> {
    private ValueProvider<KV<String, String>> configuration;
    private DummyIO.RowMapper<T> rowMapper;
    private Coder<T> coder;
    Builder() {
    }
    private Builder(DummyIO.Read<T> source) {
      this.configuration = source.getConfiguration();
      this.rowMapper = source.getRowMapper();
      this.coder = source.getCoder();
    }
    @Override
    DummyIO.Read.Builder<T> setConfiguration(ValueProvider<KV<String, String>> configuration) {
      this.configuration = configuration;
      return this;
    }
    @Override
    DummyIO.Read.Builder<T> setRowMapper(DummyIO.RowMapper<T> rowMapper) {
      this.rowMapper = rowMapper;
      return this;
    }
    @Override
    DummyIO.Read.Builder<T> setCoder(Coder<T> coder) {
      this.coder = coder;
      return this;
    }
    @Override
    DummyIO.Read<T> build() {
      return new AutoValue_DummyIO_Read<T>(
          this.configuration,
          this.rowMapper,
          this.coder);
    }
  }

}
