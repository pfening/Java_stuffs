package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataDAOuser {    
    
        private String selectedName;
    private DataBean user;

    public void setUser(DataBean user) {
        this.user = user;
    }
 

    public String getSelectedName() {        
        return selectedName;
    }

    public void setSelectedName(String selectedName) {
        this.selectedName = selectedName;       
    }
        
        public DataBean getUser() throws SQLException, Exception{
            DataBean entry = null;
            Connection conn = Database.getInstance().getConnection();		
		PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM test where name=?");
		selectStatement.setString(1, selectedName);		
		ResultSet result = selectStatement.executeQuery();		
		while(result.next()) {
			String name = result.getString("name");
			String place = result.getString("place");
			String age = result.getString("age");			
			entry = new DataBean(name,place,age);
		}		
		result.close();
		selectStatement.close();                
                return entry;             
        }  
}
