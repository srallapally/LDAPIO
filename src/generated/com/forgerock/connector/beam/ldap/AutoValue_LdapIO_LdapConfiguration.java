

package com.forgerock.connector.beam.ldap;

import javax.annotation.Generated;
import javax.annotation.Nullable;
import org.apache.beam.sdk.options.ValueProvider;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_LdapIO_LdapConfiguration extends LdapIO.LdapConfiguration {

  private final ValueProvider<String> host;
  private final ValueProvider<Integer> port;
  private final ValueProvider<Boolean> isSsl;
  private final ValueProvider<String> principal;
  private final ValueProvider<String> password;
  private final ValueProvider<String> baseContexts;
  private final ValueProvider<String> baseContextsToSearch;
  private final ValueProvider<String> userSearchFilter;
  private final ValueProvider<String> attributesToGet;
  private final ValueProvider<String> groupSearchFilter;
  private final ValueProvider<Boolean> useBlocks;
  private final ValueProvider<String> groupMemberAttribute;
  private final ValueProvider<Integer> blockSize;
  private final ValueProvider<Boolean> usePagedResultControl;
  private final ValueProvider<String> vlvSortAttribute;
  private final ValueProvider<String> uidAttribute;

  private AutoValue_LdapIO_LdapConfiguration(
      @Nullable ValueProvider<String> host,
      @Nullable ValueProvider<Integer> port,
      @Nullable ValueProvider<Boolean> isSsl,
      @Nullable ValueProvider<String> principal,
      @Nullable ValueProvider<String> password,
      @Nullable ValueProvider<String> baseContexts,
      @Nullable ValueProvider<String> baseContextsToSearch,
      @Nullable ValueProvider<String> userSearchFilter,
      @Nullable ValueProvider<String> attributesToGet,
      @Nullable ValueProvider<String> groupSearchFilter,
      @Nullable ValueProvider<Boolean> useBlocks,
      @Nullable ValueProvider<String> groupMemberAttribute,
      @Nullable ValueProvider<Integer> blockSize,
      @Nullable ValueProvider<Boolean> usePagedResultControl,
      @Nullable ValueProvider<String> vlvSortAttribute,
      @Nullable ValueProvider<String> uidAttribute) {
    this.host = host;
    this.port = port;
    this.isSsl = isSsl;
    this.principal = principal;
    this.password = password;
    this.baseContexts = baseContexts;
    this.baseContextsToSearch = baseContextsToSearch;
    this.userSearchFilter = userSearchFilter;
    this.attributesToGet = attributesToGet;
    this.groupSearchFilter = groupSearchFilter;
    this.useBlocks = useBlocks;
    this.groupMemberAttribute = groupMemberAttribute;
    this.blockSize = blockSize;
    this.usePagedResultControl = usePagedResultControl;
    this.vlvSortAttribute = vlvSortAttribute;
    this.uidAttribute = uidAttribute;
  }

  @Nullable
  @Override
  ValueProvider<String> getHost() {
    return host;
  }

  @Nullable
  @Override
  ValueProvider<Integer> getPort() {
    return port;
  }

  @Nullable
  @Override
  ValueProvider<Boolean> getIsSsl() {
    return isSsl;
  }

  @Nullable
  @Override
  ValueProvider<String> getPrincipal() {
    return principal;
  }

  @Nullable
  @Override
  ValueProvider<String> getPassword() {
    return password;
  }

  @Nullable
  @Override
  ValueProvider<String> getBaseContexts() {
    return baseContexts;
  }

  @Nullable
  @Override
  ValueProvider<String> getBaseContextsToSearch() {
    return baseContextsToSearch;
  }

  @Nullable
  @Override
  ValueProvider<String> getUserSearchFilter() {
    return userSearchFilter;
  }

  @Nullable
  @Override
  ValueProvider<String> getAttributesToGet() {
    return attributesToGet;
  }

  @Nullable
  @Override
  ValueProvider<String> getGroupSearchFilter() {
    return groupSearchFilter;
  }

  @Nullable
  @Override
  ValueProvider<Boolean> getUseBlocks() {
    return useBlocks;
  }

  @Nullable
  @Override
  ValueProvider<String> getGroupMemberAttribute() {
    return groupMemberAttribute;
  }

  @Nullable
  @Override
  ValueProvider<Integer> getBlockSize() {
    return blockSize;
  }

  @Nullable
  @Override
  ValueProvider<Boolean> getUsePagedResultControl() {
    return usePagedResultControl;
  }

  @Nullable
  @Override
  ValueProvider<String> getVlvSortAttribute() {
    return vlvSortAttribute;
  }

  @Nullable
  @Override
  ValueProvider<String> getUidAttribute() {
    return uidAttribute;
  }

  @Override
  public String toString() {
    return "LdapConfiguration{"
         + "host=" + host + ", "
         + "port=" + port + ", "
         + "isSsl=" + isSsl + ", "
         + "principal=" + principal + ", "
         + "password=" + password + ", "
         + "baseContexts=" + baseContexts + ", "
         + "baseContextsToSearch=" + baseContextsToSearch + ", "
         + "userSearchFilter=" + userSearchFilter + ", "
         + "attributesToGet=" + attributesToGet + ", "
         + "groupSearchFilter=" + groupSearchFilter + ", "
         + "useBlocks=" + useBlocks + ", "
         + "groupMemberAttribute=" + groupMemberAttribute + ", "
         + "blockSize=" + blockSize + ", "
         + "usePagedResultControl=" + usePagedResultControl + ", "
         + "vlvSortAttribute=" + vlvSortAttribute + ", "
         + "uidAttribute=" + uidAttribute
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof LdapIO.LdapConfiguration) {
      LdapIO.LdapConfiguration that = (LdapIO.LdapConfiguration) o;
      return ((this.host == null) ? (that.getHost() == null) : this.host.equals(that.getHost()))
           && ((this.port == null) ? (that.getPort() == null) : this.port.equals(that.getPort()))
           && ((this.isSsl == null) ? (that.getIsSsl() == null) : this.isSsl.equals(that.getIsSsl()))
           && ((this.principal == null) ? (that.getPrincipal() == null) : this.principal.equals(that.getPrincipal()))
           && ((this.password == null) ? (that.getPassword() == null) : this.password.equals(that.getPassword()))
           && ((this.baseContexts == null) ? (that.getBaseContexts() == null) : this.baseContexts.equals(that.getBaseContexts()))
           && ((this.baseContextsToSearch == null) ? (that.getBaseContextsToSearch() == null) : this.baseContextsToSearch.equals(that.getBaseContextsToSearch()))
           && ((this.userSearchFilter == null) ? (that.getUserSearchFilter() == null) : this.userSearchFilter.equals(that.getUserSearchFilter()))
           && ((this.attributesToGet == null) ? (that.getAttributesToGet() == null) : this.attributesToGet.equals(that.getAttributesToGet()))
           && ((this.groupSearchFilter == null) ? (that.getGroupSearchFilter() == null) : this.groupSearchFilter.equals(that.getGroupSearchFilter()))
           && ((this.useBlocks == null) ? (that.getUseBlocks() == null) : this.useBlocks.equals(that.getUseBlocks()))
           && ((this.groupMemberAttribute == null) ? (that.getGroupMemberAttribute() == null) : this.groupMemberAttribute.equals(that.getGroupMemberAttribute()))
           && ((this.blockSize == null) ? (that.getBlockSize() == null) : this.blockSize.equals(that.getBlockSize()))
           && ((this.usePagedResultControl == null) ? (that.getUsePagedResultControl() == null) : this.usePagedResultControl.equals(that.getUsePagedResultControl()))
           && ((this.vlvSortAttribute == null) ? (that.getVlvSortAttribute() == null) : this.vlvSortAttribute.equals(that.getVlvSortAttribute()))
           && ((this.uidAttribute == null) ? (that.getUidAttribute() == null) : this.uidAttribute.equals(that.getUidAttribute()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= (host == null) ? 0 : host.hashCode();
    h$ *= 1000003;
    h$ ^= (port == null) ? 0 : port.hashCode();
    h$ *= 1000003;
    h$ ^= (isSsl == null) ? 0 : isSsl.hashCode();
    h$ *= 1000003;
    h$ ^= (principal == null) ? 0 : principal.hashCode();
    h$ *= 1000003;
    h$ ^= (password == null) ? 0 : password.hashCode();
    h$ *= 1000003;
    h$ ^= (baseContexts == null) ? 0 : baseContexts.hashCode();
    h$ *= 1000003;
    h$ ^= (baseContextsToSearch == null) ? 0 : baseContextsToSearch.hashCode();
    h$ *= 1000003;
    h$ ^= (userSearchFilter == null) ? 0 : userSearchFilter.hashCode();
    h$ *= 1000003;
    h$ ^= (attributesToGet == null) ? 0 : attributesToGet.hashCode();
    h$ *= 1000003;
    h$ ^= (groupSearchFilter == null) ? 0 : groupSearchFilter.hashCode();
    h$ *= 1000003;
    h$ ^= (useBlocks == null) ? 0 : useBlocks.hashCode();
    h$ *= 1000003;
    h$ ^= (groupMemberAttribute == null) ? 0 : groupMemberAttribute.hashCode();
    h$ *= 1000003;
    h$ ^= (blockSize == null) ? 0 : blockSize.hashCode();
    h$ *= 1000003;
    h$ ^= (usePagedResultControl == null) ? 0 : usePagedResultControl.hashCode();
    h$ *= 1000003;
    h$ ^= (vlvSortAttribute == null) ? 0 : vlvSortAttribute.hashCode();
    h$ *= 1000003;
    h$ ^= (uidAttribute == null) ? 0 : uidAttribute.hashCode();
    return h$;
  }

  @Override
  LdapIO.LdapConfiguration.Builder builder() {
    return new Builder(this);
  }

  static final class Builder extends LdapIO.LdapConfiguration.Builder {
    private ValueProvider<String> host;
    private ValueProvider<Integer> port;
    private ValueProvider<Boolean> isSsl;
    private ValueProvider<String> principal;
    private ValueProvider<String> password;
    private ValueProvider<String> baseContexts;
    private ValueProvider<String> baseContextsToSearch;
    private ValueProvider<String> userSearchFilter;
    private ValueProvider<String> attributesToGet;
    private ValueProvider<String> groupSearchFilter;
    private ValueProvider<Boolean> useBlocks;
    private ValueProvider<String> groupMemberAttribute;
    private ValueProvider<Integer> blockSize;
    private ValueProvider<Boolean> usePagedResultControl;
    private ValueProvider<String> vlvSortAttribute;
    private ValueProvider<String> uidAttribute;
    Builder() {
    }
    private Builder(LdapIO.LdapConfiguration source) {
      this.host = source.getHost();
      this.port = source.getPort();
      this.isSsl = source.getIsSsl();
      this.principal = source.getPrincipal();
      this.password = source.getPassword();
      this.baseContexts = source.getBaseContexts();
      this.baseContextsToSearch = source.getBaseContextsToSearch();
      this.userSearchFilter = source.getUserSearchFilter();
      this.attributesToGet = source.getAttributesToGet();
      this.groupSearchFilter = source.getGroupSearchFilter();
      this.useBlocks = source.getUseBlocks();
      this.groupMemberAttribute = source.getGroupMemberAttribute();
      this.blockSize = source.getBlockSize();
      this.usePagedResultControl = source.getUsePagedResultControl();
      this.vlvSortAttribute = source.getVlvSortAttribute();
      this.uidAttribute = source.getUidAttribute();
    }
    @Override
    LdapIO.LdapConfiguration.Builder setHost(@Nullable ValueProvider<String> host) {
      this.host = host;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setPort(@Nullable ValueProvider<Integer> port) {
      this.port = port;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setIsSsl(@Nullable ValueProvider<Boolean> isSsl) {
      this.isSsl = isSsl;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setPrincipal(@Nullable ValueProvider<String> principal) {
      this.principal = principal;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setPassword(@Nullable ValueProvider<String> password) {
      this.password = password;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setBaseContexts(@Nullable ValueProvider<String> baseContexts) {
      this.baseContexts = baseContexts;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setBaseContextsToSearch(@Nullable ValueProvider<String> baseContextsToSearch) {
      this.baseContextsToSearch = baseContextsToSearch;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setUserSearchFilter(@Nullable ValueProvider<String> userSearchFilter) {
      this.userSearchFilter = userSearchFilter;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setAttributesToGet(@Nullable ValueProvider<String> attributesToGet) {
      this.attributesToGet = attributesToGet;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setGroupSearchFilter(@Nullable ValueProvider<String> groupSearchFilter) {
      this.groupSearchFilter = groupSearchFilter;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setUseBlocks(@Nullable ValueProvider<Boolean> useBlocks) {
      this.useBlocks = useBlocks;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setGroupMemberAttribute(@Nullable ValueProvider<String> groupMemberAttribute) {
      this.groupMemberAttribute = groupMemberAttribute;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setBlockSize(@Nullable ValueProvider<Integer> blockSize) {
      this.blockSize = blockSize;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setUsePagedResultControl(@Nullable ValueProvider<Boolean> usePagedResultControl) {
      this.usePagedResultControl = usePagedResultControl;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setVlvSortAttribute(@Nullable ValueProvider<String> vlvSortAttribute) {
      this.vlvSortAttribute = vlvSortAttribute;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration.Builder setUidAttribute(@Nullable ValueProvider<String> uidAttribute) {
      this.uidAttribute = uidAttribute;
      return this;
    }
    @Override
    LdapIO.LdapConfiguration build() {
      return new AutoValue_LdapIO_LdapConfiguration(
          this.host,
          this.port,
          this.isSsl,
          this.principal,
          this.password,
          this.baseContexts,
          this.baseContextsToSearch,
          this.userSearchFilter,
          this.attributesToGet,
          this.groupSearchFilter,
          this.useBlocks,
          this.groupMemberAttribute,
          this.blockSize,
          this.usePagedResultControl,
          this.vlvSortAttribute,
          this.uidAttribute);
    }
  }

}
