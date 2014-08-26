package dictionary;

import java.sql.*;

public class selectdb {
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet res;
    private static String dbpath ="jdbc:sqlite:/home/gabor/dictionary.db";

   public static ResultSet read(String sql) throws SQLException, ClassNotFoundException {
    try{
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(dbpath);
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
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(dbpath);
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
      stmt.close();
      conn.close();
      return null;
    }   
}