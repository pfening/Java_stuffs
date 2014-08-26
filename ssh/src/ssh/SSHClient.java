package ssh;

import com.jcraft.jsch.*;  
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;  
  
public class SSHClient {  
    public static void main(String[] args) throws JSchException, IOException {  
        String endLineStr = " $ "; // it is dependant to the server  
        String host = "192.168.122.132"; // host IP  
        String user = "pfg"; // username for SSH connection  
        String password = "770922"; // password for SSH connection  
        int port = 22; // default SSH port  
  
        JSch shell = new JSch();  
        // get a new session    
        Session session = shell.getSession(user, host, port);  
  
        // set user password and connect to a channel  
        session.setUserInfo(new SSHUserInfo(password));  
        session.connect();  
        Channel channel = session.openChannel("shell");  
        channel.connect();  
  
        DataInputStream dataIn = new DataInputStream(channel.getInputStream());  
        DataOutputStream dataOut = new DataOutputStream(channel.getOutputStream());  
  
        // send ls command to the server  
        dataOut.writeBytes("sudo su -\r\n");  
        System.out.println(dataIn.readLine());
        dataOut.writeBytes("770922\r\n"); 
        dataOut.writeBytes("who\r\n"); 
        //dataOut.writeBytes("exit\r\n"); 
        dataOut.flush();  
  
        // and print the response   
        String line = dataIn.readLine();  
        System.out.println(line);  
        while(!line.endsWith(endLineStr)) {  
            System.out.println(line);  
            line = dataIn.readLine();  
        }  
        dataIn.close();  
        dataOut.close();  
        channel.disconnect();  
        session.disconnect();  
    }  
  
    // this class implements jsch UserInfo interface for passing password to the session  
    static class SSHUserInfo implements UserInfo {  
        private String password;  
  
        SSHUserInfo(String password) {  
            this.password = password;  
        }  
  
        @Override
        public String getPassphrase() { return null; }  
  
        @Override
        public String getPassword() { return password; }  
  
        @Override
        public boolean promptPassword(String arg0) { return true; }  
  
        @Override
        public boolean promptPassphrase(String arg0) { return true; }  
  
        @Override
        public boolean promptYesNo(String arg0) { return true; }  
  
        @Override
        public void showMessage(String arg0) { System.out.println(arg0); }  
    }  
  
}  