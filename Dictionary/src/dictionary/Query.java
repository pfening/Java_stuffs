
package dictionary;

import java.sql.SQLException;

public class Query {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        String sql = null;

        sql = "insert into dict (topic,magyar,angol,cseh) values('teszt','magyar','angol','cseh')";

        selectdb.write(sql);

}
}
