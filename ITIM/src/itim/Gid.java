package itim;


import java.util.Hashtable;
import java.util.Random;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;


public class Gid {
    
    private static String rand(){
    String erGlobalIDPrefix="560702127337376776"; 
    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(9);  
    String rnd=erGlobalIDPrefix + String.format("%01d", randomInt);
    return rnd;
    }  
    
  public static void main(String[] args) throws Exception {

    String erGlobalID=rand();
    
    System.out.println("erGlobalID: " + erGlobalID);
    
    Hashtable env = new Hashtable();

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, "ldap://192.168.122.92");
    
    DirContext dctx = new InitialDirContext(env);

    String base = "ou=org,dc=com";

    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "erglobalid" };
    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "erglobalid=" + erGlobalID;

    NamingEnumeration results = dctx.search(base, filter, sc);
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();
     String gid= attrs.get("erglobalid").toString();
     System.out.println("gid: " + gid);
     if (gid !=null){
         System.out.println("egyezik");
         System.out.println("new: "+ rand());
     }
     else if (gid == null){
         System.out.println("nem egyezik");
     }  

    }

    dctx.close();
  }

  }

