package db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DB3 {

 public static void main(String[] args) throws ClassNotFoundException, SQLException
  {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(2013, Calendar.APRIL, 1);
        String bgmth = df.format(cal.getTime());
        cal.add(Calendar.MONTH, 1);
        String endmth = df.format(cal.getTime());

        String url ="jdbc:db2://localhost:50000/timdb";
        Class.forName("com.ibm.db2.jcc.DB2Driver");
        Connection conn = DriverManager.getConnection(url, "db2tim", "passw0rd");
        Statement stmt = conn.createStatement();
        String sql = "SELECT TYPE, REQUESTER, COMPLETED, SUBJECT, SUBJECT_PROFILE, SUBJECT_SERVICE, RESULT_SUMMARY FROM ITIMUSER.PROCESS WHERE RESULT_SUMMARY = 'SS' and TYPE in ('AP', 'LP') and COMPLETED between ('" + bgmth + "') and ('" + endmth + "') and SUBJECT in ('bendem','federj','muehlp','wemanb','merkxw','v-newnatj','v-sappl','bentod','jenrut01','steber01','budeld','agreds','fernahej','gyarmg','kakukz','herbet') ORDER BY COMPLETED";
        ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){

                System.out.println("REQUESTER = " + rs.getString("REQUESTER"));
                System.out.println("SUBJECT = " + rs.getString("SUBJECT"));
                System.out.println("SUBJECT_PROFILE = " + rs.getString("SUBJECT_PROFILE"));
                System.out.println("SUBJECT_SERVICE = " + rs.getString("SUBJECT_SERVICE"));
                System.out.println();
            }
            conn.close();
  }
}
