package ssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;

public class NewItimLdap {
  private static String ldapurl="ldap://itimldap";
          
  public static void main(String[] args) throws Exception {
    String service= args[0];
    System.out.print(service + ";");
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, ldapurl);
    DirContext dctx = new InitialDirContext(env);
    String base = "dc=itim";
    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "objectclass" };
    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
    String filter = "(&(erservicename=" + service + "))";

    NamingEnumeration results = dctx.search(base, filter, sc);
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();

      String obj=(attrs.get("objectclass").toString().split("[, ]"))[3];
      //System.out.println("type: " + obj);
      if (obj.equals("erPosixAixRMIService")||obj.equals("erPosixLinuxRMIService")||obj.equals("erPosixSolarisRMIService")){
       //System.out.println("Unix system");
       urlsearch(service);
      }       
    }
    dctx.close();
  }
  
    private static void urlsearch(String service) throws NamingException, IOException, ClassNotFoundException{
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, ldapurl);
    DirContext dctx = new InitialDirContext(env);
    String base = "dc=itim";
    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "erurl" };
    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
    String filter = "(&(erservicename=" + service + "))";

    NamingEnumeration results = dctx.search(base, filter, sc);
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();
      
      if (attrs.get("erurl")!=null){
      String link=(attrs.get("erurl").toString().split("[ ]"))[1];
        
        String url;
        int port;

        int arr = link.split(":").length;       

        if (arr==1){
            url = (link.split(":"))[0];
            port=22;
        }else{        
            url = (link.split(":"))[0];
            port = Integer.parseInt((link.split(":"))[1]);
        }
        sshtest(url,port);
      
      }else{
          String link = "no value";
          System.out.println("url: " + link);
      }
    }
    }
  
  public static void sshtest(String host, int port) throws IOException, ClassNotFoundException {
    try{

        String user = "itimagt";        
        System.out.print(host + ";" + port + ";");        
        JSch shell = new JSch();  
        String keyfile= "/opt/tdi/.ssh/itimwas2_dsa";        
        String passphrase= "itim4ever";
        shell.addIdentity(keyfile,passphrase);
        Session session = shell.getSession(user, host, port);  
 
        UserInfo ui = new UserInfo() {
        public String getPassword(){ return null; }
        public boolean promptYesNo(String str){ return false; }
        public String getPassphrase(){ return null; }
        public boolean promptPassphrase(String message){ return false; }
        public boolean promptPassword(String message){ return false; }
        public void showMessage(String message){ }
            public String[] promptKeyboardInteractive(String destination,
                                              String name,
                                              String instruction,
                                              String[] prompt,
                                              boolean[] echo){return null;}
        };
        
        session.setUserInfo(ui);
        session.setConfig("StrictHostKeyChecking",  "no");
        session.connect();
        
        String command="sudo -l"; 
        Channel channel=session.openChannel("exec");        
        ((ChannelExec)channel).setCommand(command);        
        ((ChannelExec)channel).setErrStream(System.out);        
        channel.setInputStream(null);
        InputStream in=channel.getInputStream();        
        channel.connect();
        
        if(channel.getExitStatus() == 1){
            System.out.print("Connection NOT OK");
          }
          else{
            System.out.print("Connection OK");
          }

          try{Thread.sleep(1000); } catch(Exception ee){}
        
        channel.disconnect();
        session.disconnect();
    }
    catch(Exception e){
      System.out.println(e.getMessage()); 
      System.out.println();
    }
  }
}