package uid;

import java.io.BufferedReader;
import java.io.FileReader;

public class Accounts2DB {

    public static void main(String[] args) {
                        try
                {

                        String strFile = "/home/gabor/ITIM4U/scope.csv";

                        BufferedReader br = new BufferedReader( new FileReader(strFile));
                        String strLine = "";
                        while( (strLine = br.readLine()) != null)			
                        {
			
			String[] st = strLine.split(",",10);

			    System.out.println("account: " + st[0] );
			    //System.out.println("ownername: " + st[1]);
                            //System.out.println("uid: " + st[2]);
			    //System.out.println("service: " + st[3]);
                            //System.out.println("ddate: " + st[4] );
			    //System.out.println("dsd: " + st[5]);
			    //System.out.println("sdate: " + st[6]);
                            //System.out.println("dss: " + st[7] );
			    //System.out.println("status: " + st[8]);
                            //System.out.println("type: " + st[9]);
	                                                        
                            String sql = "insert into accounts values('"+st[0]+"','"+st[1]+"','"+st[2]+"','"+st[3]+"','"+st[4]+"','"+st[5]+"','"+st[6]+"','"+st[7]+"','"+st[8]+"','"+st[9]+"')";
                            selectdb.write(sql);
                        }
                                              
                }
                catch(Exception e)
                {
                        System.out.println("Exception happend: " + e);                  
                }
    }
}
