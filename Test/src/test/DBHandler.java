package test;

public class DBHandler implements java.io.Serializable{
   private String name;
   private String place;
   private int age;
   
   public DBHandler(){
       name=null;
       place=null;
       age=0;
   }
   
  public DBHandler(String name, String place, int age){  
        this.name = name;  
        this.place = place;  
        this.age = age;  
  }  

    public String getName() {
       name = "Pfening " + name;
       return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place + " city";
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getAge() {
        return 2013-age;
    }

    public void setAge(int age) {
        this.age = age;
    }
   
}
