package newpack;

public class NameHandler {
    private String name;
    public NameHandler() {
    name = null;
    }

    public String getName() {
        name=name+"ka";
        return name;
    }

    public void setName(String name) {
        this.name = name+"lol"; 
        System.out.println(name);
    }
    
}
