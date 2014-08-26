package dbmap;
   
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WBean{  
    static String dbpath ="jdbc:sqlite:/home/gabor/test.db";
    private String name;
    private String place;
    private int age;    
    private Connection conn;
    private Statement stmt;
    private ResultSet res;

    public void setData() throws ClassNotFoundException, SQLException {
      String sql = "insert into test values('"+name+"','"+age+"','"+place+"')";
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(dbpath);
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
      stmt.close();
      conn.close();
    }


    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public int getAge() {
        return age;
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


}  