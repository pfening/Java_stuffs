package server;

import java.awt.MouseInfo;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MouseServer extends Thread{
    
private final ServerSocket mSocket;

public MouseServer() throws IOException{
    mSocket = new ServerSocket(1099);

    Socket server = mSocket.accept();
        while(true){
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("("+MouseInfo.getPointerInfo().getLocation().x+", "+MouseInfo.getPointerInfo().getLocation().y+")");
            }
}

    public static void main(String[] args) throws IOException, InterruptedException {
        try{
           Thread s=new MouseServer();
            s.start(); 
        }catch(IOException e){            
        }
        
    }
    
}
