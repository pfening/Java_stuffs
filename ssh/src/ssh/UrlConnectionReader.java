package ssh;

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class UrlConnectionReader {

    public static void main(String[] args) throws Exception {
    
    System.setProperty("javax.net.ssl.trustStore","/home/gabor/NetBeansProjects/ssh/src/ssh/mykeystore.jks");
    
    String uid="czy78596";
    String pwd="_JT0!KfxT4ayA65k";
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

    Document doc = Jsoup.connect("https://9.69.191.246:950").get();
    String id = doc.select("input").first().attr("value");
    System.out.println(id);
        
    URL url = new URL("https://9.69.191.246:950");
    System.out.println(url);
        
    URLConnection conn = url.openConnection();
    conn.setDoOutput(true);
        
        String data1 = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
	data1 += "&" + URLEncoder.encode("STATE", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
        data1 += "&" + URLEncoder.encode("DATA", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8");
        System.out.println(data1); 
       
        BufferedReader in;
        try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
            wr.write(data1);
            wr.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                 }
        }
        in.close();
        
        URL url2 = new URL("https://9.69.191.246:950?" + data1);
        System.out.println(url2);
        
        URLConnection conna = url2.openConnection();
        conna.setDoOutput(true);
        
        
        String data2 = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
	data2 += "&" + URLEncoder.encode("STATE", "UTF-8") + "=" + URLEncoder.encode("2", "UTF-8");
        data2 += "&" + URLEncoder.encode("DATA", "UTF-8") + "=" + URLEncoder.encode(pwd, "UTF-8");
        System.out.println(data2); 
        
        BufferedReader ina;
        try (OutputStreamWriter wra = new OutputStreamWriter(conna.getOutputStream())) {
            wra.write(data2);
            wra.flush();
            ina = new BufferedReader(new InputStreamReader(conna.getInputStream()));
            String inputLinea;
            while ((inputLinea = ina.readLine()) != null) {
                    System.out.println(inputLinea);
                 }
        }
        ina.close();
        
        
        URL url3 = new URL("https://9.69.191.246:950?" + data2);
        System.out.println(url3);
        
        URLConnection connb = url3.openConnection();
        connb.setDoOutput(true);
        
        
        String data3 = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
	data3 += "&" + URLEncoder.encode("STATE", "UTF-8") + "=" + URLEncoder.encode("3", "UTF-8");
        data3 += "&" + URLEncoder.encode("DATA", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
        System.out.println(data3); 
        
        BufferedReader inb;
        try (OutputStreamWriter wrb = new OutputStreamWriter(connb.getOutputStream())) {
            wrb.write(data3);
            wrb.flush();
            inb = new BufferedReader(new InputStreamReader(connb.getInputStream()));
            String inputLineb;
            while ((inputLineb = inb.readLine()) != null) {
                    //System.out.println(inputLineb);
                    
                      Pattern exp = Pattern.compile("User authorized for standard services");
                      Matcher matcher = exp.matcher(inputLineb.toString());
                        if(matcher.find())
                        {
                         System.out.println("OK");
                        }
                  }
        }
        inb.close();
        
    }
}
