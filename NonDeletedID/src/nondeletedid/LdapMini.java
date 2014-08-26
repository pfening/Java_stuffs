package nondeletedid;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;

public class LdapMini {
  private static String ldapurl="ldap://itimldap";
          
  public static void main(String[] args) throws Exception {
    String service= args[0];

    Hashtable env = new Hashtable();

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

    env.put(Context.PROVIDER_URL, ldapurl);

    DirContext dctx = new InitialDirContext(env);

    String base = "dc=itim";

    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "objectclass", "erglobalid" };

    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "(&(erservicename=" + service + "))";

    NamingEnumeration results = dctx.search(base, filter, sc);
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();
      
      String obj=(attrs.get("objectclass").toString().split("[,]"))[1];
  
      String gid=(attrs.get("erglobalid").toString().split("[,]"))[1];
      System.out.println(service +";"+ gid +";"+ obj);

    }
    dctx.close();
  }
}

