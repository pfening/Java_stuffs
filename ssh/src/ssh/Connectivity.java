
package ssh;

import java.io.*;  
import java.net.*;  

class Connectivity {  
    public static void main(String args[]) {  
        String ip= args[0];
        int port= Integer.parseInt(args[1]);
        try  
        {  
            Socket s=new Socket(ip,port);  
            InputStream is=s.getInputStream();  
            DataInputStream dis=new DataInputStream(is);  
            if(dis!=null)  
            {  
                System.out.println("Connected with ip "+ip+" and port "+port);  
            }  
            
            dis.close();  
            s.close();  
              
        }  
        catch(Exception e) { System.out.println(e); }  
    }  
}  