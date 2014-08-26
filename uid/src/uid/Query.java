package uid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
 
 private static String dburl="jdbc:db2://itimdb2:50000";
 private static String db="itimdb";
 private static String dbuser="itimdb";
 private static String dbpass="Itim83De";
 
 private static String service;   

    public static String qrecon(String sql,Statement stmt) throws SQLException{     
     ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
        return rs.getString("STARTED");
        }
        rs.close();
     return null;
 	}
 
  public static void main(String[] args) throws Exception {
        String service = args[0];

        System.out.println("Servicename: " + service );
   
    //DB2 part begin     
        Class.forName("com.ibm.db2.jcc.DB2Driver");
        String url =dburl + "/" + db;
        
        Connection conn = DriverManager.getConnection(url, dbuser, dbpass); 
        Statement stmt = conn.createStatement();

        String lastrecon = "select subject_service, max(started) as started from ITIMUSER.PROCESS where subject_service in '" + service + "' and result_summary='SF' group by subject_service";

        String recmsg = qrecon(lastrecon,stmt);        
        if (recmsg!=null){
            recmsg=recmsg;
        }else{
            recmsg="no value";
        }
	System.out.println(recmsg);
        
        conn.close();
        //DB2 part end
        }
}