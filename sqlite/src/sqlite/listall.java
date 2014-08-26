package sqlite;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class listall {
    public static void lista() throws SQLException, ClassNotFoundException {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JList jList1 = new javax.swing.JList();
    DefaultListModel listModel = new DefaultListModel();
    jList1.setModel(listModel);

        String sql = "SELECT name FROM person ";
        ResultSet res = selectdb.read(sql);
   
        while(res.next())
        {
            listModel.addElement(res.getString("name"));
        }
        
    JScrollPane scrollPane = new JScrollPane(jList1);
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.setSize(100, 150);
    frame.setVisible(true); 
    
    }
}
