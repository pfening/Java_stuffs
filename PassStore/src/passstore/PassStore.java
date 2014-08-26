package passstore;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class PassStore extends javax.swing.JFrame {

    public PassStore() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DefaultListModel listModel = new DefaultListModel();

        String sql = "SELECT hostname FROM list ";
        ResultSet res = null;
        try {
            res = selectdb.read(sql);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(res.next())
            {
                listModel.addElement(res.getString("hostname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        jList1 = new javax.swing.JList();
        hostname = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        HostnameField = new javax.swing.JTextField();
        Save = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        New = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        PassField = new javax.swing.JTextField();
        UIDField = new javax.swing.JTextField();
        uid = new javax.swing.JLabel();
        pass = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DescriptionField = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(listModel);

        int size = listModel.getSize();

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        hostname.setText("Hostname");

        description.setText("Description");

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        New.setText("New");
        New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewActionPerformed(evt);
            }
        });

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        uid.setText("User ID");

        pass.setText("Password");

        DescriptionField.setColumns(20);
        DescriptionField.setLineWrap(true);
        DescriptionField.setRows(2);
        DescriptionField.setWrapStyleWord(true);
        jScrollPane2.setViewportView(DescriptionField);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pass)
                    .addComponent(uid)
                    .addComponent(hostname)
                    .addComponent(description))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UIDField)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(HostnameField)
                    .addComponent(PassField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Save)
                    .addComponent(Update)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(New)
                        .addComponent(Delete)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Delete, New, Save, Update});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hostname)
                            .addComponent(HostnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(description)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(uid)
                            .addComponent(UIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pass)
                            .addComponent(PassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Save)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(New)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Delete))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Delete, New, Save, Update});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        	String passwd = (String)JOptionPane.showInputDialog("Please enter your encrypt code");
                String sql = null;
                try {
                    sql = "insert into list values('"+ HostnameField.getText() +"','"+ DescriptionField.getText() +"','"+ UIDField.getText() +"','"+ Cripto.encrypt(PassField.getText(),passwd) +"')";
                } catch (GeneralSecurityException ex) {
                    Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
                }
        	selectdb.write(sql);

        	DefaultListModel listModel = new DefaultListModel();
		listModel = (DefaultListModel) jList1.getModel();
        	listModel.addElement(HostnameField.getText());
    }//GEN-LAST:event_SaveActionPerformed

    private void NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewActionPerformed
                HostnameField.setText("");
        	DescriptionField.setText(""); 
                UIDField.setText("");
                PassField.setText("");
    }//GEN-LAST:event_NewActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        	String passwd = (String)JOptionPane.showInputDialog("Please enter your encrypt code");
		String sql = null;
                try {
                    sql = "update list set pass='"+ Cripto.encrypt(PassField.getText(),passwd) +"' where name='"+ HostnameField.getText() +"'";
                } catch (GeneralSecurityException ex) {
                    Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
                }
        	selectdb.write(sql);
    }//GEN-LAST:event_UpdateActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
                DefaultListModel listModel = new DefaultListModel();
          	listModel = (DefaultListModel) jList1.getModel();
               	int ind = jList1.getSelectedIndex();
        	if (ind >= 0) {
            	listModel.remove(ind);
		String sql = "DELETE FROM list WHERE hostname='"+ HostnameField.getText() +"'";
		selectdb.write(sql);
		HostnameField.setText("");
        	PassField.setText("");
        	};
         
       		int size = listModel.getSize();
            	if (size == 0) { 
                Delete.setEnabled(false);
 
            	} else { 
                if (ind == listModel.getSize()) {
                    ind--;
                }
                jList1.setSelectedIndex(ind);
                jList1.ensureIndexIsVisible(ind);
            	}
    }//GEN-LAST:event_DeleteActionPerformed
    
    public void jList1MouseClicked(java.awt.event.MouseEvent evt) {
		String passwd = (String)JOptionPane.showInputDialog("Please enter your decrypt code");
		String index = (String) jList1.getSelectedValue();
        	String sql = "SELECT * FROM list WHERE hostname LIKE '%"+ index +"%'";
        	ResultSet res = null;
        	try {
            	res = selectdb.read(sql);
        	} catch (SQLException | ClassNotFoundException ex) {
            	Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
        	}
        	try {
           	while(res.next())
            	{
                HostnameField.setText(res.getString("hostname"));
                DescriptionField.setText(res.getString("description"));
                UIDField.setText(res.getString("uid"));
                        try {
                            PassField.setText(Cripto.decrypt(res.getString("pass"),passwd));
                        } catch (GeneralSecurityException | IOException ex) {
                            Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
                        }
            	}
        	} catch (SQLException ex) {
            	Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
        	}
            }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        pre.main(args);
   
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PassStore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PassStore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PassStore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PassStore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PassStore().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JTextArea DescriptionField;
    private javax.swing.JTextField HostnameField;
    private javax.swing.JButton New;
    private javax.swing.JTextField PassField;
    private javax.swing.JButton Save;
    private javax.swing.JTextField UIDField;
    private javax.swing.JButton Update;
    private javax.swing.JLabel description;
    private javax.swing.JLabel hostname;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel pass;
    private javax.swing.JLabel uid;
    // End of variables declaration//GEN-END:variables
}
