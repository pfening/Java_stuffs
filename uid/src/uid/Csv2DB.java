package uid;

import java.io.BufferedReader;
import java.io.FileReader;

public class Csv2DB {

    public static void main(String[] args) {
                        try
                {

                        String strFile = "/home/gabor/ITIM4U/bp-new.csv";

                        BufferedReader br = new BufferedReader( new FileReader(strFile));
                        String strLine = "";
                        while( (strLine = br.readLine()) != null)			
                        {
			
			String[] st = strLine.split(";",3);

			    System.out.println("uid: " + st[0] );
			    System.out.println("name: " + st[1]);
			    System.out.println("empstat: " + st[2]);
                            String sql = "insert into ids values('"+st[0]+"','"+st[1]+"','"+st[2]+"')";
                            selectdb.write(sql);
                        }
                                              
                }
                catch(Exception e)
                {
                        System.out.println("Exception happend: " + e);                  
                }
    }
}
