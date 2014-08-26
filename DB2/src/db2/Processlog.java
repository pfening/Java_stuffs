package db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Processlog {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        Class.forName("com.ibm.db2.jcc.DB2Driver");
        String url ="jdbc:db2://192.168.122.166:50000/itimdb";
        conn = DriverManager.getConnection(url, "db2inst1", "password"); 
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT ID, PROCESS_ID, ACTIVITY_ID, CREATED, EVENTTYPE, OLD_PARTICIPANT_TYPE, OLD_PARTICIPANT_ID, NEW_PARTICIPANT_TYPE, NEW_PARTICIPANT_ID, REQUESTOR_TYPE, REQUESTOR, REQUESTOR_DN, OLD_STATE, NEW_STATE, DATA_ID, NEW_DATA, SMALL_NEW_DATA FROM ITIMUSER.PROCESSLOG");
        
        while (rs.next()){
            System.out.println("ID = " + rs.getString("ID"));
            System.out.println("PROCESS_ID = " + rs.getString("PROCESS_ID"));
            System.out.println("ACTIVITY_ID = " + rs.getString("ACTIVITY_ID"));
            System.out.println("CREATED = " + rs.getString("CREATED"));
            System.out.println("EVENTTYPE = " + rs.getString("EVENTTYPE"));
            System.out.println("OLD_PARTICIPANT_TYPE = " + rs.getString("OLD_PARTICIPANT_TYPE"));
            System.out.println("OLD_PARTICIPANT_ID = " + rs.getString("OLD_PARTICIPANT_ID"));
            System.out.println("NEW_PARTICIPANT_TYPE = " + rs.getString("NEW_PARTICIPANT_TYPE"));
            System.out.println("NEW_PARTICIPANT_ID = " + rs.getString("NEW_PARTICIPANT_ID"));
            System.out.println("REQUESTOR_TYPE = " + rs.getString("REQUESTOR_TYPE"));
            System.out.println("REQUESTOR = " + rs.getString("REQUESTOR"));
            System.out.println("REQUESTOR_DN = " + rs.getString("REQUESTOR_DN"));
            System.out.println("OLD_STATE = " + rs.getString("OLD_STATE"));
            System.out.println("NEW_STATE = " + rs.getString("NEW_STATE"));
            System.out.println("DATA_ID = " + rs.getString("DATA_ID"));
            System.out.println("NEW_DATA = " + rs.getString("NEW_DATA"));
            System.out.println("SMALL_NEW_DATA = " + rs.getString("SMALL_NEW_DATA"));
        }
     conn.close();

    }
}
