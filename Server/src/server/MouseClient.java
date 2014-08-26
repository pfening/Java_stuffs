package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class MouseClient {

    public static void main(String[] args) {
      String serverName = "192.168.122.35";
      int port = 1099;

      try (Socket client = new Socket(serverName, port)) {
              while(true) {
              InputStream inFromServer = client.getInputStream();
              DataInputStream in = new DataInputStream(inFromServer);
              System.out.println(in.readUTF());
              }
          }
      catch(IOException e)
      {
      }
    }
    
}
