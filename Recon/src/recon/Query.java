package recon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Query {

 private static String ldapurl="ldap://192.168.122.92";
 private static String dburl="jdbc:db2://192.168.122.92:50000";
 private static String ldapuser="cn=root";
 private static String ldappass="password";
 private static String db="itimdb";
 private static String dbuser="db2inst1";
 private static String dbpass="password";
 private static String service;
 private static String msg;
    
    public static String qerror(String sql,Statement stmt) throws SQLException{
     
     ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            if (rs.getString("SHORT_DETAIL")!=null){
            String str=rs.getString("SHORT_DETAIL");
            String stra=str.replaceAll("\n", " ");
            msg = (stra.substring(str.indexOf("tokenValue")).split("[\\<\\>\\=\\/]+"))[1];
             return msg;
            }else{
            msg = "no value";
            return msg;
        }  
        }
        rs.close();
     return null;
 }
    
    public static String qrecon(String sql,Statement stmt) throws SQLException{
     
     ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
        return rs.getString("STARTED");
        }
        rs.close();
     return null;
 }
    
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

    String[] attrib = new String[] {attriFilter};
    SearchControls sc = new SearchControls();
    sc.setReturningAttributes(attrib);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);  
    NamingEnumeration rs = dctx.search("", fil, sc);
    
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
    //String service = "Win Local Service";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String today = df.format(cal.getTime());

        cal.add(Calendar.DATE, -2);
        String yesterday = df.format(cal.getTime());      
    
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    
        Document doc = docBuilder.newDocument();
	Element rootElement = doc.createElement("datastream");
	doc.appendChild(rootElement);

        String strFile = "/home/gabor/ITIM4U/test.csv";

        BufferedReader br = new BufferedReader( new FileReader(strFile));
        String strLine = "";
        while( (strLine = br.readLine()) != null)			
        {
        String[] st = strLine.split(";");
        String service = st[0];
        System.out.println("Servicename: " + service );

    //LDAP part begin
    Hashtable env = new Hashtable();

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, ldapurl);
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, ldapuser);
    env.put(Context.SECURITY_CREDENTIALS, ldappass);
    
    DirContext dctx = new InitialDirContext(env);

    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "erurl", "owner", "erposixpkfile", "objectclass", "erglobalid" };

    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "(&(erservicename=" + service + "))";

    NamingEnumeration results = dctx.search("", filter, sc);

        Element object = doc.createElement("service");
	rootElement.appendChild(object);       
        
        Attr attr = doc.createAttribute("name");
	object.setAttribute("name",""+ service +"");
        
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();

      Element xurl = doc.createElement("erurl");
      if (attrs.get("erurl")!=null){
      String url=(attrs.get("erurl").toString().split("[ ]"))[1];
      System.out.println("url: " + url);
            xurl.appendChild(doc.createTextNode(url));
            object.appendChild(xurl);
      }else{
          String url = "no value";
          System.out.println("key file: " + url);
            xurl.appendChild(doc.createTextNode(url));
            object.appendChild(xurl);
      }
      
      String ow=(attrs.get("owner").toString().split("[, ]"))[1];
      //System.out.println("owner globid: " + ow);
      
      Element xmail = doc.createElement("mail");
      String mel = searchFunction("(&(" + ow + "))","mail");
      System.out.println("Mail: " + mel);
            xmail.appendChild(doc.createTextNode(mel));
            object.appendChild(xmail);
      
            
      Element xprx = doc.createElement("erposixpkfile"); 
      if (attrs.get("erposixpkfile")!=null){
          String prx=(attrs.get("erposixpkfile").toString().split("[ ]"))[1];
          System.out.println("key file: " + prx);
            xprx.appendChild(doc.createTextNode(prx));
            object.appendChild(xprx);
      }else{
          String prx = "no value";
          System.out.println("key file: " + prx);
            xprx.appendChild(doc.createTextNode(prx));
            object.appendChild(xprx);          
      }
 
      Element xobj = doc.createElement("objectclass");
      String obj=(attrs.get("objectclass").toString().split("[, ]"))[3];
      System.out.println("type: " + obj);
            xobj.appendChild(doc.createTextNode(obj));
            object.appendChild(xobj); 
            
      String sdn = searchFunction("(&(objectClass=erServiceItem)(erservicename=" + service + "))", "erglobalid");
      //System.out.println("service globid: " + sdn);
      
      Element xusers = doc.createElement("users");
      int usr=countFunction("(&(erservice=erglobalid="+ sdn +"*))","eruid");
      String users = Integer.toString(usr);
      System.out.println("users: " + users);
            xusers.appendChild(doc.createTextNode(users));
            object.appendChild(xusers); 
      
      String orf = searchFunction("uid=" + service + "_orphan", "erglobalid");
      //System.out.println("orphan globid: " + orf);
      
      Element xorphans = doc.createElement("orphans");
      int orp=countFunction("(&(erservice=erglobalid="+ sdn +"*)(objectclass=erAccountItem)(owner=erglobalid="+ orf +"*))","eruid");
      String orphans = Integer.toString(orp);
      System.out.println("orphans: " + orphans);  
            xorphans.appendChild(doc.createTextNode(orphans));
            object.appendChild(xorphans); 
      
            
      Element xcbn = doc.createElement("cbn");
      String cbn = searchFunction("(&(erPolicyItemName=CBN-account-*)(erpolicytarget=*"+ sdn +"*))","erpolicyitemname");
      if (cbn!=null){ 
      System.out.println("cbn: " + cbn);  
            xcbn.appendChild(doc.createTextNode(cbn));
            object.appendChild(xcbn); 
      }else{
            System.out.println("cbn: no value");  
            xcbn.appendChild(doc.createTextNode("no value"));
            object.appendChild(xcbn);
      }
      
            
      Element xprivcbn = doc.createElement("privcbn"); 
      String privcbn = searchFunction("(&(erPolicyItemName=CBN-Access-*)(erpolicytarget=*"+ sdn +"*))","erpolicyitemname");
      if (privcbn!=null){ 
      System.out.println("privcbn: " + privcbn);  
            xprivcbn.appendChild(doc.createTextNode(privcbn));
            object.appendChild(xprivcbn);
      }else{
            System.out.println("privcbn: no value");
            xprivcbn.appendChild(doc.createTextNode("no value"));
            object.appendChild(xprivcbn);
      }
    }
    dctx.close();
    //LDAP part end
    
    //DB2 part begin
     
        Class.forName("com.ibm.db2.jcc.DB2Driver");
        String url =dburl + "/" + db;
        
        Connection conn = DriverManager.getConnection(url, dbuser, dbpass); 
        Statement stmt = conn.createStatement();
        
        String lasterror = "SELECT SHORT_DETAIL FROM ITIMUSER.PROCESS WHERE SUBJECT_SERVICE = '" + service + "' AND RESULT_SUMMARY = 'SF' AND TYPE='RC' and STARTED between ('" + yesterday + "') and ('" + today + "') ";
        String lastrecon = "select subject_service, max(started) as started from ITIMUSER.PROCESS where subject_service in '" + service + "' and result_summary='SF' group by subject_service";
        
        Element xerror = doc.createElement("errormsg");
        String errmsg = qerror(lasterror,stmt);
        System.out.println(errmsg);
        xerror.appendChild(doc.createTextNode(errmsg));
        object.appendChild(xerror);
    
        Element xrecon = doc.createElement("lastrecon");
        String recmsg = qrecon(lastrecon,stmt);
        System.out.println(recmsg);
        xrecon.appendChild(doc.createTextNode(recmsg));
        object.appendChild(xrecon);
        
        conn.close();
        //DB2 part end
        }
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(new File("/home/gabor/NetBeansProjects/Recon/src/recon/service.xml"));
    transformer.transform(source, result);

  }
}