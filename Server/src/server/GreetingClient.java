package server;

import java.net.*;
import java.io.*;

public class GreetingClient
{
   public static void main(String [] args)
   {
      String serverName = "127.0.0.1";
      int port = 1099;
      try
      {
         System.out.println("Connecting to " + serverName + " on port " + port);
          try (Socket client = new Socket(serverName, port)) {
              System.out.println("Just connected to " + client.getRemoteSocketAddress());
              OutputStream outToServer = client.getOutputStream();
              DataOutputStream out = new DataOutputStream(outToServer);
              
              out.writeUTF("Hello from " + client.getLocalSocketAddress());
              InputStream inFromServer = client.getInputStream();
              DataInputStream in = new DataInputStream(inFromServer);
              System.out.println("Server says " + in.readUTF());
          }
      }catch(IOException e)
      {
      }
   }
}