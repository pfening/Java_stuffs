package newpack;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "helloWorld1", eager = true)
public class HelloWorld1 {
   public HelloWorld1() {
      System.out.println("HelloWorld started!");
   }
   public String getMessage() {
      return "Hello World!";
   }
}
