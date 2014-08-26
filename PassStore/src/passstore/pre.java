package passstore;

import java.io.File;
import java.io.IOException;

public class pre {

	public static void main(String[] args) {
	
	String fs=System.getProperty("file.separator");
	String hd=System.getProperty("user.home");

		File path = new File(hd + fs + ".passstore");

		if (!path.exists()) {
			if (path.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
        try{
		File dbfile = new File(hd + fs + ".passstore" + fs + "passwd.db");
 		if (dbfile.createNewFile()){
				System.out.println("File is created!");
                                String sql = null;
                                sql = "CREATE TABLE IF NOT EXISTS list (hostname STRING PRIMARY KEY, description STRING, uid STRING, pass STRING)";
                                selectdb.write(sql);                                
			}else{
				System.out.println("File already exists.");
                                }
	}catch (IOException e) {
	}
        }
}