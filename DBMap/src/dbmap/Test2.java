package dbmap;

import java.sql.SQLException;

public class Test2 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        int age=3;
        String Name="Alex1";
        String place="Tisnov";

        DBBean test = new DBBean();
        test.setName(Name);
        test.setAge(age);
        test.setPlace(place);
        test.setUser();
        //System.out.println(test.getName() +  test.getAge() + test.getPlace());

    }
}
