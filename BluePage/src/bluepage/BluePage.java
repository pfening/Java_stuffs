package bluepage;

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

public class BluePage {
    
    public static String hd=System.getProperty("user.home");
    
    private static String uid;
    public static String cn; 
    public static String notesEmail;
    public static String notesId;
    public static String emailAddress;
    public static String sn;
    public static String ibmSerialNumber;
    public static String employeeType;
    
    
  public static void main(String[] args) throws Exception {
        String strFile = args[0];
        
        FileWriter fw = new FileWriter(hd+"/bpextract.csv");
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
    String[] attributeFilter = { "cn","employeetype","notesEmail","notesId","emailAddress","sn","ibmSerialNumber" };

    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "(&(emailAddress=" + uid + "))";

    NamingEnumeration results = dctx.search("", filter, sc);
        
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();

        cn=attrs.get("cn").get().toString();      
        notesEmail=attrs.get("notesemail").get().toString();
        notesId=attrs.get("notesid").get().toString();
        emailAddress=attrs.get("emailaddress").get().toString();
        sn=attrs.get("sn").get().toString();
        ibmSerialNumber=attrs.get("ibmserialnumber").get().toString();
        employeeType=attrs.get("employeetype").get().toString();
	
  	}
    	dctx.close();
        System.out.println(ibmSerialNumber+";"+cn+";"+sn+";"+notesEmail+";"+notesId+";"+emailAddress+";"+employeeType);
        pw.println(ibmSerialNumber+";"+cn+";"+sn+";"+notesEmail+";"+notesId+";"+emailAddress+";"+employeeType);
	cn="no name";
	employeeType="not in BP";
	pw.flush();
        }
        
	        
        pw.close();
        fw.close(); 

  }
}