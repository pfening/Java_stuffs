package dbmap;

import java.sql.SQLException;

public class Lekerdez1 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String name="Gabor";
        DBBean t=new DBBean();
        t.setUserQuery(name);

        System.out.println(t.getName());
        System.out.println(t.getPlace());
        System.out.println(t.getAge());
    }
}
