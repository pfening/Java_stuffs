package ssh;

public class NewMain1 {
    private static String url;
    private static String port;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String link="https://123.456.789:45580";
        
        if (link.startsWith("https://")){
          String[] lnk = link.split(":");
          url = (lnk[1].split("//"))[1];
          port = lnk[2];
      }
        System.out.println(url);
        System.out.println(port);
      
    }
}
