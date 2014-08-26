package model;

import java.sql.SQLException;
import java.util.List;

public class Test3 {

    public static void main(String[] args) throws SQLException, Exception {
        String name="Reka";
        Database.getInstance().connect();
        DataDAO d=new DataDAO();
        System.out.println("name: "+d.getData(name).getName());
        System.out.println("place: "+d.getData(name).getPlace());
        System.out.println("age: "+d.getData(name).getAge());
        
        System.out.println(d.getNames().size());
        for (DataBean n:d.getNames()){
        System.out.println(n.getName());
    }
                
        //d.updateData(new DataBean("Reka1", "3","somewhere1"));
        
        Database.getInstance().disconnect();       
        
        //DataBean entry = controller.Controller.getEntry(name);
        //System.out.println(entry.getName());
        //System.out.println(entry.getPlace());
        //System.out.println(entry.getAge());
        
        //List<DataBean> list = controller.Controller.getNameList();
        //for (DataBean n:list){
        //System.out.println(n.getName());
        //}        
        
        


    }

    
}
    

