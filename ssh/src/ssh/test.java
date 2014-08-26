package ssh;

import java.io.*;
import java.net.*;
import java.security.GeneralSecurityException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class test {

    public static void main(String[] args) throws Exception {

        String addr="https://9.69.191.246:950";
        final String[] ip= addr.split("[://]");
        System.out.println(ip[3]);
        
        
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
        
    URL url = new URL(addr);
        
    URLConnection conn = url.openConnection();
    conn.setDoOutput(true);
    
    BufferedReader inb;

            inb = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = inb.readLine()) != null) {
                    System.out.println(inputLine);
                  }

    }
}
