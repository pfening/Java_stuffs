import java.io.*;
import java.net.*;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class UrlConnectionReader {
    public static void main(String[] args) throws Exception {
        
        System.setProperty("javax.net.ssl.trustStore","/home/gabor/NetBeansProjects/ssh/src/ssh/mykeystore.jks");

        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier()
        {
            @Override
            public boolean verify(String hostname, SSLSession session)
            {
                if (hostname.equals("9.69.191.246")) {
                    return true;
                }
                return false;
            }
        }
        );
        
        String data = URLEncoder.encode("STATE", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
        data += "&" + URLEncoder.encode("DATA", "UTF-8") + "=" + URLEncoder.encode("czy78596", "UTF-8");
        System.out.println(data);
        
        URL url = new URL("https://9.69.191.246:950");
        System.out.println(url);
        
        URLConnection yc = url.openConnection();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                                         yc.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
        }
    }
}
