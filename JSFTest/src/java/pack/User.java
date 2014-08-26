package pack;

import java.awt.event.ActionEvent;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author gabor
 */
@ManagedBean
@RequestScoped
public class User {

    private String name;
    private String msg;
    private Map<String, String> params;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public User() { 
    }
    
    public void check(){
        if (name.equalsIgnoreCase("gabor")){
            msg="correct";
        }else{
            msg="incorrect";
        }
    }
    
       
}
