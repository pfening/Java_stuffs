package controller;

import java.util.ArrayList;
import java.util.List;
import model.DataBean;
import model.DataDAO;
import model.Database;

public class ControllBean {
    
   private static final DataDAO data = new DataDAO();
	
   String addEntry;
   String delEntry;
   String updateEntry;
   DataBean getEntry;
   static List<DataBean> getNameList;
   
   public ControllBean() {

   }

    public String getAddEntry() {
        return addEntry;
    }

    public void setAddEntry(String addEntry) {
        this.addEntry = addEntry;
    }

    public String getDelEntry() {
        return delEntry;
    }

    public void setDelEntry(String delEntry) {
        this.delEntry = delEntry;
    }

    public String getUpdateEntry() {
        return updateEntry;
    }

    public void setUpdateEntry(String updateEntry) {
        this.updateEntry = updateEntry;
    }

    public static DataBean getGetEntry(String nev) throws Exception {
         Database.getInstance().connect();
         DataBean entry = null;
         entry = data.getData(nev);           
         Database.getInstance().disconnect();
         return entry;               
    }

    public void setGetEntry(DataBean getEntry) {
        this.getEntry = getEntry;

    }

    public static List<DataBean> getGetNameList() throws Exception {
                Database.getInstance().connect();
                DataDAO d=new DataDAO();
                List names = new ArrayList<>();
                for (DataBean n:d.getNames()){
                   names.add(n.getName());        
                }
                Database.getInstance().disconnect();
                return names;
    }

    public static void setGetNameList(List<DataBean> getNameList) {
        ControllBean.getNameList = getNameList;
    }

}
