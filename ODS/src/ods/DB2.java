package ods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.naming.directory.Attributes;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

public class DB2 {

 public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException
  {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(2012, Calendar.NOVEMBER, 1);
        String bgmth = df.format(cal.getTime());

        cal.add(Calendar.MONTH, 1);
        String endmth = df.format(cal.getTime());
      
        Class.forName("com.ibm.db2.jcc.DB2Driver");
        String url ="jdbc:db2://192.168.122.116:50000/itimdb";
        
        Connection conn = DriverManager.getConnection(url, "db2inst1", "password"); 
        Statement stmt = conn.createStatement();
        String sql = "SELECT TYPE, REQUESTER_NAME, COMPLETED, SUBJECT, SUBJECT_PROFILE, SUBJECT_SERVICE, RESULT_SUMMARY FROM ITIMUSER.PROCESS WHERE RESULT_SUMMARY = 'SS' and TYPE in ('AP', 'LP') and COMPLETED between ('" + bgmth + "') and ('" + endmth + "') ";
        ResultSet rs = stmt.executeQuery(sql);

        int c=0;
        final Object[][] data = new Object[1000][1000];
        String[] columns = new String[] { "TYPE", "REQUESTER_NAME","COMPLETED","SUBJECT","SUBJECT_PROFILE","SUBJECT_SERVICE" };
        
        while (rs.next()){
            Object type = rs.getObject("TYPE");
            Object reqname = rs.getObject("REQUESTER_NAME");
            Object comp = rs.getObject("COMPLETED");
            Object subject = rs.getObject("SUBJECT");
            Object profile = rs.getObject("SUBJECT_PROFILE");
            Object service = rs.getObject("SUBJECT_SERVICE");

            data[c] = new Object[] { type,reqname,comp,subject,profile,service };

        c++;
        }
        conn.close();
    TableModel model = new DefaultTableModel(data, columns);  
 
    final File file = new File("db2out_test.ods");
    SpreadSheet.createEmpty(model).saveAs(file);
    
    OOUtils.open(file);
  }
}
