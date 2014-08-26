
package test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        String sql = "SELECT * FROM test";
        ResultSet res = selectdb.read(sql);
        TestBean t = new TestBean();
        while(res.next())
        {
        t.setName(res.getString("name"));
        t.setAge(Integer.parseInt(res.getString("age")));
        t.setPlace(res.getString("place"));
        System.out.println("Hello " + t.getName() + " you born in " + t.getAge() + " in " + t.getPlace());
        }
}
}
