
package itim;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;

public class Search2 {
    private static String acce;

  public static void main(String[] args) throws Exception {
        Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://10.70.7.69");
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "cn=root");
            env.put(Context.SECURITY_CREDENTIALS, "secret");
            DirContext dctx = new InitialDirContext(env);

            String base = "ou=systemUser,ou=itim,ou=Sapa,o=Sapa";

            SearchControls sc = new SearchControls();
            String[] attributeFilter = { "eruid", "erlastaccessdate" };
            sc.setReturningAttributes(attributeFilter);
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String filter = "(&(eruid=*))";

            NamingEnumeration results = dctx.search(base, filter, sc);
            while (results.hasMore()) {
              SearchResult sr = (SearchResult) results.next();
              Attributes ldapattrs = sr.getAttributes();

              String[] id = ldapattrs.get("eruid").toString().split("[: ]");

            if (ldapattrs.get("erlastaccessdate")==null){
                acce="Never accessed";
                }
                else {
                  String[] acc = ldapattrs.get("erlastaccessdate").toString().split("[: ]");
                  acce = acc[2];
                }
            System.out.println(id[2] +  acce );
                

   dctx.close();
  }

}
}