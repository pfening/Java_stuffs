package server;

import java.net.*;
import java.io.*;

public class GreetingServer extends Thread
{
   private final ServerSocket serverSocket;
   
   public GreetingServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
   }

   @Override
   public void run()
   {
      while(true)
      {
         try
         {
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
             try (Socket server = serverSocket.accept()) {
                 System.out.println("Just connected to " + server.getRemoteSocketAddress());
                 DataInputStream in = new DataInputStream(server.getInputStream());
                 System.out.println(in.readUTF());
                 DataOutputStream out = new DataOutputStream(server.getOutputStream());
                 out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
             }
         }catch(IOException e)
         {
            break;
         }
      }
   }
   public static void main(String [] args)
   {
      int port = 1099;
      try
      {
         Thread t = new GreetingServer(port);
         t.start();
      }catch(IOException e)
      {
      }
   }
}