/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package food;

import java.io.Serializable;

/**
 *
 * @author gabor
 */
public class TestBean implements Serializable{
   private String Name = null;
   private String place = null;

    public String getPlace() {
        return place + " city";
    }

    public void setPlace(String place) {
        this.place = place;
    }
   private int age = 0;

    public String getName() {
       String name = "Pfening " + Name;
       return name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getAge() {
        return 2013-age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
