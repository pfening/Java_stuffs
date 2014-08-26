package test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class dbRequest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM test";
        ResultSet res = dbBean.read(sql);
   
        while(res.next())
        {
         System.out.println("name: " + res.getString("name"));
         System.out.println("age: " + res.getInt("age"));
         System.out.println("place: " + res.getString("place"));
        }
    }
}
