package db2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
 
public class SetProp 
{
    public static void main( String[] args )
    {
    	Properties prop = new Properties();
 
    	try {

    		prop.setProperty("url", "192.168.122.93");
                prop.setProperty("port", "50000");
    		prop.setProperty("database", "itimdb");
    		prop.setProperty("dbuser", "db2inst1");
                prop.setProperty("dbpassword", "password");

    		prop.store(new FileOutputStream("/home/gabor/NetBeansProjects/DB2/src/db2/config.properties"), null);
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
    }
}