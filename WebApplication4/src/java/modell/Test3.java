package modell;

import static controll.Controller.getNameList;
import java.sql.SQLException;
import java.util.List;

public class Test3 {

    public static void main(String[] args) throws SQLException, Exception {

        
        List<DataBean> list = getNameList();

        for ( DataBean n:list){
        System.out.println(n.getName());
        }        
        
        


    }

    
}
    

