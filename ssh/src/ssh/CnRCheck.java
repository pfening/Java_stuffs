package ssh;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class CnRCheck {
    private static int ok;
    private static int nok;

    public static void main(String[] args) throws NamingException, Exception {
        int ok =0;
        int nok = 0;
        
        String country = args[0];
        String site = args[1];

        AdCheck(country,site);
    }
        
          public static void LdapCheck(String sid) throws NamingException  {

            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://10.70.7.69");
            DirContext dctx = new InitialDirContext(env);

            String base = "ou=systemUser,ou=itim,ou=Sapa,o=Sapa";

            SearchControls sc = new SearchControls();
            String[] attributeFilter = { "eruid", "erlostpasswordanswer" };
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String filter = "(&(eruid="+ sid +"))";

            NamingEnumeration results = dctx.search(base, filter, sc);
            while (results.hasMore()) {
              SearchResult sr = (SearchResult) results.next();
              Attributes ldapattrs = sr.getAttributes();
              
              String[] id = ldapattrs.get("eruid").toString().split("[: ]");
                if (ldapattrs.get("erlostpasswordanswer")!=null){
                    System.out.println("For " + id[2] + " C&R is OK");
                    ok++;
                }
                else {System.out.println("For " + id[2] + " C&R is NOT OK");
                nok++;
                }
           }
            dctx.close();

          }
        
      public static void AdCheck(String country, String site) throws NamingException  {

            Hashtable env = new Hashtable();

            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

            env.put(Context.PROVIDER_URL, "ldap://10.70.7.11:389");
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "CN=service_tim,OU=ServiceAccounts,OU=IBM,DC=global,DC=to");
            env.put(Context.SECURITY_CREDENTIALS, "Sapa4ever");

            DirContext dctx = new InitialDirContext(env);

            String base = "OU=Users,OU=" + site +",OU="+ country +",OU=Sites,DC=global,DC=to";

            SearchControls sc = new SearchControls();
            String[] attributeFilter = { "sAMAccountName" };
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String filter = "(&(sAMAccountName=*))";

            NamingEnumeration results = dctx.search(base, filter, sc);

            while (results.hasMore()) {
              SearchResult sr = (SearchResult) results.next();
              Attributes adattrs = sr.getAttributes();

                String[] id = adattrs.get("sAMAccountName").toString().split("[: ]");
		LdapCheck(id[2]);
            }
            dctx.close();
            System.out.println("Ok: "+ok +" Nok: "+ nok);
  }
}
