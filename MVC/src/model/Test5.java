package model;

import java.sql.SQLException;
import java.util.List;

public class Test5 {
    public static void main(String[] args) throws SQLException, Exception {

        Database.getInstance().connect();
        DataDAO d=new DataDAO();
        d.setSelectedName("Gabor");
        DataBean data = d.getUser();
        
        System.out.println(data.getPlace());
        System.out.println(data.getName());
        System.out.println(data.getAge());
        
        List<DataBean> list = d.getNames();
        for (DataBean n:list){
        System.out.println(n.getName());
        }
        
        Database.getInstance().disconnect();
}
}