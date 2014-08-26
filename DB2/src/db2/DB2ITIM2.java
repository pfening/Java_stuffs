package db2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class DB2ITIM2 {

 public static void query(String sql,Statement stmt) throws SQLException{
     
     ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()){
            System.out.println("TYPE = " + rs.getString("TYPE"));
            System.out.println("SUBJECT_SERVICE = " + rs.getString("SUBJECT_SERVICE"));
            System.out.println("RESULT_SUMMARY = " + rs.getString("RESULT_SUMMARY"));
            System.out.println("SHORT_DETAIL = " + rs.getString("SHORT_DETAIL"));
            System.out.println("COMPLETED = " + rs.getString("COMPLETED"));
            System.out.println();
        }
        rs.close();
 }
    
     public static void querya(String sql,Statement stmt) throws SQLException{
     
     ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()){
            System.out.println("SUBJECT_SERVICE = " + rs.getString("SUBJECT_SERVICE"));
            System.out.println("Started = " + rs.getString("STARTED"));
            System.out.println();
        }
        rs.close();
 }

 public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException
  {
        String service= "Win Local Service";//args[0];
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String today = df.format(cal.getTime());

        cal.add(Calendar.DATE, 1);
        String tomorrow = df.format(cal.getTime());
        
        cal.add(Calendar.DATE, -6);
        String yesterday = df.format(cal.getTime());

        Class.forName("com.ibm.db2.jcc.DB2Driver");
        
        Properties prop = new Properties();
        
        prop.load(new FileInputStream("/home/gabor/NetBeansProjects/DB2/src/db2/config.properties"));
        String url = "jdbc:db2://"+prop.getProperty("url")+":"+prop.getProperty("port")+"/"+prop.getProperty("database");
        
        Connection conn = DriverManager.getConnection(url, prop.getProperty("dbuser"), prop.getProperty("dbpassword")); 
        Statement stmt = conn.createStatement();
        
        String sql = "SELECT TYPE, COMPLETED, SUBJECT_SERVICE, RESULT_SUMMARY, SHORT_DETAIL FROM ITIMUSER.PROCESS WHERE SUBJECT_SERVICE = '" + service + "' AND RESULT_SUMMARY = 'SF' AND TYPE='RC' and STARTED between ('" + yesterday + "') and ('" + tomorrow + "') ";
        String sqla = "select subject_service, max(started) as started from ITIMUSER.PROCESS where subject_service in '" + service + "' and result_summary='SF' group by subject_service";

        query(sql,stmt);
        querya(sqla,stmt);
                 
        conn.close();
  }
}