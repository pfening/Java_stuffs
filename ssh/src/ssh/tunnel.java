package ssh;

import com.jcraft.jsch.*;

public class tunnel{
  public static void main(String[] arg){
    
    try{       
      JSch jsch=new JSch();
      String hd=System.getProperty("user.home");
      jsch.setKnownHosts(hd + "/.ssh/known_hosts");
      String keyfile= hd + "/.ssh/id_rsa";
      jsch.addIdentity(keyfile);
      
      String user="sasfms";
      //String user="CZZ62256";
 
      String host="sasfms.frankfurt.de.ibm.com";
      //String host="10.70.7.68";

      int port=10087;
      //int port=9443;
      
      Session session=jsch.getSession(user, host, 22);

      session.setPortForwardingL(port, "localhost", port);
      
      //session.setProxy(new ProxySOCKS5("localhost", 10087));
 
      session.connect();

      Channel channel=session.openChannel("direct-tcpip");      
      //Channel channel=session.openChannel("shell");

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