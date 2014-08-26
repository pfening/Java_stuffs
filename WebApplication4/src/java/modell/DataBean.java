package modell;

public class DataBean {
    private String name;
    private String place;
    private String age;  
    
    public DataBean(){
    
    }
    
    public DataBean(String name) {
		this.name = name;
    }
    
    public DataBean(String name, String place, String age) {
		this.name = name;
		this.place = place;
		this.age = age;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    
}
