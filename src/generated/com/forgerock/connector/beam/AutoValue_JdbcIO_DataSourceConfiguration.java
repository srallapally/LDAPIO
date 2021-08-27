

package com.forgerock.connector.beam;

import java.util.Collection;
import javax.annotation.Generated;
import javax.sql.DataSource;
import org.apache.beam.sdk.options.ValueProvider;
import org.checkerframework.checker.nullness.qual.Nullable;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_JdbcIO_DataSourceConfiguration extends JdbcIO.DataSourceConfiguration {

  private final @Nullable ValueProvider<String> driverClassName;
  private final @Nullable ValueProvider<String> url;
  private final @Nullable ValueProvider<String> username;
  private final @Nullable ValueProvider<String> password;
  private final @Nullable ValueProvider<String> connectionProperties;
  private final @Nullable ValueProvider<Collection<String>> connectionInitSqls;
  private final @Nullable DataSource dataSource;

  private AutoValue_JdbcIO_DataSourceConfiguration(
       @Nullable ValueProvider<String> driverClassName,
       @Nullable ValueProvider<String> url,
       @Nullable ValueProvider<String> username,
       @Nullable ValueProvider<String> password,
       @Nullable ValueProvider<String> connectionProperties,
       @Nullable ValueProvider<Collection<String>> connectionInitSqls,
       @Nullable DataSource dataSource) {
    this.driverClassName = driverClassName;
    this.url = url;
    this.username = username;
    this.password = password;
    this.connectionProperties = connectionProperties;
    this.connectionInitSqls = connectionInitSqls;
    this.dataSource = dataSource;
  }

  @Override
  @Nullable ValueProvider<String> getDriverClassName() {
    return driverClassName;
  }

  @Override
  @Nullable ValueProvider<String> getUrl() {
    return url;
  }

  @Override
  @Nullable ValueProvider<String> getUsername() {
    return username;
  }

  @Override
  @Nullable ValueProvider<String> getPassword() {
    return password;
  }

  @Override
  @Nullable ValueProvider<String> getConnectionProperties() {
    return connectionProperties;
  }

  @Override
  @Nullable ValueProvider<Collection<String>> getConnectionInitSqls() {
    return connectionInitSqls;
  }

  @Override
  @Nullable DataSource getDataSource() {
    return dataSource;
  }

  @Override
  public String toString() {
    return "DataSourceConfiguration{"
         + "driverClassName=" + driverClassName + ", "
         + "url=" + url + ", "
         + "username=" + username + ", "
         + "password=" + password + ", "
         + "connectionProperties=" + connectionProperties + ", "
         + "connectionInitSqls=" + connectionInitSqls + ", "
         + "dataSource=" + dataSource
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof JdbcIO.DataSourceConfiguration) {
      JdbcIO.DataSourceConfiguration that = (JdbcIO.DataSourceConfiguration) o;
      return ((this.driverClassName == null) ? (that.getDriverClassName() == null) : this.driverClassName.equals(that.getDriverClassName()))
           && ((this.url == null) ? (that.getUrl() == null) : this.url.equals(that.getUrl()))
           && ((this.username == null) ? (that.getUsername() == null) : this.username.equals(that.getUsername()))
           && ((this.password == null) ? (that.getPassword() == null) : this.password.equals(that.getPassword()))
           && ((this.connectionProperties == null) ? (that.getConnectionProperties() == null) : this.connectionProperties.equals(that.getConnectionProperties()))
           && ((this.connectionInitSqls == null) ? (that.getConnectionInitSqls() == null) : this.connectionInitSqls.equals(that.getConnectionInitSqls()))
           && ((this.dataSource == null) ? (that.getDataSource() == null) : this.dataSource.equals(that.getDataSource()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= (driverClassName == null) ? 0 : driverClassName.hashCode();
    h$ *= 1000003;
    h$ ^= (url == null) ? 0 : url.hashCode();
    h$ *= 1000003;
    h$ ^= (username == null) ? 0 : username.hashCode();
    h$ *= 1000003;
    h$ ^= (password == null) ? 0 : password.hashCode();
    h$ *= 1000003;
    h$ ^= (connectionProperties == null) ? 0 : connectionProperties.hashCode();
    h$ *= 1000003;
    h$ ^= (connectionInitSqls == null) ? 0 : connectionInitSqls.hashCode();
    h$ *= 1000003;
    h$ ^= (dataSource == null) ? 0 : dataSource.hashCode();
    return h$;
  }

  @Override
  JdbcIO.DataSourceConfiguration.Builder builder() {
    return new Builder(this);
  }

  static final class Builder extends JdbcIO.DataSourceConfiguration.Builder {
    private @Nullable ValueProvider<String> driverClassName;
    private @Nullable ValueProvider<String> url;
    private @Nullable ValueProvider<String> username;
    private @Nullable ValueProvider<String> password;
    private @Nullable ValueProvider<String> connectionProperties;
    private @Nullable ValueProvider<Collection<String>> connectionInitSqls;
    private @Nullable DataSource dataSource;
    Builder() {
    }
    private Builder(JdbcIO.DataSourceConfiguration source) {
      this.driverClassName = source.getDriverClassName();
      this.url = source.getUrl();
      this.username = source.getUsername();
      this.password = source.getPassword();
      this.connectionProperties = source.getConnectionProperties();
      this.connectionInitSqls = source.getConnectionInitSqls();
      this.dataSource = source.getDataSource();
    }
    @Override
    JdbcIO.DataSourceConfiguration.Builder setDriverClassName(ValueProvider<String> driverClassName) {
      this.driverClassName = driverClassName;
      return this;
    }
    @Override
    JdbcIO.DataSourceConfiguration.Builder setUrl(ValueProvider<String> url) {
      this.url = url;
      return this;
    }
    @Override
    JdbcIO.DataSourceConfiguration.Builder setUsername(ValueProvider<String> username) {
      this.username = username;
      return this;
    }
    @Override
    JdbcIO.DataSourceConfiguration.Builder setPassword(ValueProvider<String> password) {
      this.password = password;
      return this;
    }
    @Override
    JdbcIO.DataSourceConfiguration.Builder setConnectionProperties(ValueProvider<String> connectionProperties) {
      this.connectionProperties = connectionProperties;
      return this;
    }
    @Override
    JdbcIO.DataSourceConfiguration.Builder setConnectionInitSqls(ValueProvider<Collection<String>> connectionInitSqls) {
      this.connectionInitSqls = connectionInitSqls;
      return this;
    }
    @Override
    JdbcIO.DataSourceConfiguration.Builder setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      return this;
    }
    @Override
    JdbcIO.DataSourceConfiguration build() {
      return new AutoValue_JdbcIO_DataSourceConfiguration(
          this.driverClassName,
          this.url,
          this.username,
          this.password,
          this.connectionProperties,
          this.connectionInitSqls,
          this.dataSource);
    }
  }

}
