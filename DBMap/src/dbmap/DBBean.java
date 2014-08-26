package dbmap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBBean {
    
    static String dbpath ="jdbc:sqlite:/home/gabor/test.db";
    private String name;
    private String place;
    private int age;    
    private Connection conn;
    private Statement stmt;
    private ResultSet res;

    DBBean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setUserQuery(String name) throws SQLException, ClassNotFoundException {        
    this.name=name;
    String sql = "SELECT * FROM test where name='"+name+"'"; 

      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(dbpath);
      stmt = conn.createStatement();
      res = stmt.executeQuery(sql);  
    }    
    
      public void setUser() throws ClassNotFoundException, SQLException {
      String sql = "insert into test values('"+name+"','"+age+"','"+place+"')";
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(dbpath);
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
   
    public void setName(String name) {
        this.name = name;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public String getName() throws SQLException {
        return res.getString("name");
    }

    public String getPlace() throws SQLException {
        return res.getString("place");
    }

    public String getAge() throws SQLException {
        return res.getString("age");
    }
    
             
        protected void finalize() {

        try {
             res.close();
             stmt.close();
             conn.close();
             }
             catch (SQLException sqlException) {
                    sqlException.printStackTrace();
              }
           }
}
