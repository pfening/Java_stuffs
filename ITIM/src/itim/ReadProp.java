package itim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class ReadProp {

        public static void main(String[] arg) throws IOException{
            
        String file = null;
        
        System.out.print("Enter the properies file path: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        file = br.readLine();
             
        Properties prop = new Properties(); 
    	
        FileReader reader = new FileReader(file);
	Properties properties = new Properties();
	properties.load(reader);

        Object[] lista=properties.stringPropertyNames().toArray();

            for(Object elem:lista){
            System.out.println(elem.toString()+" = "+properties.getProperty(elem.toString()));
        }   
    }    
}
