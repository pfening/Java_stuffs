package server;

import java.awt.MouseInfo;
import java.net.*;
import java.io.*;

public class MServer extends Thread
{
   private final ServerSocket mSocket;
   
   public MServer(int port) throws IOException
   {
      mSocket = new ServerSocket(port);

            Socket server = mSocket.accept();
            while(true){
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("("+MouseInfo.getPointerInfo().getLocation().x+", "+MouseInfo.getPointerInfo().getLocation().y+")");
                }
 
      }

   public static void main(String [] args)
   {
      int port = 1099;
      try
      {
         Thread t = new MServer(port);
         t.start();
      }catch(IOException e)
      {
      }
   }
}