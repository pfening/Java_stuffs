package uid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servers2DB {

    public static void main(String[] args) {
                try
                {
                        String strFile = "/home/gabor/ITIM4U/servers.csv";

                        BufferedReader br = new BufferedReader( new FileReader(strFile));
                        String strLine = "";
                        while( (strLine = br.readLine()) != null)			
                        {
			
			String[] st = strLine.split(";",5);                          
                            
			    System.out.println("servername: " + st[0] );
			    System.out.println("url: " + st[1]);
                            System.out.println("proxy: " + st[2]);
			    System.out.println("type: " + st[3]);
                            String date = new SimpleDateFormat("yyyy-MM-dd").format((Date) new SimpleDateFormat("yyyy-MM-dd").parse(st[4]));
                            System.out.println("lastrecon: "+ date);
	                                                        
                            String sql = "insert into servers values('"+st[0]+"','"+st[1]+"','"+st[2]+"','"+st[3]+"','"+date+"')";
                            selectdb.write(sql);
                        }
                                              
                }
                catch(Exception e)
                {
                        System.out.println("Exception happend: " + e);                  
                }
    }
}

