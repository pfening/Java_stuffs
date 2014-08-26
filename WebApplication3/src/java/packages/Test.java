package packages;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
 int age=7;
 String Name="Pamacs";
 String place="Ozd";
 
 QBean test = new QBean();
 test.setName(Name);
 test.setAge(age);
 test.setPlace(place);
 //System.out.println("Hello " + test.getName() + " you born in " + test.getAge() + " in " + test.getPlace());
 
  String sql = "insert into test values('"+Name+"','"+age+"','"+place+"')";
  sdb.write(sql);
 
    }
}
