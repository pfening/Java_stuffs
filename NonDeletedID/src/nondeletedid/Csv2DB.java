package nondeletedid;

import java.io.BufferedReader;
import java.io.FileReader;

public class Csv2DB {

    public static void main(String[] args) {
                        try
                {

                        String strFile = "/home/gabor/Documents/nondeletedaccounts_servers.csv";

                        BufferedReader br = new BufferedReader( new FileReader(strFile));
                        String strLine = "";
                        while( (strLine = br.readLine()) != null)			
                        {
			
			String[] st = strLine.split(";",3);

			    System.out.println("service: " + st[0] );
			    System.out.println("erglobid: " + st[1]);
			    System.out.println("type: " + st[2]);
                            String sql = "insert into servers values('"+st[0]+"','"+st[1]+"','"+st[2]+"','not started')";
                            selectdb.write(sql);
                        }
                                              
                }
                catch(Exception e)
                {
                        System.out.println("Exception happend: " + e);                  
                }
    }
}
