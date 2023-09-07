/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package formdata;

import helper.database;
import helper.reset;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.*;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author afgha
 */
public class Member extends javax.swing.JFrame {
 private Connection conn = new helper.database().connect();
 database db = new database();
 reset reset = new reset();
 private DefaultTableModel tabmode;
 
 
 protected void datatable() {
    Object[] Baris = {"ID ", "Nama ", "Alamat", "No. HP"};
    tabmode = new DefaultTableModel(null, Baris);
    tCustomer.setModel(tabmode);
    String sql = "SELECT * FROM members";
    try {
        Statement stat = conn.createStatement();
        ResultSet res = stat.executeQuery(sql);
        while(res.next()) {

        String id = res.getString("id_member");
        String nama = res.getString("nama_nember"); 
        String alamat = res.getString("alamat");
        String noHp = res.getString("no_telpon");

        Object[] data = {id, nama, alamat, noHp};

        tabmode.addRow(data);
      }
    } catch (SQLException ex) {
        Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    /**
     * Creates new form Customer
     */
    public Member() {
        initComponents();
        datatable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSimpan = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        txtCustomerName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCustomerID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tCustomer = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        txtPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneNumberActionPerformed(evt);
            }
        });

        jLabel1.setText("Nama Pelanggan");

        jLabel2.setText("Alamat");

        jLabel3.setText("No. Telp");

        tCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tCustomerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tCustomer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCustomerID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnReset))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // Ambil nilai input dari form
        String customerName = txtCustomerName.getText();
        String address = txtAddress.getText();
        String phoneNumber = txtPhoneNumber.getText();
//        String username = txtUsername.getText();
//        String password = new String(txtPassword.getPassword());
        // Buat array kolom dan array nilai untuk insert data
        String[] columns = {"id_member","nama_member", "alamat", "no_telpon"};
        Object[] values = {"0",customerName, address, phoneNumber};
        
        try {
            // Panggil fungsi insertData dengan menggunakan koneksi database
            db.insertData(conn, "members", columns, values);
            reset.resetTextFields(rootPane);
            datatable();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menambahkan data");
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneNumberActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // Ambil nilai input dari form
    int customerId = Integer.parseInt(txtCustomerID.getText());
    String customerName = txtCustomerName.getText();
    String address = txtAddress.getText();
    String phoneNumber = txtPhoneNumber.getText();
//    String username = txtUsername.getText();
//    String password = new String(txtPassword.getPassword());
    String[] columns;
    Object[] values; 
    // Buat array kolom dan nilai
    

   
      columns = new String[] {"nama_member", "alamat", "no_telpon"};

      values = new Object[] {customerName, address, phoneNumber};

    
    // Buat kondisi berdasarkan customer_id
    String condition = "id_member = " + customerId;

    try {
        // Panggil fungsi updateData dengan menggunakan koneksi database
        db.updateData(conn, "members", columns, values, condition);
        datatable();
        // Reset form setelah update berhasil
        reset.resetTextFields(rootPane);
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal mengupdate data");
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       // Ambil nilai input dari form
        int customerId = Integer.parseInt(txtCustomerID.getText());

        // Buat kondisi berdasarkan customer_id
        String condition = "id_member = " + customerId;

        try {
            // Panggil fungsi deleteData dengan menggunakan koneksi database
            db.deleteData(conn, "members", condition);
            datatable();
            // Reset form setelah delete berhasil
            reset.resetTextFields(rootPane);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menghapus data");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        reset.resetTextFields(rootPane);
    }//GEN-LAST:event_btnResetActionPerformed

    private void tCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tCustomerMouseClicked
        int selectedRowIndex = tCustomer.getSelectedRow();
       String customerId = tabmode.getValueAt(selectedRowIndex, 0).toString();
       String customerName = tabmode.getValueAt(selectedRowIndex, 1).toString();
       String address = tabmode.getValueAt(selectedRowIndex, 2).toString();
       String phoneNumber = tabmode.getValueAt(selectedRowIndex, 3).toString();

       txtCustomerID.setText(customerId);
       txtCustomerName.setText(customerName);
       txtAddress.setText(address);
       txtPhoneNumber.setText(phoneNumber);
    }//GEN-LAST:event_tCustomerMouseClicked

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
            java.util.logging.Logger.getLogger(Member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Member.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Member().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tCustomer;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCustomerID;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtPhoneNumber;
    // End of variables declaration//GEN-END:variables
}
