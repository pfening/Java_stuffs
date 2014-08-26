package model;

import java.util.List;

public class Test4 {

    public static void main(String[] args) throws Exception {
        String name="Reka";     
        DataBean entry = controller.ControllBean.getGetEntry(name);
        System.out.println(entry.getName());
        System.out.println(entry.getPlace());
        System.out.println(entry.getAge());
        
        
        List<DataBean> list = controller.ControllBean.getGetNameList();
        for (DataBean n:list){
        System.out.println(n.getName());
        }   
    }
    
}
