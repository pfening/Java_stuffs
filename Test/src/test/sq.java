/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import test.Handler;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sq {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        //String sql = "SELECT * FROM test";
        //ResultSet res = selectdb.read(sql);
        
        Handler t = new Handler();
        t.setData("Aleksandra");
        //t.setName(res.getString("name"));
        //t.setAge(Integer.parseInt(res.getString("age")));
        //t.setPlace(res.getString("place"));
        System.out.println("Hello " + t.getName() + " you are " + t.getAge() + " old, and you born in " + t.getPlace());

}
}
