
package uid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class Uid {
    private static String cn;
    private static String hr;
    private static String sid;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        try
        {
        String strFile = "/home/gabor/ITIM4U/uid.csv";
        BufferedReader br = new BufferedReader( new FileReader(strFile));
        String strLine = "";
        while( (strLine = br.readLine()) != null)			
        {
        String[] st = strLine.split(",",1);
        System.out.println();
        System.out.print(st[0] +";");
        
    Hashtable env = new Hashtable();

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

    env.put(Context.PROVIDER_URL, "ldap://bluepages.ibm.com");

    DirContext dctx = new InitialDirContext(env);

    String base = "";

    SearchControls sc = new SearchControls();
    String[] attributeFilter = {  "cn","uid","employeetype" };
    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
    sid=st[0];
    String filter = "(&(uid="+ sid +"))";

    NamingEnumeration results = dctx.search(base, filter, sc);
  
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();
      
      if (attrs.get("cn").get().toString().isEmpty()){
         cn="no value";
      }else{
        cn = attrs.get("cn").get().toString();   
      }

      if (attrs.get("employeetype").get().toString().isEmpty()){
         hr="no value";
      }else{
          hr = attrs.get("employeetype").get().toString();
      }

      System.out.print(cn+";"+hr);
      //System.out.println();
    }
    dctx.close();
    
        }
        }
        catch(Exception e)
        {
        System.out.println("Exception happend: " + e);                  
        }
}
}
