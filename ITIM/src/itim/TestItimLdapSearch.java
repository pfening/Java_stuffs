package itim;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;


public class TestItimLdapSearch {
  public static void main(String[] args) throws Exception {
    String sid = "pfg";
    Hashtable env = new Hashtable();

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, "ldap://192.168.122.93");
    
    DirContext dctx = new InitialDirContext(env);

    String base = "";

    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "eruid","erpassword", "eraccountstatus","erchangepswdrequired" };
    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "(&(eruid="+ sid +"))";

    NamingEnumeration results = dctx.search(base, filter, sc);
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();

      System.out.println(attrs.get("eruid"));
      System.out.println(attrs.get("mail"));
      System.out.println(attrs.get("erglobalid"));
      System.out.println(attrs.get("erpassword"));
      System.out.println(attrs.get("eraccountstatus"));
      System.out.println(attrs.get("erchangepswdrequired"));
      
    }
    dctx.close();
  }
}

