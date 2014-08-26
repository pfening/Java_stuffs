
package test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Handler implements java.io.Serializable{
   private String name;
   private String place;
   private int age;
   private String qname;
   private String sql=null;

    public void getData(){
    sql = "SELECT * FROM test where name='"+name+"'";
    }  
   
    public void setData(String name) {        
    this.name=name;
    }
   
   
   
   
    //public String getQname() {
    //    return qname;
    //}

    //public void setQname(String qname) {
    //    this.qname = qname;
    //    System.out.println(qname);
    //    sql = "SELECT * FROM test where name='Alex'";
    //}
   
    
   
   public Handler(){
       name=null;
       place=null;
       age=0;
   }
   
  public Handler(String name, String place, int age){  
        this.name = name;  
        this.place = place;  
        this.age = age;  
  }  

    public String getName() throws SQLException, ClassNotFoundException {
       return selectdb.read(sql).getString("name");
    }
    
    public String getPlace() throws SQLException, ClassNotFoundException {
        return selectdb.read(sql).getString("place");
    }
    
    public int getAge() throws SQLException, ClassNotFoundException {
        return selectdb.read(sql).getInt("age");
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