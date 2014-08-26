package nondeletedid;

import java.sql.*;

public class selectdb {
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet res;
    private static final String dbpath ="jdbc:sqlite:/home/gabor/Documents/nond.db";

   public static ResultSet read(String sql) throws SQLException, ClassNotFoundException {

      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(dbpath);
      stmt = conn.createStatement();
      res = stmt.executeQuery(sql);

        res.close();
        stmt.close();
        conn.close();

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