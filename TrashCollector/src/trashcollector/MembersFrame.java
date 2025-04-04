/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package trashcollector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MembersFrame extends javax.swing.JFrame {

    public MembersFrame() {
        initComponents();
        loadMemberNames();
        loadTrashCollections();
        setLocationRelativeTo(null);
    }
    private void loadMemberNames() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT username FROM users WHERE role = 'member'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                NameComboBox.addItem(rs.getString("username"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading member names: " + e.getMessage());
        }
    }
    private void loadTrashCollections() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // Clear table before loading new data

    try (Connection conn = DBConnection.getConnection()) {
        String query = "SELECT u.username, td.trash_type, td.weight " +
                       "FROM trash_details td " +
                       "JOIN trash_collections tc ON td.collection_id = tc.collection_id " +
                       "JOIN users u ON tc.user_id = u.user_id " +
                       "ORDER BY tc.collection_date DESC";

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("username"),
                rs.getString("trash_type"),
                rs.getDouble("weight")
            });
        }

        calculateTotalWeight(); // Update total weight after loading data

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading trash collections: " + e.getMessage());
    }
}
    private void calculateTotalWeight() {
    double totalWeight = 0.0;
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    for (int i = 0; i < model.getRowCount(); i++) {
        totalWeight += (double) model.getValueAt(i, 2);
    }
    TotalWeightCollectedTextField.setText(String.format("%.2f", totalWeight));
}
    private int getUserIdByUsername(Connection conn, String username) throws SQLException {
        String query = "SELECT user_id FROM users WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("user_id"); // Return the user_id
        } else {
            throw new SQLException("User not found: " + username); // Handle case if user is not found
        }
    }
    private int getCollectionId(Connection conn) throws SQLException {
        String query = "SELECT MAX(collection_id) FROM trash_collections";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                // Return the next collection_id (current max + 1)
                return rs.getInt(1) + 1;
            } else {
                // If no records are found, start with collection_id = 1
                return 1;
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        NameComboBox = new javax.swing.JComboBox<>();
        EWasteTextField = new javax.swing.JTextField();
        PlasticTextField = new javax.swing.JTextField();
        PaperTextField = new javax.swing.JTextField();
        MetalTextField = new javax.swing.JTextField();
        OrganicTextField = new javax.swing.JTextField();
        GlassTextField = new javax.swing.JTextField();
        MetalBox = new javax.swing.JCheckBox();
        EwasteBox = new javax.swing.JCheckBox();
        PaperBox = new javax.swing.JCheckBox();
        PlasticBox = new javax.swing.JCheckBox();
        OrganicBox = new javax.swing.JCheckBox();
        GlassBox = new javax.swing.JCheckBox();
        TotalWeightCollected = new javax.swing.JLabel();
        TotalWeightCollectedTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        SubmitButton = new javax.swing.JButton();
        LogoutButton = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel2.setText("SHIBUYA TRASH COLLECTOR'S");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel3.setText("SHIBUYA TRASH COLLECTOR'S");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel1.setText("Member Name:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));
        jPanel2.add(NameComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 100, -1));

        EWasteTextField.setEditable(false);
        EWasteTextField.setFont(new java.awt.Font("Mongolian Baiti", 0, 12)); // NOI18N
        jPanel2.add(EWasteTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 100, 30));

        PlasticTextField.setEditable(false);
        PlasticTextField.setFont(new java.awt.Font("Mongolian Baiti", 0, 12)); // NOI18N
        jPanel2.add(PlasticTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 100, 30));

        PaperTextField.setEditable(false);
        PaperTextField.setFont(new java.awt.Font("Mongolian Baiti", 0, 12)); // NOI18N
        jPanel2.add(PaperTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 100, 30));

        MetalTextField.setEditable(false);
        MetalTextField.setFont(new java.awt.Font("Mongolian Baiti", 0, 12)); // NOI18N
        jPanel2.add(MetalTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 100, 30));

        OrganicTextField.setEditable(false);
        OrganicTextField.setFont(new java.awt.Font("Mongolian Baiti", 0, 12)); // NOI18N
        jPanel2.add(OrganicTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 100, 30));

        GlassTextField.setEditable(false);
        GlassTextField.setFont(new java.awt.Font("Mongolian Baiti", 0, 12)); // NOI18N
        jPanel2.add(GlassTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 100, 30));

        MetalBox.setText("Metal");
        MetalBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MetalBoxActionPerformed(evt);
            }
        });
        jPanel2.add(MetalBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 275, -1, 20));

        EwasteBox.setText("E-Waste");
        EwasteBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EwasteBoxActionPerformed(evt);
            }
        });
        jPanel2.add(EwasteBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 275, -1, -1));

        PaperBox.setText("Paper");
        PaperBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaperBoxActionPerformed(evt);
            }
        });
        jPanel2.add(PaperBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 215, -1, -1));

        PlasticBox.setText("Plastic");
        PlasticBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlasticBoxActionPerformed(evt);
            }
        });
        jPanel2.add(PlasticBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 155, -1, -1));

        OrganicBox.setText("Organic");
        OrganicBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrganicBoxActionPerformed(evt);
            }
        });
        jPanel2.add(OrganicBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 155, -1, -1));

        GlassBox.setText("Glass");
        GlassBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GlassBoxActionPerformed(evt);
            }
        });
        jPanel2.add(GlassBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 215, -1, -1));

        TotalWeightCollected.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        TotalWeightCollected.setText("Total Weight Collected:");
        jPanel2.add(TotalWeightCollected, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        TotalWeightCollectedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalWeightCollectedTextFieldActionPerformed(evt);
            }
        });
        jPanel2.add(TotalWeightCollectedTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 105, 120, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Trash Type", "Weight"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 380, 190));

        SubmitButton.setFont(new java.awt.Font("Tw Cen MT", 0, 12)); // NOI18N
        SubmitButton.setText("Submit");
        SubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitButtonActionPerformed(evt);
            }
        });
        jPanel2.add(SubmitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 561, -1, 20));

        LogoutButton.setFont(new java.awt.Font("Tw Cen MT", 0, 12)); // NOI18N
        LogoutButton.setText("Logout");
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutButtonActionPerformed(evt);
            }
        });
        jPanel2.add(LogoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PlasticBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlasticBoxActionPerformed
        PlasticTextField.setEditable(PlasticBox.isSelected());
        if (!PlasticBox.isSelected()) {
            PlasticTextField.setText(""); // Clear the text field when unchecked
        }
    }//GEN-LAST:event_PlasticBoxActionPerformed

    private void PaperBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaperBoxActionPerformed
        PaperTextField.setEditable(PaperBox.isSelected());
        if (!PaperBox.isSelected()) {
            PaperTextField.setText(""); 
    }
    }//GEN-LAST:event_PaperBoxActionPerformed

    private void MetalBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MetalBoxActionPerformed
        MetalTextField.setEditable(MetalBox.isSelected());
        if (!MetalBox.isSelected()) {
            MetalTextField.setText(""); 
        }
    }//GEN-LAST:event_MetalBoxActionPerformed

    private void GlassBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GlassBoxActionPerformed
        GlassTextField.setEditable(GlassBox.isSelected());
        if (!GlassBox.isSelected()) {
        GlassTextField.setText(""); 
        }
    }//GEN-LAST:event_GlassBoxActionPerformed

    private void OrganicBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrganicBoxActionPerformed
        OrganicTextField.setEditable(OrganicBox.isSelected());
        if (!OrganicBox.isSelected()) {
            OrganicTextField.setText(""); 
        }
    }//GEN-LAST:event_OrganicBoxActionPerformed

    private void EwasteBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EwasteBoxActionPerformed
        EWasteTextField.setEditable(EwasteBox.isSelected());
        if (!EwasteBox.isSelected()) {
            EWasteTextField.setText(""); 
        }
    }//GEN-LAST:event_EwasteBoxActionPerformed

    private void TotalWeightCollectedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalWeightCollectedTextFieldActionPerformed
        double totalWeight = DBConnection.getTotalCollectedWeight();
        TotalWeightCollectedTextField.setText(String.format("%.2f kg", totalWeight)); 
    }//GEN-LAST:event_TotalWeightCollectedTextFieldActionPerformed

    private void SubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitButtonActionPerformed
        try (Connection conn = DBConnection.getConnection()) {
        String memberName = (String) NameComboBox.getSelectedItem();

        // Retrieve weights from text fields, default to 0 if empty
        double plasticWeight = !PlasticTextField.getText().isEmpty() ? Double.parseDouble(PlasticTextField.getText()) : 0;
        double paperWeight = !PaperTextField.getText().isEmpty() ? Double.parseDouble(PaperTextField.getText()) : 0;
        double metalWeight = !MetalTextField.getText().isEmpty() ? Double.parseDouble(MetalTextField.getText()) : 0;
        double organicWeight = !OrganicTextField.getText().isEmpty() ? Double.parseDouble(OrganicTextField.getText()) : 0;
        double glassWeight = !GlassTextField.getText().isEmpty() ? Double.parseDouble(GlassTextField.getText()) : 0;
        double eWasteWeight = !EWasteTextField.getText().isEmpty() ? Double.parseDouble(EWasteTextField.getText()) : 0;

        // Check if all weights are 0, indicating that no input has been provided
        if (plasticWeight == 0 && paperWeight == 0 && metalWeight == 0 && organicWeight == 0 && glassWeight == 0 && eWasteWeight == 0) {
            JOptionPane.showMessageDialog(this, "Error: Please enter at least one weight value.");
            return; // Prevent the rest of the method from running
        }
        
        double totalWeight = plasticWeight + paperWeight + metalWeight + organicWeight + glassWeight + eWasteWeight;

        // Insert the trash collection data into the database
        int userId = getUserIdByUsername(conn, memberName);
        int collectionId = getCollectionId(conn);

        String insertQuery = "INSERT INTO trash_collections (collection_id, user_id, collection_date, total_weight) VALUES (?, ?, NOW(), ?)";
        PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
        insertStmt.setInt(1, collectionId);
        insertStmt.setInt(2, userId);
        insertStmt.setDouble(3, totalWeight);  // Insert total weight here
        insertStmt.executeUpdate();


        String insertDetailsQuery = "INSERT INTO trash_details (collection_id, trash_type, weight) VALUES (?, ?, ?)";
        PreparedStatement insertDetailsStmt = conn.prepareStatement(insertDetailsQuery);
        
        // Insert details for each trash type only if the weight is greater than 0
        if (plasticWeight > 0) {
            insertDetailsStmt.setInt(1, collectionId);
            insertDetailsStmt.setString(2, "Plastic");
            insertDetailsStmt.setDouble(3, plasticWeight);
            insertDetailsStmt.executeUpdate();
        }

        if (paperWeight > 0) {
            insertDetailsStmt.setInt(1, collectionId);
            insertDetailsStmt.setString(2, "Paper");
            insertDetailsStmt.setDouble(3, paperWeight);
            insertDetailsStmt.executeUpdate();
        }

        if (metalWeight > 0) {
            insertDetailsStmt.setInt(1, collectionId);
            insertDetailsStmt.setString(2, "Metal");
            insertDetailsStmt.setDouble(3, metalWeight);
            insertDetailsStmt.executeUpdate();
        }

        if (organicWeight > 0) {
            insertDetailsStmt.setInt(1, collectionId);
            insertDetailsStmt.setString(2, "Organic");
            insertDetailsStmt.setDouble(3, organicWeight);
            insertDetailsStmt.executeUpdate();
        }

        if (glassWeight > 0) {
            insertDetailsStmt.setInt(1, collectionId);
            insertDetailsStmt.setString(2, "Glass");
            insertDetailsStmt.setDouble(3, glassWeight);
            insertDetailsStmt.executeUpdate();
        }

        if (eWasteWeight > 0) {
            insertDetailsStmt.setInt(1, collectionId);
            insertDetailsStmt.setString(2, "E-Waste");
            insertDetailsStmt.setDouble(3, eWasteWeight);
            insertDetailsStmt.executeUpdate();
        }

        // Update total weight field
        TotalWeightCollectedTextField.setText(String.format("%.2f kg", totalWeight));

        // Reload the trash collections in the table
        loadTrashCollections();
        
        JOptionPane.showMessageDialog(this, "Trash collection submitted successfully!");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error submitting trash collection: " + e.getMessage());
    }
}

// Helper method to insert trash details into the database
private void insertTrashDetails(Connection conn, int collectionId, int userId, String trashType, double weight) throws SQLException {
    if (weight > 0) { // Only insert if weight is greater than 0
        String query = "INSERT INTO trash_details (collection_id, user_id, trash_type, weight) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, collectionId);
            stmt.setInt(2, userId);
            stmt.setString(3, trashType);
            stmt.setDouble(4, weight);
            stmt.executeUpdate();
        }
    }
    }//GEN-LAST:event_SubmitButtonActionPerformed

    private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
        this.dispose();  // Close the current window/frame

        LoginTrash loginWindow = new LoginTrash();
        loginWindow.setVisible(true);
    }//GEN-LAST:event_LogoutButtonActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(MembersFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MembersFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MembersFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MembersFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MembersFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EWasteTextField;
    private javax.swing.JCheckBox EwasteBox;
    private javax.swing.JCheckBox GlassBox;
    private javax.swing.JTextField GlassTextField;
    private javax.swing.JButton LogoutButton;
    private javax.swing.JCheckBox MetalBox;
    private javax.swing.JTextField MetalTextField;
    private javax.swing.JComboBox<String> NameComboBox;
    private javax.swing.JCheckBox OrganicBox;
    private javax.swing.JTextField OrganicTextField;
    private javax.swing.JCheckBox PaperBox;
    private javax.swing.JTextField PaperTextField;
    private javax.swing.JCheckBox PlasticBox;
    private javax.swing.JTextField PlasticTextField;
    private javax.swing.JButton SubmitButton;
    private javax.swing.JLabel TotalWeightCollected;
    private javax.swing.JTextField TotalWeightCollectedTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
