package uid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class BPQuery {
    private static String uid;
    public static String cn=null;
    public static String empstat=null;

  public static void main(String[] args) throws Exception {
        String strFile = args[0];
        
        FileWriter fw = new FileWriter("/home/gabor/ITIM4U/bp.csv");
        PrintWriter pw = new PrintWriter(fw);
        
        BufferedReader br = new BufferedReader( new FileReader(strFile));
        String strLine = "";
        while( (strLine = br.readLine()) != null){
        String[] st = strLine.split(";");
        uid = st[0];


    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, "ldap://bluepages.ibm.com");
    
    DirContext dctx = new InitialDirContext(env);

    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "cn","uid","employeetype" };

    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "(&(uid=" + uid + "))";

    NamingEnumeration results = dctx.search("", filter, sc);
        
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();

      cn=attrs.get("cn").get().toString();
      empstat=attrs.get("employeetype").get().toString();
	
  	}
    	dctx.close();
        System.out.println(uid+";"+cn+";"+empstat);
        pw.println(uid+";"+cn+";"+empstat);
	cn="no name";
	empstat="not in BP";
	pw.flush();
        }
        
	        
        pw.close();
        fw.close(); 

  }
}