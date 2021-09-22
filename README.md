# LDAPIO
Beam IO for Ldap
IO that reads user entries from Ldap. Tested with OpenDJ and Active Directory. Supports paged search.

Known Issues
1. It doesn't do parallel reads
2. Doesn't support a streamable read (would be nice if the IO could read a page at a time)

//TODO
1. Implement vlv index based search strategy (current
2. Search for Groups
3. Search for group memberships as part of user search
4. Implement BoundedSource
