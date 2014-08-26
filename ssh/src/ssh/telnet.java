package ssh;

import java.net.*;
 import java.io.*;

 class telnet
 {

    public static void main(String[] args) throws IOException {

        Socket pingSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            pingSocket = new Socket("telehack.com", 23);
            out = new PrintWriter(pingSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
        } catch (IOException e) {
            return;
        }

        out.println(".joke");
        System.out.println(in.readLine());
        out.close();
        in.close();
        pingSocket.close();
    }
 }