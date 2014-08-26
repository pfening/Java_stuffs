package itim;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;

public class Report {
  public static void main(String[] args) throws Exception {
    Hashtable env = new Hashtable();

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

    env.put(Context.PROVIDER_URL, "ldap://10.70.7.11:389");
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, "CN=service_tim,OU=ServiceAccounts,OU=IBM,DC=global,DC=to");
    env.put(Context.SECURITY_CREDENTIALS, "Sapa4ever");

    DirContext dctx = new InitialDirContext(env);

    String base = "OU=Sites,DC=global,DC=to";

    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "distinguishedName" };
    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "ou=Users";

    NamingEnumeration results = dctx.search(base, filter, sc);
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();

      String[] addn=attrs.get("distinguishedName").toString().split("[: ]");
      System.out.println(addn[2]);
    }
    dctx.close();
  }
}
