

package com.forgerock.connector.beam;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_DummyIO_ReadRows<T> extends DummyIO.ReadRows<T> {

  private final int fetchSize;
  private final boolean outputParallelization;

  private AutoValue_DummyIO_ReadRows(
      int fetchSize,
      boolean outputParallelization) {
    this.fetchSize = fetchSize;
    this.outputParallelization = outputParallelization;
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
    if (o instanceof DummyIO.ReadRows) {
      DummyIO.ReadRows<?> that = (DummyIO.ReadRows<?>) o;
      return (this.fetchSize == that.getFetchSize())
           && (this.outputParallelization == that.getOutputParallelization());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= fetchSize;
    h$ *= 1000003;
    h$ ^= outputParallelization ? 1231 : 1237;
    return h$;
  }

  @Override
  DummyIO.ReadRows.Builder<T> builder() {
    return new Builder<T>(this);
  }

  static final class Builder<T> extends DummyIO.ReadRows.Builder<T> {
    private Integer fetchSize;
    private Boolean outputParallelization;
    Builder() {
    }
    private Builder(DummyIO.ReadRows<T> source) {
      this.fetchSize = source.getFetchSize();
      this.outputParallelization = source.getOutputParallelization();
    }
    @Override
    DummyIO.ReadRows.Builder<T> setFetchSize(int fetchSize) {
      this.fetchSize = fetchSize;
      return this;
    }
    @Override
    DummyIO.ReadRows.Builder<T> setOutputParallelization(boolean outputParallelization) {
      this.outputParallelization = outputParallelization;
      return this;
    }
    @Override
    DummyIO.ReadRows<T> build() {
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
      return new AutoValue_DummyIO_ReadRows<T>(
          this.fetchSize,
          this.outputParallelization);
    }
  }

}
