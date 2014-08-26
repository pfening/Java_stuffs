package uid;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class Naptar2 {

  public static final void main(String[] args) throws ParseException, SQLException, ClassNotFoundException, IOException {
        FileWriter fw = new FileWriter("/home/gabor/ITIM4U/reconproblem.csv");
        PrintWriter pw = new PrintWriter(fw);     
      
        String sql = "SELECT service, account, ddate, lastrecon FROM recon, accounts WHERE accounts.service=recon.servicename";
        ResultSet res = selectdb.read(sql);
        while(res.next()){
        
        String time1 = res.getString(3);
        String time2 = res.getString(4);

        if(time1.matches(".*\\d.*")){
            time1=time1;
        } else{
            time1="2099.01.01";
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date date1 = format.parse(time1);
        
        Date date2 = format.parse(time2);
        if(date2.getTime() < date1.getTime()){
            pw.println(res.getString(1)+";"+res.getString(2)+";"+res.getString(3)+";"+res.getString(4) + ";delete request was after last success recon");
        } 
        else{
            pw.println(res.getString(1)+";"+res.getString(2)+";"+res.getString(3)+";"+res.getString(4));
        }        
        }
        pw.flush();
        pw.close();
        fw.close(); 
  }
}