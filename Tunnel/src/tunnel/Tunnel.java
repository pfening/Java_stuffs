package tunnel;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.ProxySOCKS5;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class Tunnel {
    private static String sapauid;
    private static String esniuid;
    private static String esnipass;
    
    public static void tox(String uid, String pwd) throws IOException {

    String addr="https://9.69.191.246:950";
        
    final String[] ip= addr.split("[://]");

    String session[] = { uid, pwd, "1" };
    
    TrustManager[] trustAllCerts = new TrustManager[] { 
    new X509TrustManager() {     
        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
            return null;
        } 
        @Override
        public void checkClientTrusted( 
            java.security.cert.X509Certificate[] certs, String authType) {
            } 
        @Override
        public void checkServerTrusted( 
            java.security.cert.X509Certificate[] certs, String authType) {
        }
        } 
        }; 

        try {
            SSLContext sc = SSLContext.getInstance("SSL"); 
            sc.init(null, trustAllCerts, new java.security.SecureRandom()); 
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (GeneralSecurityException e) {
        } 

        try { 
            URL url = new URL(addr); 
        } catch (MalformedURLException e) {
        } 

        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier()
        {
            @Override
            public boolean verify(String hostname, SSLSession session)
            {
                if (hostname.equals(ip[3])) {
                    return true;
                }
                return false;
            }
        }
        );

    Document doc = Jsoup.connect(addr).get();
    String id = doc.select("input").first().attr("value");

    URL url = new URL(addr);

       for (int st=1; st<=3; st++) {

            String data = "?" + URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
            data += "&" + URLEncoder.encode("STATE", "UTF-8") + "=" + URLEncoder.encode(""+ st +"", "UTF-8");
            data += "&" + URLEncoder.encode("DATA", "UTF-8") + "=" + URLEncoder.encode(session[st-1], "UTF-8");
         
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);  
            
            BufferedReader in;
            try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
            wr.write(data);
            wr.flush();
            
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                    
                    Pattern exp = Pattern.compile("User authorized for standard services");
                      Matcher matcher = exp.matcher(inputLine.toString());
                        if(matcher.find())
                        {
                         JOptionPane.showMessageDialog(null, "Tox connection OK");                            
                        }
                }
                wr.close();
            }
            in.close();
       }
    } 

    public static void tunnel(String user) {
           try{       
      JSch jsch=new JSch();
      String hd=System.getProperty("user.home");
      jsch.setKnownHosts(hd + "/.ssh/known_hosts");
      String keyfile= hd + "/.ssh/id_rsa";
      jsch.addIdentity(keyfile);
      String host="10.70.7.68";
     
      Session session=jsch.getSession(user, host, 22);
      session.setPortForwardingL(9443, "localhost", 9443);      
      session.setProxy(new ProxySOCKS5("129.39.150.169", 1080)); 
      session.connect();
      Channel channel=session.openChannel("direct-tcpip");
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
    
    public static void main(String[] arg) throws IOException, URISyntaxException{
        Properties prop = new Properties(); 
    	try {
    	prop.load(new FileInputStream( System.getProperty("user.home") + "/sapaconn.properties"));
 
            esniuid=prop.getProperty("esniuid");
            esnipass = prop.getProperty("esnipass");
            sapauid = prop.getProperty("sapauid");
                        
    	} catch (IOException ex) {
    		ex.printStackTrace();
        } 

        tox(esniuid,esnipass);
        Desktop.getDesktop().browse(new URI("https://localhost:9443/itim/console/main"));
        tunnel(sapauid);        
    }
}
