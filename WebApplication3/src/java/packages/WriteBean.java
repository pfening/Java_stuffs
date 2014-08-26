package packages;
   
import java.sql.SQLException;

public class WriteBean{  
    private String name;
    private String place;
    private int age;
   private String entry;

    public void setEntry(String n) throws ClassNotFoundException, SQLException {
        String sqlw = "insert into test values('"+n+"')";
        System.out.println(sqlw);
        sdb.write(sqlw);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
   

}  