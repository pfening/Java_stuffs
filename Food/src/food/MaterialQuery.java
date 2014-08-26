package food;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialQuery {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
                
        String sql = "SELECT * FROM material";
        ResultSet res = selectdb.read(sql);
        
        while(res.next())
        {
            String name = res.getString("name");
            String cat = res.getString("category");
            int quant = res.getInt("quantity");
            String uni = res.getString("unit");
            int prc = res.getInt("price");
        
        System.out.println(name+" "+cat+" "+quant+" "+uni+" "+prc);
        }
}
}

