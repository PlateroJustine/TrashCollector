/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package trashcollector;

import java.sql.*;

public class LoginTrash extends javax.swing.JFrame {

    /**
     * Creates new form LoginTrash
     */
    public LoginTrash() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void validateLogin(String username, String password, String role) {
    String query = "SELECT * FROM users WHERE username = ? AND password = ? AND role = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(query)) {

        pst.setString(1, username);
        pst.setString(2, password);
        pst.setString(3, role);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            // Login successful, redirect based on the role
            if (role.equals("leader")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Login Successful as Leader!");
                // Open Leader Frame
                Leader_Frame leaderFrame = new Leader_Frame();
                leaderFrame.setVisible(true);
            } else if (role.equals("member")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Login Successful as Member!");
                // Open Member Frame
                MembersFrame memberFrame = new MembersFrame(); // Assuming you have a class MemberFrame
                memberFrame.setVisible(true);
            }
            this.setVisible(false); // Close the login screen
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid login credentials!");
        }

    } catch (SQLException ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error connecting to database: " + ex.getMessage());
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        LoginButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jTextField1 = new javax.swing.JTextField();
        LeaderBox = new javax.swing.JCheckBox();
        MemberBox = new javax.swing.JCheckBox();

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel2.setText("SHIBUYA TRASH COLLECTOR'S");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LoginButton.setFont(new java.awt.Font("Tw Cen MT", 0, 12)); // NOI18N
        LoginButton.setText("Login");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });
        jPanel1.add(LoginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        CancelButton.setFont(new java.awt.Font("Tw Cen MT", 0, 12)); // NOI18N
        CancelButton.setText("Cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });
        jPanel1.add(CancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, -1, -1));

        jLabel3.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel3.setText("SHIBUYA TRASH COLLECTOR'S");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel4.setText("Password:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel5.setText("Username:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jPasswordField1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 140, 30));

        jTextField1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 140, 30));

        LeaderBox.setText("Leader");
        LeaderBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaderBoxActionPerformed(evt);
            }
        });
        jPanel1.add(LeaderBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, -1));

        MemberBox.setText("Member");
        MemberBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MemberBoxActionPerformed(evt);
            }
        });
        jPanel1.add(MemberBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        String username = jTextField1.getText();
        String password = new String(jPasswordField1.getPassword());
        boolean isLeader = LeaderBox.isSelected();
        boolean isMember = MemberBox.isSelected();

        if (username.isEmpty() || password.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Username and password cannot be empty!");
            return;
        }

        // Check if a role is selected
        if (!isLeader && !isMember) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a role (Leader or Member).");
            return;
        }

        // Determine the role from the checkbox
        String role = isLeader ? "leader" : "member";
        validateLogin(username, password, role);
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void LeaderBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeaderBoxActionPerformed
        if (LeaderBox.isSelected()) {
        MemberBox.setSelected(false); // Deselect the Member checkbox
    }
    }//GEN-LAST:event_LeaderBoxActionPerformed

    private void MemberBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MemberBoxActionPerformed
        if (MemberBox.isSelected()) {
        LeaderBox.setSelected(false); // Deselect the Leader checkbox
    }
    }//GEN-LAST:event_MemberBoxActionPerformed

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
            java.util.logging.Logger.getLogger(LoginTrash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginTrash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginTrash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginTrash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginTrash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JCheckBox LeaderBox;
    private javax.swing.JButton LoginButton;
    private javax.swing.JCheckBox MemberBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
