package sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;

public class dbRequest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String name = "alex";
        String sql = "SELECT * FROM person WHERE name LIKE '%"+ name +"%'";
        ResultSet res = selectdb.read(sql);
   
        while(res.next())
        {
         System.out.print("ID: " + res.getInt("id"));
         System.out.print(", name: " + res.getString("name"));
         System.out.print(", age: " + res.getString("age") + "\n");
        }
    }
}
