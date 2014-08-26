 

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
    public static String dbfolder=".passstore";
        /**
     *
     */
    public static String dbname="password.db";
        /**
     *
     */
    public static String dbfullpath=hd + fs + dbfolder + fs + dbname;
                
        /**
     *
     */
    public static final String pass = (String)JOptionPane.showInputDialog("Please enter your master password");
    
	public static void main(String[] args) {

		File path = new File(hd + fs + dbfolder);

		if (!path.exists()) {
                    path.mkdir();
                    }
        try{
		File dbfile = new File(dbfullpath);
 		if (dbfile.createNewFile()){
                    String sql = null;
                    sql = "CREATE TABLE IF NOT EXISTS list (hostname STRING PRIMARY KEY, ip STRING, uid STRING, pass STRING, description STRING, app STRING, tsocks STRING)";
                    selectdb.write(sql);                                
                    }
	}catch (IOException e) {
	}
        }
    private static final Logger LOG = Logger.getLogger(pre.class.getName());
        
}