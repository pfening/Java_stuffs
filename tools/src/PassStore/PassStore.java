 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class PassStore extends javax.swing.JFrame {

    public PassStore() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
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
        Open = new javax.swing.JButton();
        App = new javax.swing.JLabel();
        AppBox = new javax.swing.JComboBox();
        tsockCheckBox = new javax.swing.JCheckBox();
        iplabel = new javax.swing.JLabel();
        ipField = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Import = new javax.swing.JMenuItem();
        Export = new javax.swing.JMenuItem();
        Help = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();

        jFileChooser1.setFileFilter(new CustomFilter());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PassStore");

        jList1.setModel(listModel);

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

        Open.setText("Open");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenActionPerformed(evt);
            }
        });

        App.setText("Application");

        AppBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Terminal", "RDP", "Tox", "None" }));
        AppBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AppBoxActionPerformed(evt);
            }
        });

        tsockCheckBox.setText("Tsocks");

        iplabel.setText("IP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pass)
                    .addComponent(uid)
                    .addComponent(hostname)
                    .addComponent(description)
                    .addComponent(App)
                    .addComponent(iplabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UIDField)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(HostnameField)
                    .addComponent(PassField)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(AppBox, 0, 107, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tsockCheckBox))
                    .addComponent(ipField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Open, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(New, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Delete, New, Open, Save, Update});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Save)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Update)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(New)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Delete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Open))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hostname)
                                    .addComponent(HostnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(iplabel)
                                    .addComponent(ipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
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
                                    .addComponent(PassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(App)
                                    .addComponent(AppBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tsockCheckBox))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Delete, New, Open, Save, Update});

        jMenu1.setText("File");

        Import.setText("Import CSV");
        Import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportActionPerformed(evt);
            }
        });
        jMenu1.add(Import);

        Export.setText("Export CSV");
        Export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportActionPerformed(evt);
            }
        });
        jMenu1.add(Export);

        Help.setText("Help");
        Help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelpActionPerformed(evt);
            }
        });
        jMenu1.add(Help);

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jMenu1.add(Exit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
                String sql = null;
                try {
                    sql = "insert into list values('"+ HostnameField.getText() +"','"+ ipField.getText() +"','"+ UIDField.getText() +"','"+ Cripto.encrypt(PassField.getText()) +"','"+ DescriptionField.getText() +"','"+ AppBox.getSelectedItem() +"','"+ tsockCheckBox.isSelected() +"')";
                } catch (GeneralSecurityException ex) {
                    Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
                }

                boolean emp = HostnameField.getText().isEmpty();
                System.out.println(emp);
                
                if (Boolean.FALSE.equals(emp)) {

                selectdb.write(sql);

        	DefaultListModel listModel = new DefaultListModel();
		listModel = (DefaultListModel) jList1.getModel();
                
                boolean dup = listModel.contains(HostnameField.getText());
                
                if (Boolean.FALSE.equals(dup)) {
                    listModel.addElement(HostnameField.getText());
                }
                else {
                    JOptionPane.showMessageDialog(null, "Hostname must be uniq");
                }
                }
    }//GEN-LAST:event_SaveActionPerformed

    private void NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewActionPerformed
                HostnameField.setText("");
                ipField.setText("");
                UIDField.setText("");
                PassField.setText("");
                DescriptionField.setText("");
                AppBox.setSelectedItem("");
                tsockCheckBox.setSelected(false);
    }//GEN-LAST:event_NewActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
		String sql = null;
                try {
                    sql = "update list set pass='"+ Cripto.encrypt(PassField.getText()) +"' where hostname = '"+ HostnameField.getText() +"'";
                } catch (GeneralSecurityException ex) {
                    Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
                }
        	selectdb.write(sql);
    }//GEN-LAST:event_UpdateActionPerformed

    @SuppressWarnings("empty-statement")
    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
                
                DefaultListModel listModel = new DefaultListModel();
          	listModel = (DefaultListModel) jList1.getModel();
               	int ind = jList1.getSelectedIndex();
        	if (ind >= 0) {
            	listModel.remove(ind);
		String sql = "DELETE FROM list WHERE hostname='"+ HostnameField.getText() +"'";
		selectdb.write(sql);
                HostnameField.setText("");
                ipField.setText("");
                UIDField.setText("");
                PassField.setText("");
                DescriptionField.setText("");
                AppBox.setSelectedItem("");
                tsockCheckBox.setSelected(false);
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

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenActionPerformed
        String prog = (String) AppBox.getSelectedItem();
        boolean tso = tsockCheckBox.isSelected();
        
        if ( true == tso ) {
            if ("Terminal".equals(prog) ) {
            try {
             OpenVia.tterminal(UIDField.getText(),ipField.getText());
         } catch (IOException ex) {
             Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
         }
        }  
        if ("RDP".equals(prog) ) {
            try {
             OpenVia.trdp(UIDField.getText(), ipField.getText(), PassField.getText());
         } catch (IOException ex) {
             Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
        } 
        
        if ( false == tso ) {
        if ("Terminal".equals(prog) ) {
            try {
             OpenVia.terminal(UIDField.getText(),ipField.getText());
         } catch (IOException ex) {
             Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
         }
        }  
        if ("RDP".equals(prog) ) {
            try {
             OpenVia.rdp(UIDField.getText(), ipField.getText(), PassField.getText());
         } catch (IOException ex) {
             Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
         }
        }  
        if ("Tox".equals(prog) ) {
            try {
             OpenVia.tox(UIDField.getText(), ipField.getText(),PassField.getText());
         } catch (IOException ex) {
             Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
         }
        }  
        }

    }//GEN-LAST:event_OpenActionPerformed

    private void AppBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AppBoxActionPerformed
        if ("Terminal".equals((String) AppBox.getSelectedItem()) ) {tsockCheckBox.setEnabled(true);}
        if ("RDP".equals((String) AppBox.getSelectedItem()) ) {tsockCheckBox.setEnabled(true);}
        if ("Tox".equals((String) AppBox.getSelectedItem()) ) {tsockCheckBox.setEnabled(false);}
        if ("None".equals((String) AppBox.getSelectedItem()) ) {tsockCheckBox.setEnabled(false);}
    }//GEN-LAST:event_AppBoxActionPerformed

    private void ImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportActionPerformed
                
        int returnVal = jFileChooser1.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = jFileChooser1.getSelectedFile();
        String csvFile = file.getAbsolutePath();
          
          try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ( (line=br.readLine()) != null)
            {
             String[] st = line.split(";"); 
             String sql;
                  try {
                      sql = "insert into list values('"+st[0]+"','"+st[1]+"','"+st[2]+"','"+Cripto.encrypt(st[3])+"','"+st[4]+"','"+st[5]+"','"+st[6]+"')";
                      selectdb.write(sql);
                  } catch (GeneralSecurityException ex) {
                      Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
                  }
             
            }
        }
            catch (IOException ex) {          
                Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
            }          
        }
        String sql = "DELETE FROM list WHERE hostname='Hostname'";
        selectdb.write(sql);
        UpdateList();
    }//GEN-LAST:event_ImportActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void ExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportActionPerformed
                
        int returnVal = jFileChooser1.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter outFile = null;
     
                File file = jFileChooser1.getSelectedFile();
                String expFile = file.getAbsolutePath();
                  
                String sql = "SELECT * FROM list";
                ResultSet res = null;
                res = selectdb.read(sql);
                outFile = new FileWriter(expFile);
                try (PrintWriter out = new PrintWriter(outFile)) {
                    out.println("Hostname;IP;UserID;Password;Description;Application(Terminal,RDP,Tox,None);tsocks(true/false)");
                    while(res.next())
                    {
                        String input=res.getString("hostname") +";"+ res.getString("ip") +";"+ res.getString("uid") +";"+ Cripto.decrypt(res.getString("pass")) +";"+ res.getString("description") +";"+ res.getString("app") +";"+ Boolean.valueOf(res.getString("tsocks"));
                        out.println(input);
                    }          
                } catch (    GeneralSecurityException | IOException | SQLException ex) {          
                    Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
                }          
            } catch (    IOException | SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
            }          
        }
    }//GEN-LAST:event_ExportActionPerformed

    private void HelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HelpActionPerformed
        JOptionPane.showMessageDialog(rootPane, "To receive import template, please export it via Export CSV menupoint.");
    }//GEN-LAST:event_HelpActionPerformed
    
    /**
     *
     * @param evt
     */
    public void jList1MouseClicked(java.awt.event.MouseEvent evt) {
		String index = (String) jList1.getSelectedValue();
        	String sql = "SELECT * FROM list WHERE hostname = '"+ index +"'";
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
                ipField.setText(res.getString("ip"));
                DescriptionField.setText(res.getString("description"));
                UIDField.setText(res.getString("uid"));
                        try {
                            PassField.setText(Cripto.decrypt(res.getString("pass")));
                        } catch (GeneralSecurityException | IOException ex) {
                            Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
                        }
                AppBox.setSelectedItem(res.getString("app"));
                tsockCheckBox.setSelected(Boolean.valueOf(res.getString("tsocks")));
            	}
        	} catch (SQLException ex) {
            	Logger.getLogger(PassStore.class.getName()).log(Level.SEVERE, null, ex);
        	}
            }

    private void UpdateList() {
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

        jList1.setModel(listModel);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(jList1);
    }
    
    class CustomFilter extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept(File file) {
            return file.isDirectory() || file.getAbsolutePath().endsWith(".csv");
        }
        @Override
        public String getDescription() {
            return "CSV documents (*.csv)";
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
            @Override
            public void run() {
                new PassStore().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel App;
    private javax.swing.JComboBox AppBox;
    private javax.swing.JButton Delete;
    private javax.swing.JTextArea DescriptionField;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem Export;
    private javax.swing.JMenuItem Help;
    private javax.swing.JTextField HostnameField;
    private javax.swing.JMenuItem Import;
    private javax.swing.JButton New;
    private javax.swing.JButton Open;
    private javax.swing.JTextField PassField;
    private javax.swing.JButton Save;
    private javax.swing.JTextField UIDField;
    private javax.swing.JButton Update;
    private javax.swing.JLabel description;
    private javax.swing.JLabel hostname;
    private javax.swing.JTextField ipField;
    private javax.swing.JLabel iplabel;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel pass;
    private javax.swing.JCheckBox tsockCheckBox;
    private javax.swing.JLabel uid;
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(PassStore.class.getName());
}
