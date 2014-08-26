import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;

public class ItimLdap {
  private static String ldapurl="ldap://itimldap";
          
    private static int countFunction(String fil, String attriFilter) throws NamingException{
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, ldapurl);
    DirContext dctx = new InitialDirContext(env);

    String[] attrib = new String[] {attriFilter};
    SearchControls sc = new SearchControls();
    sc.setReturningAttributes(attrib);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);  
    NamingEnumeration rs = dctx.search("", fil, sc);
    int count = 0;
    while (rs.hasMore()) {
      SearchResult sr = (SearchResult) rs.next();
      Attributes attrs = sr.getAttributes();
      ++count;
    }
      return count;
    }
    
    private static String searchFunction(String fil, String attriFilter) throws NamingException{
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, ldapurl);
    DirContext dctx = new InitialDirContext(env);

    String base = "";
    String[] attrib = new String[] {attriFilter};
    SearchControls sc = new SearchControls();
    sc.setReturningAttributes(attrib);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);  
    NamingEnumeration rs = dctx.search(base, fil, sc);
      while (rs.hasMore()) {
      SearchResult sr = (SearchResult) rs.next();
      Attributes attrs = sr.getAttributes();
      
      if (attrs.get(attriFilter)!=null){
          String at = (attrs.get(attriFilter).toString().split("[ ]"))[1];
          return at;
      }else{
          String at = "no value";
          return at;
      }

    }
        return null;
    }

  public static void main(String[] args) throws Exception {
    String service= args[0];

    Hashtable env = new Hashtable();

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

    env.put(Context.PROVIDER_URL, ldapurl);

    DirContext dctx = new InitialDirContext(env);

    String base = "dc=itim";

    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "erurl", "owner", "erposixpkfile", "objectclass", "erglobalid" };

    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "(&(erservicename=" + service + "))";

    NamingEnumeration results = dctx.search(base, filter, sc);
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();

      if (attrs.get("erurl")!=null){
      String url=(attrs.get("erurl").toString().split("[ ]"))[1];
      System.out.println("url: " + url);
      }else{
          String url = "no value";
          System.out.println("key file: " + url);
      }
      
      String ow=(attrs.get("owner").toString().split("[, ]"))[1];
      //System.out.println("owner globid: " + ow);
      
      String mel = searchFunction("(&(" + ow + "))","mail");
      System.out.println("Mail: " + mel);

      
      if (attrs.get("erposixpkfile")!=null){
          String prx=(attrs.get("erposixpkfile").toString().split("[ ]"))[1];
          System.out.println("key file: " + prx);
      }else{
          String prx = "no value";
          System.out.println("key file: " + prx);
      }
 
      
      String obj=(attrs.get("objectclass").toString().split("[,]"))[1];
      System.out.println("type: " + obj);
      
            
      String sdn = searchFunction("(&(objectClass=erServiceItem)(erservicename=" + service + "))", "erglobalid");
      //System.out.println("service globid: " + sdn);
      
      int users=countFunction("(&(erservice=erglobalid="+ sdn +",ou=services,erglobalid=00000000000000000000,ou=IBM,dc=itim))","eruid");
      System.out.println("users: " + users);
      
      String orf = searchFunction("uid=" + service + "_orphan", "erglobalid");
      //System.out.println("orphan globid: " + orf);
      
      int orphans=countFunction("(&(erservice=erglobalid="+ sdn +"*)(objectclass=erAccountItem)(owner=erglobalid="+ orf +"*))","eruid");
      System.out.println("orphans: " + orphans);    
      
    }
    dctx.close();
  }
}

