package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class DataDAOlist {
    
    private String selectedName;
    private String name;
    private String place;
    private String age;  
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSelectedName() {        
        return selectedName;
    }

    public void setSelectedName(String selectedName) {
        this.selectedName = selectedName;       
    }

    public List getList() throws SQLException, Exception{
                Database.getInstance().connect();
                DataDAO d=new DataDAO();
                List names = new ArrayList<>();
                for (DataBean n:d.getNames()){
                   names.add(n.getName());        
                }
                Database.getInstance().disconnect();
                return names;
    }
    
    public DataBean getUser() throws SQLException, Exception{
                Database.getInstance().connect();
                DataDAO d=new DataDAO();
                DataBean data = d.getData(selectedName); 
                Database.getInstance().disconnect();
                return data;              
    }  
    
    public void addNewUser() throws Exception {
                Database.getInstance().connect();
                DataDAO d=new DataDAO();
                DataBean entry = new DataBean(name,place,age);
                d.addData(entry);
                Database.getInstance().disconnect();
    }
            
    public void delUser() throws Exception {
                Database.getInstance().connect();
                DataDAO d=new DataDAO();
                d.deleteData(selectedName);
                Database.getInstance().disconnect();
    }
    
    public DataDAOlist() {
    }   
    
}
