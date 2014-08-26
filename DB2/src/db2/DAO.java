package db2;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException;
public class DAO 
{
     public static void main(String  rgs[]) throws Exception 
     { 
        //get the connetion
        Connection  connection = getConnection (); 
     } 
      
     private static Connection  getConnection() throws ClassNotFoundException, SQLException 
     { 
        Class. forName ( "com.ibm.db2.jcc.DB2Driver" ); 
        Connection  conn =  DriverManager.getConnection("jdbc:db2://192.168.122.166:50000/itimdb","db2inst1","password"); 
      
        System. out .println( "From DAO, connection obtained " );
        return conn;
     } 
}