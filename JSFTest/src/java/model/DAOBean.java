package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class DAOBean {

    private String selectedName;
    private String name;
    private String place;
    private String age; 
    private DataBean user;
    

    public String getSelectedName() {
        return selectedName;
    }

    public void setSelectedName(String selectedName) {
        this.selectedName = selectedName;
    }

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

    public DataBean getUser() throws Exception {
        Database.getInstance().connect();
        DataDAO d=new DataDAO();
        user = d.getData(selectedName); 
        Database.getInstance().disconnect();
        return user;        
    }

    public void setUser() throws Exception { 
        DataBean newuser=null;
        Database.getInstance().connect();
        DataDAO d=new DataDAO();
        newuser = new DataBean(name,place,age);      
        d.addData(newuser);
        Database.getInstance().disconnect();
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
    
    public void delUser() throws Exception {
                Database.getInstance().connect();
                DataDAO d=new DataDAO();
                d.deleteData(selectedName);
                Database.getInstance().disconnect();
    }
    
    
    public DAOBean() {
    }
    
}
