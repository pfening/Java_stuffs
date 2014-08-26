package ssh;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;

public class Orphans {

    public static void main(String[] args) throws NamingException, Exception {
        LdapCheck();
    }
        
          public static void LdapCheck() throws NamingException  {

            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://10.70.7.69");
            DirContext dctx = new InitialDirContext(env);

            String base = "ou=orphans,erglobalid=00000000000000000000,ou=Sapa,o=Sapa";

            SearchControls sc = new SearchControls();
            String[] attributeFilter = { "eraddisplayname", "eraddistinguishedname", "eruid", "mail", "cn", "description", "telephonenumber", "title"};
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String filter = "(&(eruid=*)(eradcontainer=ou=users,*))";

            NamingEnumeration results = dctx.search(base, filter, sc);
            while (results.hasMore()) {
              SearchResult sr = (SearchResult) results.next();
              Attributes ldapattrs = sr.getAttributes();
                System.out.println("ITIMLDAP############################################");
                System.out.println(ldapattrs.get("eraddisplayname"));
                System.out.println(ldapattrs.get("cn"));
                System.out.println(ldapattrs.get("title"));
                System.out.println(ldapattrs.get("description"));
                System.out.println(ldapattrs.get("eruid"));
                System.out.println(ldapattrs.get("telephonenumber"));
                System.out.println(ldapattrs.get("mail"));
                System.out.println(ldapattrs.get("eraddistinguishedname"));
                String[] id = ldapattrs.get("eruid").toString().split("[: ]");
		AdCheck(id[2]);
              
           }
            dctx.close();

          }
        
      public static void AdCheck(String sid) throws NamingException  {

            System.out.println(sid);

            Hashtable env = new Hashtable();

            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

            env.put(Context.PROVIDER_URL, "ldap://10.70.7.11:389");
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "CN=service_tim,OU=ServiceAccounts,OU=IBM,DC=global,DC=to");
            env.put(Context.SECURITY_CREDENTIALS, "Sapa4ever");

            DirContext dctx = new InitialDirContext(env);

            String base = "ou=sites,DC=global,DC=to";

            SearchControls sc = new SearchControls();
            String[] attributeFilter = { "sn", "co", "c", "cn", "l", "givenName", "sAMAccountName", "mail", "countryCode", "description" };
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String filter = "(&(sAMAccountName=" + sid + "))";

            NamingEnumeration results = dctx.search(base, filter, sc);

            while (results.hasMore()) {
              SearchResult sr = (SearchResult) results.next();
              Attributes adattrs = sr.getAttributes();
                System.out.println("AD################################################");
		System.out.println(adattrs.get("sn"));
                System.out.println(adattrs.get("givenName"));
                System.out.println(adattrs.get("sAMAccountName"));
                System.out.println(adattrs.get("cn"));
                System.out.println(adattrs.get("co"));
                System.out.println(adattrs.get("c"));
                System.out.println(adattrs.get("l"));   
                System.out.println(adattrs.get("mail"));
                System.out.println(adattrs.get("countryCode"));
                System.out.println(adattrs.get("description"));
		System.out.println("END###############################################");
            }
            dctx.close();
  }
}
