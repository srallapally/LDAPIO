package com.forgerock.connector.beam.ldap;

import com.forgerock.connector.beam.utils.Entity;
import org.apache.beam.sdk.coders.AvroCoder;
import org.apache.beam.sdk.coders.DefaultCoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Collections.unmodifiableSet;

@DefaultCoder(AvroCoder.class)
public class LdapManager implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(LdapManager.class);
    private static final String LDAP_CTX_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
    public static final String SASL_GSSAPI = "SASL-GSSAPI";
    private final BasicLdapConfiguration config;
    private LdapContext initCtx;
    private Set<String> supportedControls;

    public LdapManager(BasicLdapConfiguration config){
        this.config = config;
    }
    private Hashtable getDefaultContextEnv(){
        final Hashtable env = new Hashtable(11);

        env.put(Context.INITIAL_CONTEXT_FACTORY, LDAP_CTX_FACTORY);
        env.put(Context.PROVIDER_URL, getLdapUrls());
        env.put(Context.REFERRAL, "follow");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        if (config.isSsl()) {
            env.put(Context.SECURITY_PROTOCOL, "ssl");
        }
        return env;
    }

    private LdapContext getAnonymousContext() throws NamingException {
        InitialLdapContext ctx = null;
        return new InitialLdapContext(getDefaultContextEnv(), null);
    }

    public LdapContext getInitialContext() {
        if (initCtx != null) {
            return initCtx;
        }
        initCtx = createContext(config.getPrincipal(), config.getPassword());
        System.out.println(this.getClass().getSimpleName()+":getInitialContext():I am here");
        return initCtx;
    }

    private LdapContext createContext(String principal, String credentials) {
        final LdapContext result = null;

        final Hashtable<Object, Object> env = getDefaultContextEnv();
        String authentication = "simple";
        env.put(Context.SECURITY_AUTHENTICATION, authentication);

        if (isNotBlank(principal)) {
            env.put(Context.SECURITY_PRINCIPAL, principal);
        }
        if(isNotBlank(credentials)) {
            env.put(Context.SECURITY_CREDENTIALS, credentials);
        }
        return createContext(env);
    }

    private LdapContext createContext(Hashtable<?, ?> env) {
        InitialLdapContext context = null;
        try {
            context = new InitialLdapContext(env, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return context;
    }

    private String getLdapUrls() {
        StringBuilder builder = new StringBuilder();
        builder.append("ldap://");
        builder.append(config.getHost());
        builder.append(':');
        builder.append(config.getPort());
        System.out.println( builder.toString());
        return builder.toString();
    }

    public void checkAlive() throws Exception {
        Attributes attrs = getInitialContext().getAttributes("", new String[]{"subschemaSubentry"});
        attrs.get("subschemaSubentry");
    }

    public void login(){
        try {
            initCtx = this.getInitialContext();
        } catch(Exception e){
        }
    }
    public ArrayList<Entity> executeSearch(){
        ArrayList<Entity> persons = new ArrayList<>();
        try {
            //LdapContext ctx = new InitialLdapContext(env, null);
            login();
            LdapContext ctx = this.initCtx;
            if(ctx == null) {
                System.out.println("WHOOOOAAA");
            }
            byte[] cookie = null;
            String sortKey = null;
            SortControl sortControl = null;

            if(config.isUsePagedResultControl()) {
                if (!supportsControl(PagedResultsControl.OID)) {
                    System.out.println("PagedResultsControl is not supported");
                } else {
                    System.out.println("PagedResultsControl is supported "+config.getUserSearchFilter()+":"+config.getBaseContexts()[0]+"::"+config.getAttributesToGet());
                    ctx.setRequestControls(new Control[]{new PagedResultsControl(this.config.getBlockSize(), Control.NONCRITICAL)});
                    SearchControls ctrls = new SearchControls();
                    ctrls.setReturningAttributes(config.getAttributesToGet().split(","));
                    ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
                    ctrls.setDerefLinkFlag(true);
                    ctrls.setReturningObjFlag(false);
                    ctrls.setTimeLimit(0);
                    sortControl = new SortControl(sortKey, Control.NONCRITICAL);
                    do {
                        /* perform the search */
                        NamingEnumeration results = ctx.search((config.getBaseContexts())[0], config.getUserSearchFilter(), ctrls);

                        /* for each entry print out name + all attrs and values */
                        while (results != null && results.hasMore()) {
                            SearchResult entry = (SearchResult) results.next();
                            System.out.print(entry.getName());
                            Entity p = new Entity();
                            p.set_id(entry.getName());
                            Attributes attrs = entry.getAttributes();
                            for (NamingEnumeration<?> enumer = attrs.getAll(); enumer.hasMore(); ) {
                                Attribute attrib = (Attribute) enumer.next();
                                for (NamingEnumeration<?> e = attrib.getAll(); e.hasMore(); ) {
                                    Object value = e.next();
                                    p.addAttribute(attrib.getID(),(String)value);
                                    System.out.print(":" + attrib.getID() + ":" + (String) value);
                                }
                            }
                            persons.add(p);
                            //System.out.println("-");
                        }

                        // Examine the paged results control response
                        Control[] controls = ctx.getResponseControls();
                        if (controls != null) {
                            for (int i = 0; i < controls.length; i++) {
                                if (controls[i] instanceof PagedResultsResponseControl) {
                                    PagedResultsResponseControl prrc = (PagedResultsResponseControl) controls[i];
                                    /*
                                    if (total != 0) {
                                        System.out.println("***************** END-OF-PAGE "
                                                + "(total : " + total + ") *****************\n");
                                    } else {
                                        System.out.println("***************** END-OF-PAGE "
                                                + "(total: unknown) ***************\n");
                                    }
                                     */
                                    cookie = prrc.getCookie();
                                }
                            }
                        } else {
                            System.out.println("No controls were sent from the server");
                        }
                        // Re-activate paged results
                        ctx.setRequestControls(new Control[]{new PagedResultsControl(config.getBlockSize(), cookie, Control.NONCRITICAL)});
                    } while (cookie != null);
                }
            }
            ctx.close();
        } catch (NamingException e) {
            System.err.println("PagedSearch failed.");
            e.printStackTrace();
        } catch (IOException ie) {
            System.err.println("PagedSearch failed.");
            ie.printStackTrace();
        }

        System.out.println("There are "+persons.size() +" ldap entries");
        return persons;
    }
    /**
     * Returns {@code} true if the control with the given OID is supported by
     * the server.
     */
    public boolean supportsControl(String oid) {
        return getSupportedControls().contains(oid);
    }

    private Set<String> getSupportedControls() {
        if (supportedControls == null) {
            try {
                Attributes attrs = getInitialContext().getAttributes("", new String[]{"supportedControl"});
                try {
                    supportedControls = unmodifiableSet(LdapUtils.getStringAttrValues(attrs, "supportedControl"));
                    System.out.println(supportedControls.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (NamingException e) {
                LOG.error("Exception while retrieving the supported controls");
                supportedControls = emptySet();
            }
        }
        return supportedControls;
    }
    public void close() {
        try {
            quietClose(initCtx);
        } finally {
            initCtx = null;
        }
    }

    private static void quietClose(LdapContext ctx) {
        try {
            if (ctx != null) {
                ctx.close();
            }
        } catch (NamingException e) {
            LOG.warn(e.getMessage());
        }
    }

    private static boolean isNotBlank(String val) {
        return !isBlank(val);
    }
    private static boolean isBlank(final String val) {
        return (val == null) ? true : isEmpty(val.trim());
    }
    private static boolean isEmpty(final String val) {
        return (val == null) ? true : "".equals(val) ? true : false;
    }

}
