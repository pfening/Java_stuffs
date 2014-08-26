package recon;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class pre {

        /**
     *
     */
    public static String fs=System.getProperty("file.separator");
	/**
     *
     */
    public static String hd=System.getProperty("user.home");
        /**
     *
     */
    public static String dbfolder=".recon";
        /**
     *
     */
    public static String dbname="recon.db";
        /**
     *
     */
    public static String dbfullpath=hd + fs + dbfolder + fs + dbname;
                    
	public static void main(String[] args) {

		File path = new File(hd + fs + dbfolder);

		if (!path.exists()) {
                    path.mkdir();
                    }
        try{
		File dbfile = new File(dbfullpath);
 		if (dbfile.createNewFile()){
                    String sqla = null;
                    sqla = "CREATE TABLE IF NOT EXISTS customerlist (shortname STRING PRIMARY KEY, description STRING)";
                    selectdb.write(sqla);                                

                    String sqlb = null;
                    sqlb = "CREATE TABLE IF NOT EXISTS serverlist (servername STRING PRIMARY KEY, shortname STRING)";
                    selectdb.write(sqlb);  
                    
                    String sqlc = null;
                    sqlc = "CREATE TABLE IF NOT EXISTS serverdetails (servername STRING PRIMARY KEY, url STRING, mail STRING, erposixpkfile STRING, objectclass STRING, users INTIGER, orphans INTIGER, cbn STRING, privcbn STRING, errormsg STRING, lastrecon STRING)";
                    selectdb.write(sqlc); 
                    }
	}catch (IOException e) {
	}
        
        }
    private static final Logger LOG = Logger.getLogger(pre.class.getName());
        
}