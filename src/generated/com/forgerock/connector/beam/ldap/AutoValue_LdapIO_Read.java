

package com.forgerock.connector.beam.ldap;

import javax.annotation.Generated;
import javax.annotation.Nullable;
import org.apache.beam.sdk.coders.Coder;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_LdapIO_Read<T> extends LdapIO.Read<T> {

  private final LdapIO.LdapConfiguration ldapConfiguration;
  private final LdapIO.RowMapper<T> rowMapper;
  private final Coder<T> coder;

  private AutoValue_LdapIO_Read(
      @Nullable LdapIO.LdapConfiguration ldapConfiguration,
      @Nullable LdapIO.RowMapper<T> rowMapper,
      @Nullable Coder<T> coder) {
    this.ldapConfiguration = ldapConfiguration;
    this.rowMapper = rowMapper;
    this.coder = coder;
  }

  @Nullable
  @Override
  LdapIO.LdapConfiguration getLdapConfiguration() {
    return ldapConfiguration;
  }

  @Nullable
  @Override
  LdapIO.RowMapper<T> getRowMapper() {
    return rowMapper;
  }

  @Nullable
  @Override
  Coder<T> getCoder() {
    return coder;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LdapIO.Read) {
      LdapIO.Read<?> that = (LdapIO.Read<?>) o;
      return ((this.ldapConfiguration == null) ? (that.getLdapConfiguration() == null) : this.ldapConfiguration.equals(that.getLdapConfiguration()))
           && ((this.rowMapper == null) ? (that.getRowMapper() == null) : this.rowMapper.equals(that.getRowMapper()))
           && ((this.coder == null) ? (that.getCoder() == null) : this.coder.equals(that.getCoder()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= (ldapConfiguration == null) ? 0 : ldapConfiguration.hashCode();
    h$ *= 1000003;
    h$ ^= (rowMapper == null) ? 0 : rowMapper.hashCode();
    h$ *= 1000003;
    h$ ^= (coder == null) ? 0 : coder.hashCode();
    return h$;
  }

  @Override
  LdapIO.Read.Builder<T> toBuilder() {
    return new Builder<T>(this);
  }

  static final class Builder<T> extends LdapIO.Read.Builder<T> {
    private LdapIO.LdapConfiguration ldapConfiguration;
    private LdapIO.RowMapper<T> rowMapper;
    private Coder<T> coder;
    Builder() {
    }
    private Builder(LdapIO.Read<T> source) {
      this.ldapConfiguration = source.getLdapConfiguration();
      this.rowMapper = source.getRowMapper();
      this.coder = source.getCoder();
    }
    @Override
    LdapIO.Read.Builder<T> setLdapConfiguration(@Nullable LdapIO.LdapConfiguration ldapConfiguration) {
      this.ldapConfiguration = ldapConfiguration;
      return this;
    }
    @Override
    LdapIO.Read.Builder<T> setRowMapper(@Nullable LdapIO.RowMapper<T> rowMapper) {
      this.rowMapper = rowMapper;
      return this;
    }
    @Override
    LdapIO.Read.Builder<T> setCoder(@Nullable Coder<T> coder) {
      this.coder = coder;
      return this;
    }
    @Override
    LdapIO.Read<T> build() {
      return new AutoValue_LdapIO_Read<T>(
          this.ldapConfiguration,
          this.rowMapper,
          this.coder);
    }
  }

}
