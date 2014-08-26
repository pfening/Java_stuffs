package packages;

import java.sql.SQLException;

public class Lekerdez1 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String name="Reka";
        QBean t=new QBean();
        t.setData(name);
        
        //System.out.println(t.getData().getName());
        //System.out.println(t.getData().getPlace());
        //System.out.println(t.getData().getAge());
        System.out.println(t.getName());
        System.out.println(t.getPlace());
        System.out.println(t.getAge());
    }
}
