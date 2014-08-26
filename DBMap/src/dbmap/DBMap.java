package dbmap;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBMap {

    public static void main(String[] args) throws SQLException {

        try {
            String sql = "select name from test";
            ResultSet rs = null;
            
            rs = selectdb.read(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i < columnCount + 1; i++ ){
                String columnName = metaData.getColumnLabel(i);
                System.out.println(columnName);
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBMap.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
