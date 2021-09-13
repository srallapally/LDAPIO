

package com.forgerock.connector.beam;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_TestIO_ReadRows extends TestIO.ReadRows {

  private AutoValue_TestIO_ReadRows(
 ) {
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof TestIO.ReadRows) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    return h$;
  }

  @Override
  TestIO.ReadRows.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends TestIO.ReadRows.Builder {
    Builder() {
    }
    private Builder(TestIO.ReadRows source) {
    }
    @Override
    TestIO.ReadRows build() {
      return new AutoValue_TestIO_ReadRows(
   );
    }
  }

}
