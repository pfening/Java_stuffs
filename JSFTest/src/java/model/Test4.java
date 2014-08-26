package model;

import static controller.ControllBean.getGetNameList;

public class Test4 {

    public static void main(String[] args) throws Exception {
        String name="Reka";     
        DataBean entry = controller.ControllBean.getGetEntry(name);
        System.out.println(entry.getName());
        System.out.println(entry.getPlace());
        System.out.println(entry.getAge());

        System.out.println(getGetNameList());

    }
    
}
