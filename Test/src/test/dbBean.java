package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbBean {
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet res;
    private static String dbpath ="jdbc:sqlite:/home/gabor/test.db";
    
    public dbBean() throws ClassNotFoundException, SQLException {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(dbpath);
    }
    
    public static ResultSet read(String sql) throws SQLException, ClassNotFoundException {
    try{
      stmt = conn.createStatement();
      res = stmt.executeQuery(sql);
       }
    catch(SQLException e)
        {
        res.close();
        stmt.close();
        conn.close();
        }
        return res;
    }
    
   public static String write(String sql) throws ClassNotFoundException, SQLException{
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
      stmt.close();
      conn.close();
      return null;
    }
    
}
