package uid;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class Naptar {

  public static final void main(String[] args) throws ParseException, SQLException, ClassNotFoundException {
    
        String sql = "SELECT service, ddate, lastrecon FROM recon, accounts WHERE accounts.service=recon.servicename";
        ResultSet res = selectdb.read(sql);
        while(res.next())
        {
        System.out.println(res.getString(1)+";"+res.getString(2)+";"+res.getString(3));
        String time1 = res.getString(2);
        String time2 = res.getString(3);

        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);
        if(date2.getTime() < date1.getTime()){
            System.out.println("delete request was after last success recon");
        } 
        
        
        }
  }
}