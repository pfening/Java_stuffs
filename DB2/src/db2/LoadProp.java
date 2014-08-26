package db2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
 
public class LoadProp 
{
    public static void main( String[] args )
    {
    	Properties prop = new Properties();
 
    	try {
               //load a properties file
    		prop.load(new FileInputStream("/home/gabor/NetBeansProjects/DB2/src/db2/config.properties"));
 
                System.out.println("url: " + prop.getProperty("url"));
                System.out.println("port: " + prop.getProperty("port"));
                System.out.println("database: " + prop.getProperty("database"));
    		System.out.println("dbuser: " + prop.getProperty("dbuser"));
    		System.out.println("password: " + prop.getProperty("dbpassword"));
 
                String dblink = "jdbc:db2://"+prop.getProperty("url")+":"+prop.getProperty("port")+"/"+prop.getProperty("database");
                String conn = prop.getProperty("dbuser") +" " + prop.getProperty("dbpassword");
                System.out.println(dblink);
                System.out.println(conn);
                        
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
 
    }
}