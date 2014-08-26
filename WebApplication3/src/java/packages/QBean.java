package packages;

import java.sql.*;     
import java.util.LinkedList;
import java.util.List;
   
public class QBean{  
    private String sql;
    private ResultSet res;
    private String data;
    private String name;
    private String place;
    private int age;
    private List names =new LinkedList();
   
    public String getName() throws ClassNotFoundException, SQLException {
        return res.getString("name");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() throws ClassNotFoundException, SQLException {
        return res.getString("place");
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getAge() throws ClassNotFoundException, SQLException {
        return res.getInt("age");
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getData(){  
            return data;
    }  
   
    public void setData(String name) throws SQLException, ClassNotFoundException {        
    this.name=name;
    sql = "SELECT * FROM test where name='"+name+"'";
    res = sdb.read(sql);
    }

    public List getNames() throws SQLException, ClassNotFoundException {
    sql = "SELECT name FROM test";
    res = sdb.read(sql);
    while(res.next())
        {
    names.add(res.getString("name"));
        }
        return names;
    }
}  