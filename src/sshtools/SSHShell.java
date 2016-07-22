/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sshtools;

import java.awt.Color;
import java.awt.Font;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author di3sdn
 */
public class SSHShell extends javax.swing.JFrame {

    private static String username = "";
    private static String password = "";
    /**
     * Creates new form SSHShell
     */
    public SSHShell() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jDialog1 = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        _UserName = new javax.swing.JTextField();
        _Password = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        _ServerName = new javax.swing.JTextField();
        _Kommando = new javax.swing.JTextField();
        Submit = new javax.swing.JButton();
        _Suche1 = new javax.swing.JTextField();
        lblServer = new javax.swing.JLabel();
        lblSuchen = new javax.swing.JLabel();
        lblKommando = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        _Output = new javax.swing.JTextPane();
        lblSuchen1 = new javax.swing.JLabel();
        _Suche2 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jToolBar1.setRollover(true);

        jDialog1.setModal(true);
        jDialog1.setResizable(false);

        jLabel3.setText("Username");

        jLabel4.setText("Password");

        jButton2.setText("Set Username / Password");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dssh");
        setMinimumSize(new java.awt.Dimension(800, 500));

        _ServerName.setNextFocusableComponent(_Kommando);
        _ServerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _ServerNameActionPerformed(evt);
            }
        });

        _Kommando.setNextFocusableComponent(Submit);
        _Kommando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _KommandoActionPerformed(evt);
            }
        });

        Submit.setText("Submit");
        Submit.setNextFocusableComponent(_Suche1);
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        _Suche1.setNextFocusableComponent(_ServerName);
        _Suche1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _Suche1ActionPerformed(evt);
            }
        });

        lblServer.setText("Server:");

        lblSuchen.setText("Suche 1");

        lblKommando.setText("Kommando");

        jScrollPane2.setViewportView(_Output);

        lblSuchen1.setText("Suche 2");

        _Suche2.setNextFocusableComponent(_ServerName);
        _Suche2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _Suche2ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Logon");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Add Username/Password");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblKommando, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                            .addComponent(lblServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(_ServerName, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(lblSuchen, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(_Suche1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(lblSuchen1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(_Suche2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE))
                            .addComponent(_Kommando))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Submit)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblServer)
                            .addComponent(_ServerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSuchen)
                            .addComponent(_Suche1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSuchen1)
                                .addComponent(_Suche2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKommando)
                            .addComponent(_Kommando)))
                    .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        // TODO add your handling code here:
         doExecute();
    }//GEN-LAST:event_SubmitActionPerformed

    private void _KommandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__KommandoActionPerformed
        // TODO add your handling code here:
        doExecute();
    }//GEN-LAST:event__KommandoActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
        //System.out.println("Debug 1");
        jDialog1.show();
        jDialog1.setBounds(200,200,200,200);
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        username = _UserName.getText();
        password = _Password.getText();
        jDialog1.hide();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        //System.out.println("Debug 2");
        jDialog1.show();
        jDialog1.setBounds(200,200,200,200);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void _ServerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__ServerNameActionPerformed
        // TODO add your handling code here:
        doExecute();
    }//GEN-LAST:event__ServerNameActionPerformed

    private void _Suche1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__Suche1ActionPerformed
        // TODO add your handling code here:
        doStyle();
    }//GEN-LAST:event__Suche1ActionPerformed

    private void _Suche2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__Suche2ActionPerformed
        // TODO add your handling code here:
        doStyle();
    }//GEN-LAST:event__Suche2ActionPerformed

    private void doExecute() {
        if (username == null || username.length()<3 || password == null || password.length()<2) {
            _Output.setText("Username / Password nicht gesetzt..........");
        } else {
            _Output.setText("Loading.....");
            String response = SSHTools.executeSSH(_ServerName.getText(), username, password, _Kommando.getText());
            _Output.setText(response);
            doStyle();
        }
    }
    
    private void doStyle() {
        System.out.println("doStyle-001");
        Font font = new Font("Courier New", Font.PLAIN, 13);
        StyledDocument doc = _Output.getStyledDocument();
        _Output.setFont(font);
        Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        doc.setCharacterAttributes(0,doc.getLength(), defaultStyle,true);

        
        String text = _Output.getText();
        
        //Suche 1
        String string1 = _Suche1.getText();
        System.out.println("doStyle-003");
        if (string1.length()>1) {
            System.out.println("doStyle-010");
            SimpleAttributeSet keyWord1 = new SimpleAttributeSet();
            StyleConstants.setForeground(keyWord1, Color.YELLOW);
            StyleConstants.setBackground(keyWord1, Color.RED);
            int start_loc = 0;
            while (text.toLowerCase().indexOf(string1.toLowerCase(), start_loc) > -1) {
                int found_loc = text.toLowerCase().indexOf(string1.toLowerCase(), start_loc);
                doc.setCharacterAttributes(found_loc, string1.length(), keyWord1, true);
                start_loc = found_loc +1;
            }
        } else {
            System.out.println("doStyle-020");
            //doc.re
        }
        
        
        //Suche 1
        String string2 = _Suche2.getText();
        System.out.println("doStyle-003");
        if (string2.length()>1) {
            System.out.println("doStyle-010");
            SimpleAttributeSet keyWord2 = new SimpleAttributeSet();
            StyleConstants.setForeground(keyWord2, Color.GREEN);
            StyleConstants.setBackground(keyWord2, Color.GRAY);
            int start_loc = 0;
            while (text.toLowerCase().indexOf(string2.toLowerCase(), start_loc) > -1) {
                int found_loc = text.toLowerCase().indexOf(string2.toLowerCase(), start_loc);
                doc.setCharacterAttributes(found_loc, string2.length(), keyWord2, true);
                start_loc = found_loc +1;
            }
        } else {
            System.out.println("doStyle-020");
            //doc.re
        }

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SSHShell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SSHShell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SSHShell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SSHShell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SSHShell me = new SSHShell();
                me.setVisible(true);
                me.jDialog1.setBounds(200,200,200,200);
                me.jDialog1.show();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Submit;
    private javax.swing.JTextField _Kommando;
    private javax.swing.JTextPane _Output;
    private javax.swing.JPasswordField _Password;
    private javax.swing.JTextField _ServerName;
    private javax.swing.JTextField _Suche1;
    private javax.swing.JTextField _Suche2;
    private javax.swing.JTextField _UserName;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblKommando;
    private javax.swing.JLabel lblServer;
    private javax.swing.JLabel lblSuchen;
    private javax.swing.JLabel lblSuchen1;
    // End of variables declaration//GEN-END:variables
}
