package ssh;

import com.jcraft.jsch.*;

public class Ssh_key_auth{

    static void connectit(String user, String host) {
    
    try{
      JSch jsch=new JSch();
      String hd=System.getProperty("user.home");
      jsch.setKnownHosts(hd + "/.ssh/known_hosts");
      String keyfile= hd + "/.ssh/id_rsa";
      jsch.addIdentity(keyfile);
      
      //String user="pfg";
 
      //String host="192.168.122.117";

      Session session=jsch.getSession(user, host, 22);

      UserInfo ui = new UserInfo() {
        public String getPassword(){ return null; }
        public boolean promptYesNo(String str){ return false; }
        public String getPassphrase(){ return null; }
        public boolean promptPassphrase(String message){ return false; }
        public boolean promptPassword(String message){ return false; }
        public void showMessage(String message){ }
        };
      
      session.setUserInfo(ui);
 
      session.connect();

      Channel channel=session.openChannel("xterm");

      channel.setInputStream(System.in);
      channel.setOutputStream(System.out);

      channel.connect();
      
      while(true){                
            if(channel.isClosed()){ break; }
                }
                channel.disconnect();
                session.disconnect();
                System.out.println("Disconnected");

    }
    catch(Exception e){
      System.out.println(e);
    }
  }
}