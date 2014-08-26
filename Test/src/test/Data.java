package test;

import java.sql.*;     

   
public class Data{  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public DBHandler data = new DBHandler();
    private String name;
    private String place;
    private int age;

@SuppressWarnings("empty-statement")
public DBHandler getData() throws SQLException, ClassNotFoundException{  

    String sql = "SELECT * FROM test where name='"+name+"'";
    ResultSet res = selectdb.read(sql);
    data.setName(res.getString("name"));
    data.setAge(res.getInt("age"));
    data.setPlace(res.getString("place"));;  
    return data;
}  
   
    public void setData(String name) {        
    this.name=name;
    }
}  