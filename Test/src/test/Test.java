/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.SQLException;

/**
 *
 * @author gabor
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
 int age=33;
 String Name="Aleksandra";
 String place="Tychy";
 
 TestBean test = new TestBean();
 test.setName(Name);
 test.setAge(age);
 test.setPlace(place);
 System.out.println("Hello " + test.getName() + " you born in " + test.getAge() + " in " + test.getPlace());
 
  String sql = "insert into test values('"+Name+"','"+age+"','"+place+"')";
  selectdb.write(sql);
 
    }
}
