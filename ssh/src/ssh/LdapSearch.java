package ssh;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;


public class LdapSearch {
  public static void main(String[] args) throws Exception {
    String sid = "pfening";
    Hashtable env = new Hashtable();

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

    env.put(Context.PROVIDER_URL, "ldap://bluepages.ibm.com");
    
    DirContext dctx = new InitialDirContext(env);

    String base = "";

    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "uid" };
    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "(&(sn="+ sid +"))";
    System.out.println(filter);
    NamingEnumeration results = dctx.search(base, filter, sc);
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();

      System.out.println(attrs);
      
      //Attribute attr = attrs.get("uid");
      //Attribute atdn = attrs.get("hrOrgUnitCode");
      //System.out.println(atdn);
      //String dn = atdn.toString();
      //System.out.println(dn);
      //System.out.println(attrs.getIDs());
      //System.out.println(attrs.get("cn") + " ");
      //System.out.println(attrs.get("mail") + " ");
      
      //Attribute attr = attrs.get("uid");
      //System.out.print(attr.get() + ": ");
      //attr = attrs.get("mail");
      //System.out.println(attr.get());
    }
    dctx.close();
  }

    private static String String(Attribute get) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
