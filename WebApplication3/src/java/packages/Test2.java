package packages;

import java.sql.SQLException;

public class Test2 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        int age=5;
        String Name="gabor2";
        String place="brno";

        WriteBean test = new WriteBean();
        test.setName(Name);
        test.setAge(age);
        test.setPlace(place);
        //test.setEntry();
        //System.out.println(test.getName() +  test.getAge() + test.getPlace());

         //String sql = "insert into test values('"+Name+"','"+age+"','"+place+"')";
         //selectdb.write(sql);
    }
}
