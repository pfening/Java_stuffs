package packages;

import java.sql.*;

public class sdb {
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet res;
    private static ResultSet result;
    private static String dbpath ="jdbc:sqlite:/home/gabor/test.db";

   public static ResultSet read(String sql) throws SQLException, ClassNotFoundException {
    try{
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(dbpath);
      stmt = conn.createStatement();
      res = stmt.executeQuery(sql);
      result=res;
       }
    finally{
            res.close();
            stmt.close();
            conn.close();         
    }
        return result;
    }

    public static String write(String sql) throws ClassNotFoundException, SQLException{
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(dbpath);
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
      stmt.close();
      conn.close();
      return null;
    }   
}