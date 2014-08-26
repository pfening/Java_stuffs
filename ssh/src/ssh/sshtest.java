package ssh;

import com.jcraft.jsch.*;

public class sshtest{
  public static void main(String[] arg){
    
    try{
      JSch jsch=new JSch();

      String keyfile= "/home/gabor/Java/ssh/ConnTest/id_dsa";
      String passphrase= "itim4ever";
      jsch.addIdentity(keyfile,passphrase);
          
      String user="sshtest";
 
      String host="192.168.122.133";

      Session session=jsch.getSession(user, host, 22);

      UserInfo ui = new UserInfo() {
        public String getPassword(){ return null; }
        public boolean promptYesNo(String str){ return true; }
        public String getPassphrase(){ return null; }
        public boolean promptPassphrase(String message){ return false; }
        public boolean promptPassword(String message){ return false; }
        public void showMessage(String message){ }
            public String[] promptKeyboardInteractive(String destination,
                                              String name,
                                              String instruction,
                                              String[] prompt,
                                              boolean[] echo){
      return null;
            }
        };
      
      session.setUserInfo(ui);

      session.connect();

      Channel channel=session.openChannel("shell");

      channel.setInputStream(System.in);
      channel.setOutputStream(System.out);

      channel.connect();
      
      while(true){                
            if(channel.isClosed()){
          System.out.println("exit-status: "+channel.getExitStatus());
          break;
        }
        try{Thread.sleep(1000);}catch(Exception ee){}
      }
      channel.disconnect();
      session.disconnect();

    }
    catch(Exception e){
      System.out.println(e);
    }
  }
}