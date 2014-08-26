
package food;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        String sql = "SELECT * FROM material";
        ResultSet res = selectdb.read(sql);
        MaterialBean m = new MaterialBean();
        while(res.next())
        {
        m.setName(res.getString("name"));
        m.setCategory(res.getString("category"));
        m.setQuantity(res.getInt("quantity"));
        m.setUnit(res.getString("unit"));
        m.setPrice(res.getInt("price"));
        
        System.out.println(m.getName() + m.getCategory() + m.getQuantity() + m.getUnit() + m.getPrice());
        }
}
}
