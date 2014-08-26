package PassStore_2;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

public class pre {

        public static String fs=System.getProperty("file.separator");
	public static String hd=System.getProperty("user.home");
        public static String dbfolder=".passstore";
        public static String dbname="passwd_e.db";
        public static String dbfullpath=hd + fs + dbfolder + fs + dbname;
                
        public static final String pass = (String)JOptionPane.showInputDialog("Please enter your master password");
    
	public static void main(String[] args) {

		File path = new File(hd + fs + dbfolder);

		if (!path.exists()) {
			if (path.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
        try{
		File dbfile = new File(dbfullpath);
 		if (dbfile.createNewFile()){
				System.out.println("File is created!");
                                String sql = null;
                                sql = "CREATE TABLE IF NOT EXISTS list (hostname STRING PRIMARY KEY, description STRING, uid STRING, pass STRING, app STRING, tsocks STRING)";
                                selectdb.write(sql);                                
			}else{
				System.out.println("File already exists.");
                                }
	}catch (IOException e) {
	}
        }
}