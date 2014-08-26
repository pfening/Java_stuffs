package controll;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modell.DataBean;
import modell.DataDAO;
import modell.Database;

public class Controller {
	
	private static final DataDAO data = new DataDAO();
	
	public static void updateEntry(String name, String age, String place) {                                         
            try {
                Database.getInstance().connect();
                data.updateData(new DataBean(name,age,place));
                Database.getInstance().disconnect();                     
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }       
        
     	public static void addEntry(String name, String age, String place) {                                         
            try {
                Database.getInstance().connect();
                data.addData(new DataBean(name,age,place));
                Database.getInstance().disconnect();                     
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        
         public static void delEntry(String name) {                                         
            try {
                Database.getInstance().connect();
                data.deleteData(new DataBean(name));
                Database.getInstance().disconnect();                     
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
         
         public static DataBean getEntry(String nev) throws SQLException, Exception{
                Database.getInstance().connect();
                DataBean entry = null;
                entry = data.getData(nev);           
                Database.getInstance().disconnect();
                return entry;
    }
         
          public static List<DataBean> getNameList() throws SQLException, Exception{
                Database.getInstance().connect();
                List<DataBean> entry = data.getNames();           
                Database.getInstance().disconnect();
                return entry;

    }   
}
