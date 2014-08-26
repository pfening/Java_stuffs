package uid;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableQuery {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        FileWriter fw = new FileWriter("/home/gabor/ITIM4U/test.csv");
        PrintWriter pw = new PrintWriter(fw);
        
        //String sql = "SELECT * FROM accounts";
        String sql = "SELECT * FROM accounts INNER JOIN ids ON ids.uid = accounts.uid INNER JOIN recon ON recon.servicename = accounts.service WHERE accounts.service IN (SELECT service FROM accounts WHERE service NOT LIKE '%Vault%') UNION SELECT * FROM accounts LEFT OUTER JOIN ids ON ids.uid = accounts.uid LEFT OUTER JOIN recon ON recon.servicename = accounts.service WHERE accounts.service IN (SELECT service FROM accounts WHERE service NOT LIKE '%Vault%') order by ownername";
        
        ResultSet res = selectdb.read(sql);        
        int ccol=res.getMetaData().getColumnCount();

        while(res.next())
        {
        for(int col=1;col<=ccol;col=col+1) {
            //System.out.print(res.getString(col)+";");
            pw.print(res.getString(col)+";");
            }
        pw.println();
        }
        pw.flush();
        pw.close();
        fw.close(); 
    }
}
