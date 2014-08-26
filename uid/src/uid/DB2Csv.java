package uid;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB2Csv {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        FileWriter fw = new FileWriter("/home/gabor/ITIM4U/All_data.csv");
        PrintWriter pw = new PrintWriter(fw);
        
        String sql = "SELECT * FROM accounts INNER JOIN ids ON ids.uid = accounts.uid INNER JOIN recon ON recon.servicename = accounts.service WHERE accounts.service IN (SELECT service FROM accounts WHERE service NOT LIKE '%Vault%') UNION SELECT * FROM accounts LEFT OUTER JOIN ids ON ids.uid = accounts.uid LEFT OUTER JOIN recon ON recon.servicename = accounts.service WHERE accounts.service IN (SELECT service FROM accounts WHERE service NOT LIKE '%Vault%') order by ownername";
        ResultSet res = selectdb.read(sql);
        
        while(res.next())
        {
         pw.println(res.getString(1)+";"+res.getString(2)+";"+res.getString(3)+";"+res.getString(4)+";"+res.getString(5)+";"+res.getString(6)+";"+res.getString(7)+";"+res.getString(8)+";"+res.getString(9)+";"+res.getString(10)+";"+res.getString(11)+";"+res.getString(12)+";"+res.getString(13)+";"+res.getString(14));
        }

        pw.flush();

        pw.close();

        fw.close();        
    }     
}
