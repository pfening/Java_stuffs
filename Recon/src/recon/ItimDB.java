import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ItimDB {

    private static String dburl="jdbc:db2://itimdb2:50000";
    private static String db="itimdb";
    
    public static void query(String sql,Statement stmt) throws SQLException{
     
     ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()){
            //System.out.println("TYPE = " + rs.getString("TYPE"));
            System.out.println("SUBJECT_SERVICE = " + rs.getString("SUBJECT_SERVICE"));
            //System.out.println("RESULT_SUMMARY = " + rs.getString("RESULT_SUMMARY"));
            //System.out.println("SHORT_DETAIL = " + rs.getString("SHORT_DETAIL"));
            //System.out.println("COMPLETED = " + rs.getString("COMPLETED"));
            String str=rs.getString("SHORT_DETAIL");
            String tokens = (str.substring(str.indexOf("tokenValue")).split("[\\<\\>\\=\\/]+"))[1];
            System.out.println("Error: " + tokens);
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
    
    
 public static void main(String[] args) throws ClassNotFoundException, SQLException
  {
        String service= args[0];
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String today = df.format(cal.getTime());

        cal.add(Calendar.DATE, 1);
        String tomorrow = df.format(cal.getTime());
        
        cal.add(Calendar.DATE, -2);
        String yesterday = df.format(cal.getTime());
      
        Class.forName("com.ibm.db2.jcc.DB2Driver");
        String url =dburl + "/" + db;
        
        Connection conn = DriverManager.getConnection(url, "itimdb", "Itim83De"); 
        Statement stmt = conn.createStatement();
        
        String sql = "SELECT TYPE, SUBJECT_SERVICE, RESULT_SUMMARY, SHORT_DETAIL, COMPLETED FROM ITIMUSER.PROCESS WHERE SUBJECT_SERVICE = '" + service + "' AND RESULT_SUMMARY = 'SF' AND TYPE='RC' and STARTED between ('" + yesterday + "') and ('" + tomorrow + "') ";
        String sqla = "SELECT SUBJECT_SERVICE, MAX(STARTED) AS STARTED FROM ITIMUSER.PROCESS_RECON WHERE SUBJECT_SERVICE IN '" + service + "' AND RESULT_SUMMARY='SS' GROUP BY SUBJECT_SERVICE";
        
        query(sql,stmt);
        querya(sqla,stmt);
        
        conn.close();

  }

}
