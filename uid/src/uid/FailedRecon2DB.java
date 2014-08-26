package uid;

import java.io.BufferedReader;
import java.io.FileReader;

public class FailedRecon2DB {

    public static void main(String[] args) {
    try{
        String strFile = "/home/gabor/ITIM4U/recon_failed.csv";
        BufferedReader br = new BufferedReader( new FileReader(strFile));
            String strLine = "";
                while( (strLine = br.readLine()) != null)			
                {
                String[] st = strLine.split(";",3);

                System.out.println("servicename: " + st[0] );
                String date=st[1].replace("-", ".");
                if (date.matches(".         ")){
                    date="2000.01.01";
                }
                System.out.println("lastrecon: " + date);
                String sql = "insert into recon values('"+st[0]+"','"+date+"')";
                selectdb.write(sql);
                }
            }
        catch(Exception e)
        {
        System.out.println("Exception happend: " + e);                  
        }
    }
}
