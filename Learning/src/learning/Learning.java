package learning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Learning {
    
public static BufferedReader br = null;
public static String text = null;
public static String path ="/home/gabor/Java/test.exa";
    public static void main(String[] args) throws FileNotFoundException, IOException {   
        
        String content = new Scanner(new File(path)).useDelimiter("\\Z").next();
        //System.out.println(content);
        String[] ent=content.split("#");
              System.out.println("Question number is: "+ent[1]);
              System.out.println("Question is: "+ent[2]);
              System.out.println("A: "+ent[3]);
              System.out.println("B: "+ent[4]);
              System.out.println("C: "+ent[5]);
              System.out.println("D: "+ent[6]);
              System.out.println("Answer is: "+ent[7]);   

   }
  
}

