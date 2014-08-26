package model;

import java.sql.SQLException;

public class Test5 {
    public static void main(String[] args) throws SQLException, Exception {
        
        DAOBean d=new DAOBean();
        //d.setSelectedName("Laci");
        d.setName("Laci");
        DataBean usr = d.getUser();
        //d.getUser();
        System.out.println(usr.getName());
        System.out.println(usr.getPlace());
        System.out.println(usr.getAge());
        
        //System.out.println(d.getList());       
 
        //d.setName("Gabor3");
        //d.setPlace("Ozd");
        //d.setAge("36");
        //d.setUser();
                
        //d.delUser();
}
}