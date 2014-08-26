package ssh;

import java.io.*;
import java.net.*;
import java.security.GeneralSecurityException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class toxt {

    public static void main(String[] args) throws Exception {
    
    String uid="czy78596";
    String pwd="Olu$ka88";
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
                if (hostname.equals(ip)) {
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
                    //System.out.println(inputLine);
                    
                    Pattern exp = Pattern.compile("User authorized for standard services");
                      Matcher matcher = exp.matcher(inputLine.toString());
                        if(matcher.find())
                        {
                         System.out.println("Connection OK");
                        }
                }
                wr.close();
            }
            in.close();
       }
    }
}