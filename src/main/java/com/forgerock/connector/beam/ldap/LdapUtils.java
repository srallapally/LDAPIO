package com.forgerock.connector.beam.ldap;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import java.util.HashSet;
import java.util.Set;

public class LdapUtils {
    /**
     * Return the value of the {@code ldapAttrName} parameter cast to a String.
     */
    public static String getStringAttrValue(Attributes ldapAttrs, String ldapAttrName) throws Exception {
        Attribute attr = ldapAttrs.get(ldapAttrName);
        if (attr != null) {
            return (String) attr.get();
        }
        return null;
    }

    /**
     * Return the <b>case insensitive</b> set of values of the {@code
     * ldapAttrName} parameter cast to a String.
     */
    public static Set<String> getStringAttrValues(Attributes ldapAttrs, String ldapAttrName) throws Exception {
        Set<String> result = new HashSet<String>();
        addStringAttrValues(ldapAttrs, ldapAttrName, result);
        return result;
    }

    public static void addStringAttrValues(Attributes ldapAttrs, String ldapAttrName, Set<String> toSet)
    throws Exception {
        Attribute attr = ldapAttrs.get(ldapAttrName);
        if (attr == null) {
            return;
        }
        NamingEnumeration<?> attrEnum = attr.getAll();
        while (attrEnum.hasMore()) {
            toSet.add((String) attrEnum.next());
        }
    }
}
