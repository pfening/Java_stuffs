package arduino;

import java.io.BufferedReader;
import java.io.IOException;
 
public class SerialRead{
 public static BufferedReader input;

 public static void main(String[] ag) {
  try {
 SerialClass obj = new SerialClass();
 obj.initialize();
 input = SerialClass.input;
  
 String inputLine=input.readLine();
 System.out.println(inputLine);

 }
 catch(IOException e){} 
 }
}
