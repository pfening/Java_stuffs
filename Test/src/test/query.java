
package test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class query {
   private String name;
   private String place;
   private int age;
   String qname = null;
   String qplace = null;
   int qage = 0;
   String rname;
     
   public query() throws SQLException, ClassNotFoundException {
    String sql = "SELECT * FROM test where name='Gabor'";
    ResultSet res = selectdb.read(sql);
    qname = res.getString("name");
    qage=res.getInt("age");
    qplace=res.getString("place");    
   }
      
   public String getName() {
        return qname;
    }
   
    public void setName(String name) {
        this.name = name;        
    }

    public String getPlace() {
        return qplace;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getAge() {
        return qage;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
