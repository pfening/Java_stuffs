package itim;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;


public class ItimAccountLdapSearch {
  public static void main(String[] args) throws Exception {
    String sid = "pfg";
    Hashtable env = new Hashtable();

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, "ldap://192.168.122.92");
    
    DirContext dctx = new InitialDirContext(env);

    String base = "";

    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "uid","mail","erglobalid" };
    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "(&(uid="+ sid +"))";

    NamingEnumeration results = dctx.search(base, filter, sc);
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();

      System.out.println(attrs.get("uid"));
      System.out.println(attrs.get("mail"));
      System.out.println(attrs.get("erglobalid"));

      
    }
    dctx.close();
  }


}

