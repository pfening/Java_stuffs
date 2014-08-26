package packages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class query {

    static List names =new LinkedList();
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    String sql = "SELECT name FROM test";
    ResultSet res = selectdb.read(sql);
    while(res.next())
        {
    names.add(res.getString("name"));
        }
    System.out.println(names);
    }
}
