package dbmap;

import java.sql.*;     
   
public class QBean{      
    static String dbpath ="jdbc:sqlite:/home/gabor/test.db";
    private String name;
    private String place;
    private int age;    
    private Connection conn;
    private Statement stmt;
    private ResultSet res;

    public String getName() throws SQLException {
        return res.getString("name");
    }

    public String getPlace() throws SQLException {
        return res.getString("place");
    }

    public String getAge() throws SQLException {
        return res.getString("age");
    }

    public void setData(String name) throws SQLException, ClassNotFoundException {        
    this.name=name;
    String sql = "SELECT * FROM test where name='"+name+"'"; 
    
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
    }    
}  