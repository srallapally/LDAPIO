package com.forgerock.connector.beam.ldap;

public class BasicLdapConfiguration {
    static final int DEFAULT_PORT = 389;

    public int getPort() {
        return port;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }
    public void setPrincipal(String principal) {
        this.principal = principal;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setBaseContexts(String[] baseContexts) {
        this.baseContexts = baseContexts;
    }
    public void setBaseContextsToSearch(String[] baseContextsToSearch) {
        this.baseContextsToSearch = baseContextsToSearch;
    }
    public void setUserSearchFilter(String userSearchFilter) {
        this.userSearchFilter = userSearchFilter;
    }
    public void setAttributesToGet(String attributesToGet) {
        this.attributesToGet = attributesToGet;
    }
    public void setGroupSearchFilter(String groupSearchFilter) {
        this.groupSearchFilter = groupSearchFilter;
    }
    public void setUseBlocks(boolean useBlocks) {
        this.useBlocks = useBlocks;
    }
    public void setGroupMemberAttribute(String groupMemberAttribute) {
        this.groupMemberAttribute = groupMemberAttribute;
    }
    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }
    public void setUsePagedResultControl(boolean usePagedResultControl) {
        this.usePagedResultControl = usePagedResultControl;
    }
    public void setVlvSortAttribute(String vlvSortAttribute) {
        this.vlvSortAttribute = vlvSortAttribute;
    }
    public void setUidAttribute(String uidAttribute) {
        this.uidAttribute = uidAttribute;
    }
    public String getHost() {
        return host;
    }
    public boolean isSsl() {
        return ssl;
    }
    public boolean isStartTLS() {
        return startTLS;
    }
    public void setStartTLS(boolean startTLS) {
        this.startTLS = startTLS;
    }
    public String getPrincipal() {
        return principal;
    }
    public String getPassword() {
        return password;
    }
    public String[] getBaseContexts() {
        return baseContexts;
    }
    public String[] getBaseContextsToSearch() {
        return baseContextsToSearch;
    }
    public String getUserSearchFilter() {
        return userSearchFilter;
    }
    public String getAttributesToGet() {
        return attributesToGet;
    }
    public String getGroupSearchFilter() {
        return groupSearchFilter;
    }
    public String getGroupMemberAttribute() {
        return groupMemberAttribute;
    }
    public int getBlockSize() {
        return blockSize;
    }
    public boolean isUsePagedResultControl() {
        return usePagedResultControl;
    }
    public String getVlvSortAttribute() {
        return vlvSortAttribute;
    }
    public String getUidAttribute() {
        return uidAttribute;
    }

    private int port = DEFAULT_PORT;
    private String host;
    private boolean ssl;
    private boolean startTLS = false;
    private String principal;
    private String password;
    private String[] baseContexts = { };
    private String[] baseContextsToSearch;
    private String userSearchFilter = null;
    private String attributesToGet = "*";
    private String groupSearchFilter = null;
    private String groupMemberAttribute = "uniqueMember";
    /**
     * Whether to use block-based LDAP controls like simple paged results or VLV control.
     */
    private boolean useBlocks = false;

    /**
     * The block size for simple paged results and VLV index searches.
     */
    private int blockSize = 100;

    /**
     * If true, simple paged search will be preferred over VLV index search
     * when both are available.
     */
    private boolean usePagedResultControl = false;

    /**
     * The attribute used as the sort key for the VLV index.
     */
    private String vlvSortAttribute = "uid";

    /**
     * The LDAP attribute to map Uid to.
     */
    private String uidAttribute = "entryUUID";
}
