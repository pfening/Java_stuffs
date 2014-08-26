package db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DB2 {

 public static void main(String[] args) throws ClassNotFoundException, SQLException
  {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String today = df.format(cal.getTime());

        cal.add(Calendar.DATE, 1);
        String tomorrow = df.format(cal.getTime());
      
        Class.forName("com.ibm.db2.jcc.DB2Driver");
        String url ="jdbc:db2://192.168.122.92:50000/itimdb";
        
        Connection conn = DriverManager.getConnection(url, "db2inst1", "password"); 
        Statement stmt = conn.createStatement();
        //String sql = "SELECT TYPE, REQUESTER, COMPLETED, SUBJECT, SUBJECT_PROFILE, SUBJECT_SERVICE, RESULT_SUMMARY, RESULT_DETAIL FROM ITIMUSER.PROCESS WHERE RESULT_SUMMARY = 'SS' and TYPE in ('AP', 'LP') and COMPLETED between ('" + bgmth + "') and ('" + endmth + "') and SUBJECT in ('tester', 'gabor') ";
        String sql = "SELECT TYPE, REQUESTER, COMPLETED, SUBJECT, SUBJECT_PROFILE, SUBJECT_SERVICE, RESULT_SUMMARY, SHORT_DETAIL FROM ITIMUSER.PROCESS WHERE SUBJECT_SERVICE = 'Win Local Service' AND RESULT_SUMMARY = 'SF' AND TYPE='RC' ";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()){
            //System.out.println("ROOT_PROCESS_ID = " + rs.getString("ROOT_PROCESS_ID"));
            //System.out.println("ID = " + rs.getString("ID"));
            //System.out.println("PARENT_ID = " + rs.getString("PARENT_ID"));
            //System.out.println("PARENT_ACTIVITY_ID = " + rs.getString("PARENT_ACTIVITY_ID"));
            //System.out.println("NAME = " + rs.getString("NAME"));
            System.out.println("TYPE = " + rs.getString("TYPE"));
            //System.out.println("DEFINITION_ID = " + rs.getString("DEFINITION_ID"));
            //System.out.println("REQUESTER_TYPE = " + rs.getString("REQUESTER_TYPE"));
            System.out.println("REQUESTER = " + rs.getString("REQUESTER"));
            //System.out.println("REQUESTER_NAME = " + rs.getString("REQUESTER_NAME"));
            //System.out.println("DESCRIPTION = " + rs.getString("DESCRIPTION"));
            //System.out.println("PRIORITY = " + rs.getString("PRIORITY"));
            //System.out.println("SCHEDULED = " + rs.getString("SCHEDULED"));
            //System.out.println("STARTED = " + rs.getString("STARTED"));
            System.out.println("COMPLETED = " + rs.getString("COMPLETED"));
            //System.out.println("LASTMODIFIED = " + rs.getString("LASTMODIFIED"));
            //System.out.println("SUBMITTED = " + rs.getString("SUBMITTED"));
            //System.out.println("STATE = " + rs.getString("STATE"));
            //System.out.println("NOTIFY = " + rs.getString("NOTIFY"));
            //System.out.println("REQUESTEE = " + rs.getString("REQUESTEE"));
            //System.out.println("REQUESTEE_NAME = " + rs.getString("REQUESTEE_NAME"));
            System.out.println("SUBJECT = " + rs.getString("SUBJECT"));
            System.out.println("SUBJECT_PROFILE = " + rs.getString("SUBJECT_PROFILE"));
            System.out.println("SUBJECT_SERVICE = " + rs.getString("SUBJECT_SERVICE"));
            //System.out.println("SUBJECT_ACCESS_ID = " + rs.getString("SUBJECT_ACCESS_ID"));
            //System.out.println("SUBJECT_ACCESS_NAME = " + rs.getString("SUBJECT_ACCESS_NAME"));
            //System.out.println("COMMENTS = " + rs.getString("COMMENTS"));
            System.out.println("RESULT_SUMMARY = " + rs.getString("RESULT_SUMMARY"));
            //System.out.println("RESULT_DETAIL = " + rs.getString("RESULT_DETAIL"));
            System.out.println("SHORT_DETAIL = " + rs.getString("SHORT_DETAIL"));
            //String str =rs.getString("SHORT_DETAIL");
            //ProcMsg(str);
            //System.out.println("Short details = " + (str.substring(str.indexOf("tokenValue")).split("[\\<\\>\\=\\/]+"))[1]);
            //System.out.println("TENANT = " + rs.getString("TENANT"));
            System.out.println();
        }
        conn.close();

  }

    private static void ProcMsg(String str) {
        String tokens = (str.substring(str.indexOf("tokenValue")).split("[\\<\\>\\=\\/]+"))[1];
        System.out.println(tokens);
    
    }

}
