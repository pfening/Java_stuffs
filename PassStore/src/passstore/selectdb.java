package passstore;

import java.sql.*;

public class selectdb {

   public static ResultSet read(String sql) throws SQLException, ClassNotFoundException {
   Connection conn = null;
   Statement stmt = null;
   ResultSet res = null;
    String fs = System.getProperty("file.separator");
    String hd = System.getProperty("user.home");
    String dbpath ="jdbc:sqlite:" + hd + fs + ".passstore" + fs + "passwd.db";
   try{
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(dbpath);
      stmt = conn.createStatement();
      res = stmt.executeQuery(sql);

   }
   catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("Error processing results: " + e.toString());
            try
            {
                res.close();
                stmt.close();
                conn.close();
            }
            catch(Exception ex)
            {
            }
         }
        return res;
}
   
public static String write(String sql){
   Connection conn = null;
   Statement stmt = null;
    String fs = System.getProperty("file.separator");
    String hd = System.getProperty("user.home");
    String dbpath ="jdbc:sqlite:" + hd + fs + ".passstore" + fs + "passwd.db";
   try{
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(dbpath);
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
   }
   catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("Error processing results: " + e.toString());
            try
            {
                stmt.close();
                conn.close();
            }
            catch(Exception ex)
            {
            }
         }
        return null;
}   
   
}