package ssh;

import com.jcraft.jsch.*;
import java.io.IOException;
import java.io.InputStream;
 
public class ConnTest{
  public static void main(String[] arg) throws IOException, ClassNotFoundException {
    try{
        //String host = arg[0]; 
        String host = "192.168.122.13";
        String user = "sshtest";
        //int port = Integer.parseInt(arg[1]); 
        int port =22;
        
        System.out.print(host +";" + port +";");
        
        JSch shell = new JSch();  

        String keyfile= "/home/gabor/Java/ssh/ConnTest/id_dsa";
        
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

          try{
            Thread.sleep(1000);
          }
            catch(Exception ee){}
        
        channel.disconnect();
        session.disconnect();
    }
    catch(Exception e){
      System.out.println(e.getMessage()); 
      System.out.println();
    }

  }
  }