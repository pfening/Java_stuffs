package db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MonthlyPassResetReport {

 public static void main(String[] args) throws ClassNotFoundException, SQLException
  {
        
        int year = Integer.parseInt(args[0]);
        int month = Integer.parseInt(args[1]);
        int day = Integer.parseInt(args[2]);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.MONTH, day);
        String bgmth = df.format(cal.getTime());
        cal.add(Calendar.MONTH, 1);
        String endmth = df.format(cal.getTime());

        String url ="jdbc:db2://localhost:50000/timdb";
        Class.forName("com.ibm.db2.jcc.DB2Driver");
        Connection conn = DriverManager.getConnection(url, "db2tim", "Sapa4ever");
        Statement stmt = conn.createStatement();
        String sql = "SELECT TYPE, REQUESTER_NAME, COMPLETED, SUBJECT, SUBJECT_PROFILE, REQUESTEE_NAME, RESULT_SUMMARY FROM ITIMUSER.PROCESS WHERE RESULT_SUMMARY = 'SS' and TYPE in ('LP') and COMPLETED between ('" + bgmth + "') and ('" + endmth + "') ORDER BY COMPLETED";
        ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
	        System.out.println(rs.getString("TYPE")+";"+rs.getString("REQUESTER_NAME")+";"+rs.getString("REQUESTEE_NAME")+";"+rs.getString("COMPLETED"));
            }
            conn.close();
  }
}