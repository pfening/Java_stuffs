package db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DB2ITIM {

    private static String dburl="jdbc:db2://192.168.122.92:50000";
    private static String db="itimdb";
    
    public static String qerror(String sql,Statement stmt) throws SQLException{
     
     ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()){
            //System.out.println("SUBJECT_SERVICE = " + rs.getString("SUBJECT_SERVICE"));
            String str=rs.getString("SHORT_DETAIL");
            String stra=str.replaceAll("\n", " ");
            String errormsg = (stra.substring(str.indexOf("tokenValue")).split("[\\<\\>\\=\\/]+"))[1];
            return errormsg;
        }
        rs.close();
        return null;
 }
    
     public static String qrecon(String sql,Statement stmt) throws SQLException{
     
     ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()){
            //System.out.println("SUBJECT_SERVICE = " + rs.getString("SUBJECT_SERVICE"));
            return rs.getString("STARTED");
        }
        rs.close();
        return null;
 }
    
    
 public static void main(String[] args) throws ClassNotFoundException, SQLException
  {
        String service= "Win Local Service";//args[0];
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String today = df.format(cal.getTime());
        
        cal.add(Calendar.DATE, -2);
        String yesterday = df.format(cal.getTime());
      
        Class.forName("com.ibm.db2.jcc.DB2Driver");
        String url =dburl + "/" + db;
        
        Connection conn = DriverManager.getConnection(url, "db2inst1", "password"); 
        Statement stmt = conn.createStatement();
        
             
        String lasterror = "SELECT SHORT_DETAIL FROM ITIMUSER.PROCESS WHERE SUBJECT_SERVICE = '" + service + "' AND RESULT_SUMMARY = 'SF' AND TYPE='RC' and STARTED between ('" + yesterday + "') and ('" + today + "') ";
        String lastrecon = "select subject_service, max(started) as started from ITIMUSER.PROCESS where subject_service in '" + service + "' and result_summary='SF' group by subject_service";
        
        String msg = qerror(lasterror,stmt);
        System.out.println("lasterror: " + msg);

        String msg2 = qrecon(lastrecon,stmt);
        System.out.println("lastrecon: " + msg2);
        
        conn.close();

  }

}
