package itim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class IQuery {

 private static String ldapurl="ldap://itimldap";
 private static String ldapuser="cn=root";
 private static String ldappass="Itim83De";
 
 private static String dburl="jdbc:db2://itimdb2:50000";
 private static String db="itimdb";
 private static String dbuser="itimdb";
 private static String dbpass="Itim83De";

    private static String service;
    private static String url;
    private static String ow;
    private static String mel;
    private static String prx;
    private static String obj;
    private static String recmsg;
    private static String recdtl;
    
    public static String qrecon(String sql,Statement stmt) throws SQLException{
     
     ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
        return rs.getString("STARTED");
        }
        rs.close();
     return null;
 }
        
  public static String qerror(String sqla,Statement stmt) throws SQLException{
     
     ResultSet rs = stmt.executeQuery(sqla);
        while (rs.next()){
            String str=rs.getString("SHORT_DETAIL");
            return str;
        }
        rs.close();
     return null;
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
        String service= args[0];

    //LDAP part begin
    Hashtable env = new Hashtable();

    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, ldapurl);
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, ldapuser);
    env.put(Context.SECURITY_CREDENTIALS, ldappass);
    
    DirContext dctx = new InitialDirContext(env);

    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "erurl", "owner", "erposixpkfile", "objectclass" };
    
    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "(&(erservicename=" + service + "))";

    NamingEnumeration results = dctx.search("", filter, sc);
        
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();

      if (attrs.get("erurl")!=null){
      url=(attrs.get("erurl").toString().split("[ ]"))[1];
      //System.out.println("url: " + url);
      }else{
          url = "no value";
          //System.out.println("url: " + url);
      }

      String ow=(attrs.get("owner").toString().split("[, ]"))[1];
      
      mel = searchFunction("(&(" + ow + "))","mail");
      //System.out.println("Mail: " + mel);      
      
      
      if (attrs.get("erposixpkfile")!=null){
          prx=(attrs.get("erposixpkfile").toString().split("[ ]"))[1];
          //System.out.println("key file: " + prx);
      }else{
          prx = "no value";
          //System.out.println("key file: " + prx);    
      }
 
      if (attrs.get("objectclass")!=null){
      obj=(attrs.get("objectclass").toString().split("[, ]"))[3];
      //System.out.println("type: " + obj);
      }else{
          obj = "no value";
          //System.out.println("objectclass: " + obj);
      }

    }
    dctx.close();
    //LDAP part end
    
    //DB2 part begin     
        Class.forName("com.ibm.db2.jcc.DB2Driver");
        String durl = dburl + "/" + db;
        
        Connection conn = DriverManager.getConnection(durl, dbuser, dbpass); 
        Statement stmt = conn.createStatement();
   
        String lastrecon = "select subject_service, max(started) as started from ITIMUSER.PROCESS where subject_service in '" + service + "' and result_summary='SW' AND TYPE='RC' group by subject_service";

        recmsg = qrecon(lastrecon,stmt);        
        if (recmsg!=null){
            recmsg=recmsg;
        }else{
            recmsg="no value";
        }
        //System.out.println(recmsg);
        
        String lasterror = "SELECT SHORT_DETAIL FROM ITIMUSER.PROCESS WHERE SUBJECT_SERVICE = '" + service + "' AND RESULT_SUMMARY = 'SF' AND TYPE='RC' and STARTED '" + recmsg + "' ";
        
        recdtl = qerror(lasterror,stmt);        
        if (recdtl!=null){
            recdtl=recdtl;
        }else{
            recdtl="no value";
        }
        //System.out.println(recdtl);
              
        conn.close();
        //DB2 part end
        
        System.out.println(service + ";" + url + ";" + mel + ";" + prx + ";" + obj + ";" + recmsg + ";" + recdtl);
        }
  }
