/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package formdata;

import helper.database;
import helper.reset;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.*;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author afgha
 */
public class TransaksiPenjualan extends javax.swing.JFrame {
 private Connection conn = new helper.database().connect();
 database db = new database();
 reset reset = new reset();
// private DefaultTableModel tabmode;
 private int kategoriId;
 
 
    protected void datatable() {
       // Buat model dan view table
       Object[] kolom = {"ID Transaksi", "ID Member", "ID Produk",
               "Member", "Produk", "Harga", "Jumlah", "Total Harga", "Tanggal Penjualan"};

       Object[] kolom2 = {"No Baris", "ID Member", "ID Produk", "Member", "Produk", "Harga", "Stok", "Jumlah", "Total Harga", "Tanggal Penjualan"};

       DefaultTableModel model = new DefaultTableModel(null, kolom);
       DefaultTableModel model2 = new DefaultTableModel(null, kolom2) {
           @Override
           public boolean isCellEditable(int row, int column2) {
               return false; // Membuat sel tidak dapat diedit
           }
       };
       tTransaksi.setModel(model);
       tKeranjang.setModel(model2);

       // Query select join
       String query = "SELECT t.id_transaksi, t.id_member, t.id_produk, ";
       query += "c.nama_member, p.nama_produk, p.harga, p.stok, t.jumlah, t.total_harga,t.tanggal_penjualan ";
       query += "FROM transaksi_penjualan t ";
       query += "JOIN members c ON t.id_member = c.id_member ";
       query += "JOIN produk p ON t.id_produk = p.id_produk";
       try {

           Statement stmt = conn.createStatement();

           // Eksekusi query
           ResultSet rs = stmt.executeQuery(query);

           while (rs.next()) {

               Object[] row = new Object[kolom.length];

               row[0] = rs.getString("id_transaksi");
               row[1] = rs.getString("id_member");
               row[2] = rs.getString("id_produk");
               row[3] = rs.getString("nama_member");
               row[4] = rs.getString("nama_produk");
               row[5] = rs.getString("harga");
               row[6] = rs.getString("jumlah");
               row[7] = rs.getString("total_harga");
               row[8] = rs.getString("tanggal_penjualan");

               // Tambahkan baris baru
               model.addRow(row);

           }

       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
 

    public TransaksiPenjualan() {
        initComponents();
        datatable();
        txtTanggalPenjualan.setDate(new java.util.Date());
        tTransaksi.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            int row = tTransaksi.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tTransaksi.getModel();

            String idTransaksi = model.getValueAt(row, 0).toString();
            String idMember = model.getValueAt(row, 1).toString();
            String idProduk = model.getValueAt(row, 2).toString();
            String namaMember = model.getValueAt(row, 3).toString();
            String namaProduk = model.getValueAt(row, 4).toString();
            String hargaProduk = model.getValueAt(row, 5).toString();
            String jumlah = model.getValueAt(row, 6).toString();
            String tgl = model.getValueAt(row, 8).toString();

            txtIdTransaksi.setText(idTransaksi);
            txtIdMember.setText(idMember);
            txtNamaMember.setText(namaMember);
            txtIdProduk.setText(idProduk);
            txtNamaProduk.setText(namaProduk);
            txtHargaProduk.setText(hargaProduk);
            txtJumlah.setText(jumlah);
            txtTanggalPenjualan.setDate(java.sql.Date.valueOf(tgl));
        }
    }
});
        txtJumlah.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        calculateTotalHarga();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        calculateTotalHarga();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        calculateTotalHarga();
    }

    private void calculateTotalHarga() {
        try {
            double hargaProduk = Double.parseDouble(txtHargaProduk.getText());
            int jumlah = Integer.parseInt(txtJumlah.getText());
            double totalHarga = hargaProduk * jumlah;
            txtTotalHarga.setText(String.valueOf(totalHarga));
        } catch (NumberFormatException ex) {
            // Penanganan jika input tidak valid
            txtTotalHarga.setText("");
        }
    }
});
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
        txtIdTransaksi = new javax.swing.JTextField();
        txtIdMember = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tTransaksi = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtNamaMember = new javax.swing.JTextField();
        btnCariCustomer = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtNamaProduk = new javax.swing.JTextField();
        txtIdProduk = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCariProduk = new javax.swing.JButton();
        txtJumlah = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTanggalPenjualan = new com.toedter.calendar.JDateChooser();
        txtTotalHarga = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtHargaProduk = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tKeranjang = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtStokProduk = new javax.swing.JTextField();
        btnTambahKeranjang = new javax.swing.JButton();
        btnHapusCart = new javax.swing.JButton();
        btnHapusSemuaCart = new javax.swing.JButton();
        btnUpdateItemKeranjang = new javax.swing.JButton();

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

        txtIdTransaksi.setEnabled(false);

        jLabel1.setText("ID Transaksi");

        jLabel2.setText("ID Member");

        tTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tTransaksi);

        jLabel4.setText("Nama Member");

        txtNamaMember.setEnabled(false);

        btnCariCustomer.setText("Cari Member");
        btnCariCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariCustomerActionPerformed(evt);
            }
        });

        jLabel5.setText("Nama Produk");

        jLabel3.setText("ID Produk");

        txtCariProduk.setText("Cari Produk");
        txtCariProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariProdukActionPerformed(evt);
            }
        });

        jLabel6.setText("Jumlah");

        jLabel8.setText("Tanggal Penjualan");

        txtTanggalPenjualan.setEnabled(false);

        jLabel9.setText("Total Harga");

        jLabel10.setText("Harga Produk");

        txtHargaProduk.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Table Penjualan");

        tKeranjang.setModel(new javax.swing.table.DefaultTableModel(
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
        tKeranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tKeranjangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tKeranjang);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Keranjang");

        jLabel13.setText("Stok");

        txtStokProduk.setEnabled(false);

        btnTambahKeranjang.setText("Tambah Keranjang");
        btnTambahKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahKeranjangActionPerformed(evt);
            }
        });

        btnHapusCart.setText("Hapus Item Keranjang");
        btnHapusCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusCartActionPerformed(evt);
            }
        });

        btnHapusSemuaCart.setText("Hapus Semua Item Keranjang");
        btnHapusSemuaCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusSemuaCartActionPerformed(evt);
            }
        });

        btnUpdateItemKeranjang.setText("Update Item Keranjang");
        btnUpdateItemKeranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateItemKeranjangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTanggalPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdMember, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaMember, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCariCustomer)
                            .addComponent(txtCariProduk))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHargaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStokProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTambahKeranjang)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdateItemKeranjang)
                                .addGap(23, 23, 23)
                                .addComponent(btnHapusCart)
                                .addGap(18, 18, 18)
                                .addComponent(btnHapusSemuaCart)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(47, 47, 47)
                                .addComponent(txtIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(118, 118, 118)
                                .addComponent(jLabel11)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel8)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(txtTanggalPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtIdMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNamaMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnCariCustomer)
                        .addGap(58, 58, 58)
                        .addComponent(txtCariProduk))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel10)
                                .addGap(19, 19, 19)
                                .addComponent(jLabel13)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(txtHargaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(txtStokProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahKeranjang)
                    .addComponent(btnHapusCart)
                    .addComponent(btnHapusSemuaCart)
                    .addComponent(btnUpdateItemKeranjang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        try {
        int rowCount = tKeranjang.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String idMember = tKeranjang.getValueAt(i, 1).toString();
            String idProduk = tKeranjang.getValueAt(i, 2).toString();
            String jumlah = tKeranjang.getValueAt(i, 7).toString();
            String totalHarga = tKeranjang.getValueAt(i, 8).toString();
            String tanggalPenjualanStr = tKeranjang.getValueAt(i, 9).toString();

            String[] columns = {"id_member", "id_produk", "jumlah", "total_harga", "tanggal_penjualan"};
            Object[] values = {idMember, idProduk, jumlah, totalHarga, null};

            // Convert tanggalPenjualanStr to java.sql.Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate;
            try {
                parsedDate = dateFormat.parse(tanggalPenjualanStr);
                java.sql.Date tanggalPenjualan = new java.sql.Date(parsedDate.getTime());
                values[4] = tanggalPenjualan;
            } catch (ParseException ex) {
                ex.printStackTrace();
                continue; // Skip this iteration if date parsing fails
            }

            db.insertData(conn, "transaksi_penjualan", columns, values);
        }

        // Reset form
        reset();

        // Refresh table
        datatable();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal melakukan insert data");
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // Ambil nilai input
        int transaksiId = Integer.parseInt(txtIdTransaksi.getText());
        String idMember = txtIdMember.getText();
        String idProduk = txtIdProduk.getText();
        String jumlah = txtJumlah.getText();
        java.sql.Date tanggal = new java.sql.Date(txtTanggalPenjualan.getDate().getTime());

        // Mendapatkan indeks baris yang dipilih pada tabel
        int selectedRow = tTransaksi.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih baris pada tabel transaksi.");
            return;
        }

        try {
            // Panggil updateData untuk tabel transaksi_penjualan
            String[] transaksiColumns = {"id_member", "id_produk", "jumlah", "tanggal_penjualan"};
            Object[] transaksiValues = {idMember, idProduk, jumlah, tanggal};
            String transaksiCondition = "id_transaksi = " + transaksiId;
            db.updateData(conn, "transaksi_penjualan", transaksiColumns, transaksiValues, transaksiCondition);

            // Refresh tabel
            datatable();

            // Reset form
            reset();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       // Ambil nilai input dari form
        int idProduk = Integer.parseInt(txtIdTransaksi.getText());

        // Buat kondisi berdasarkan customer_id
        String condition = "id_transaksi = " + idProduk;

        try {
            // Panggil fungsi deleteData dengan menggunakan koneksi database
            db.deleteData(conn, "transaksi_penjualan", condition);
            datatable();
            // Reset form setelah delete berhasil
            reset();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menghapus data");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        reset.resetTextFields(rootPane);
        txtTanggalPenjualan.setDate(new java.util.Date());
    }//GEN-LAST:event_btnResetActionPerformed
    
    
    
    private void tTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tTransaksiMouseClicked
        
    }//GEN-LAST:event_tTransaksiMouseClicked

    private void btnCariCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariCustomerActionPerformed
        // Ambil keyword pencarian
  String keyword = txtIdMember.getText();

  try {

    String sql = "SELECT * FROM members WHERE id_member LIKE ?";
    
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setString(1, "%" + keyword + "%");

    ResultSet rs = stmt.executeQuery();
    
    if(rs.next()) {
      // Ambil nama customer
      String nama = rs.getString("nama_member");
      
      // Isi ke textfield
      txtNamaMember.setText(nama);
    } else {
      JOptionPane.showMessageDialog(null, "Member tidak ditemukan");
      txtNamaMember.setText("");
    }

  } catch (SQLException ex) {
    ex.printStackTrace(); 
  }
    }//GEN-LAST:event_btnCariCustomerActionPerformed

    private void txtCariProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariProdukActionPerformed
       String id = txtIdProduk.getText();

        try {
            // Buat query untuk mencari produk berdasarkan kriteria
            String sql = "SELECT nama_produk, harga,stok FROM produk WHERE id_produk = ?";
            
            // Persiapkan statement yang akan dijalankan
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            // Set nilai parameter pada statement
            stmt.setString(1, id);
            
            // Jalankan query dan dapatkan hasilnya
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Jika ada hasil, ambil nilai nama_produk dan harga_produk
                String namaProduk = rs.getString("nama_produk");
                double hargaProduk = rs.getDouble("harga");
                String stok = rs.getString("stok");
                
                // Set nilai nama_produk dan harga_produk ke dalam txtNamaProduk dan txtHargaProduk
                txtNamaProduk.setText(namaProduk);
                txtHargaProduk.setText(String.valueOf(hargaProduk));
                txtStokProduk.setText(stok);
            } else {
                // Jika tidak ada hasil, kosongkan txtNamaProduk dan txtHargaProduk
                txtNamaProduk.setText("");
                txtHargaProduk.setText("");
                JOptionPane.showMessageDialog(null, "Produk tidak ditemukan");
            }
            
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam mencari produk");
        }
    }//GEN-LAST:event_txtCariProdukActionPerformed

    private void tKeranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tKeranjangMouseClicked
        if (evt.getClickCount() == 1) {
            int row = tKeranjang.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tKeranjang.getModel();

            String idMember = model.getValueAt(row, 1).toString();
            String idProduk = model.getValueAt(row, 2).toString();
            String namaMember = model.getValueAt(row, 3).toString();
            String namaProduk = model.getValueAt(row, 4).toString();
            String hargaProduk = model.getValueAt(row, 5).toString();
            String stokProduk = model.getValueAt(row, 6).toString(); // Get the stock value
            String jumlah = model.getValueAt(row, 7).toString();
            String totalHarga = model.getValueAt(row, 8).toString();
            String tgl = model.getValueAt(row, 9).toString();

            txtIdMember.setText(idMember);
            txtNamaMember.setText(namaMember);
            txtIdProduk.setText(idProduk);
            txtNamaProduk.setText(namaProduk);
            txtHargaProduk.setText(hargaProduk);
            txtStokProduk.setText(stokProduk); // Set the stock value
            txtJumlah.setText(jumlah);
            txtTotalHarga.setText(totalHarga);
            txtTanggalPenjualan.setDate(java.sql.Date.valueOf(tgl));
        }
    }//GEN-LAST:event_tKeranjangMouseClicked
    
    
    void reset(){
        reset.resetTextFields(rootPane);
        txtTanggalPenjualan.setDate(new java.util.Date());
    }
    
    
    
    private void btnTambahKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahKeranjangActionPerformed
       DefaultTableModel model = (DefaultTableModel) tKeranjang.getModel();

    // Validasi input
    if (txtIdMember.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "ID Member harus diisi.");
    } else if (txtIdProduk.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "ID Produk harus diisi.");
    } else if (txtNamaMember.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Nama Member harus diisi.");
    } else if (txtNamaProduk.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Nama Produk harus diisi.");
    } else if (txtHargaProduk.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Harga Produk harus diisi.");
    } else if (txtJumlah.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Jumlah harus diisi.");
    } else if (txtTotalHarga.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Total Harga harus diisi.");
    } else if (txtTanggalPenjualan.getDate() == null) {
        JOptionPane.showMessageDialog(null, "Tanggal Penjualan harus diisi.");
    } else if (txtStokProduk.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Stok Produk harus diisi.");
    } else {
    // Ambil nilai stok dari komponen stok (gantilah dengan nama yang sesuai)
    int stok = Integer.parseInt(txtStokProduk.getText()); // Misalnya, txtStok adalah komponen yang menampilkan stok produk

    // Ambil nilai jumlah dari komponen input jumlah
    int jumlahBarang = Integer.parseInt(txtJumlah.getText());

    if (stok == 0) {
        JOptionPane.showMessageDialog(null, "Stok produk sudah habis. Tidak dapat menambahkan ke keranjang.");
    } else if (jumlahBarang <= 0) {
        JOptionPane.showMessageDialog(null, "Jumlah barang harus lebih besar dari 0.");
    } else if (jumlahBarang > stok) {
        JOptionPane.showMessageDialog(null, "Jumlah barang melebihi stok yang tersedia.");
    } else {
        // Check if idMember and idProduk already exist in the table model
        boolean isDuplicate = false;
        for (int i = 0; i < model.getRowCount(); i++) {
            String existingIdMember = model.getValueAt(i, 1).toString();
            String existingIdProduk = model.getValueAt(i, 2).toString();
            if (existingIdMember.equals(txtIdMember.getText()) && existingIdProduk.equals(txtIdProduk.getText())) {
                isDuplicate = true;
                break;
            }
        }

        if (isDuplicate) {
            JOptionPane.showMessageDialog(null, "ID Member dan ID Produk sudah ada dalam keranjang.");
        } else {
            // Ambil jumlah baris saat ini dalam tabel
            int rowCount = model.getRowCount();

            // Nomor baris akan dimulai dari 1
            int nomorBaris = rowCount + 1;
            String idMember = txtIdMember.getText();
            String idProduk = txtIdProduk.getText();
            String namaMember = txtNamaMember.getText();
            String namaProduk = txtNamaProduk.getText();
            String harga = txtHargaProduk.getText();
            String jumlah = txtJumlah.getText();
            String totalHarga = txtTotalHarga.getText();
            java.sql.Date tanggalPenjualan = new java.sql.Date(txtTanggalPenjualan.getDate().getTime());

            Object[] row = {nomorBaris, idMember, idProduk, namaMember, namaProduk, harga, stok, jumlah, totalHarga, tanggalPenjualan};
            model.addRow(row);
            reset();
            String tempIdMember = idMember;
            String tempnamaMember = namaMember;
            txtIdMember.setText(tempIdMember);
            txtNamaMember.setText(tempnamaMember);
        }
    }
}
    }//GEN-LAST:event_btnTambahKeranjangActionPerformed

    private void btnHapusCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusCartActionPerformed
        int selectedRow = tKeranjang.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tKeranjang.getModel();
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Pilih item yang akan dihapus.");
        }
    }//GEN-LAST:event_btnHapusCartActionPerformed

    private void btnHapusSemuaCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusSemuaCartActionPerformed
        DefaultTableModel model = (DefaultTableModel) tKeranjang.getModel();
        model.setRowCount(0); // Menghapus semua baris dari tabel
    }//GEN-LAST:event_btnHapusSemuaCartActionPerformed

    private void btnUpdateItemKeranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateItemKeranjangActionPerformed
        int selectedRow = tKeranjang.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tKeranjang.getModel();
        if (selectedRow != -1) {
            // Ambil nilai dari komponen input teks update yang sesuai
             // Ambil jumlah baris saat ini dalam tabel
            int rowCount = model.getRowCount();

                // Nomor baris akan dimulai dari 1
            int nomorBaris = rowCount + 1;
            String updatedIdMember = txtIdMember.getText();
            String updatedIdProduk = txtIdProduk.getText();
            String updatedNamaMember = txtNamaMember.getText();
            String updatedNamaProduk = txtNamaProduk.getText();
            String updatedHarga = txtHargaProduk.getText();
            String updatedJumlah = txtJumlah.getText();
            String updatedTotalHarga = txtTotalHarga.getText();
            
            // Validasi input
            if (updatedIdMember.isEmpty() || updatedIdProduk.isEmpty() || updatedNamaMember.isEmpty() ||
                updatedNamaProduk.isEmpty() || updatedHarga.isEmpty() || updatedJumlah.isEmpty() ||
                updatedTotalHarga.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Isi semua kolom update dengan nilai yang valid.");
            } else {
                // Validasi jumlah
                try {
                    int jumlahBarang = Integer.parseInt(updatedJumlah);
                    int stok = Integer.parseInt(txtStokProduk.getText()); // Ganti dengan komponen stok yang sesuai
                    if (jumlahBarang <= 0 || jumlahBarang > stok) {
                        JOptionPane.showMessageDialog(null, "Jumlah barang tidak valid.");
                        return; // Jangan lanjutkan update jika validasi gagal
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Jumlah barang harus berupa angka.");
                    return; // Jangan lanjutkan update jika validasi gagal
                }

                // Update data di dalam tabel
                tKeranjang.setValueAt(nomorBaris, selectedRow, 0); 
                tKeranjang.setValueAt(updatedIdMember, selectedRow, 1); 
                tKeranjang.setValueAt(updatedIdProduk, selectedRow, 2); 
                tKeranjang.setValueAt(updatedNamaMember, selectedRow, 3);
                tKeranjang.setValueAt(updatedNamaProduk, selectedRow, 4); 
                tKeranjang.setValueAt(updatedHarga, selectedRow, 5); 
                tKeranjang.setValueAt(updatedJumlah, selectedRow, 6);
                tKeranjang.setValueAt(updatedTotalHarga, selectedRow, 7); 
                
                // Clear komponen input teks update jika diperlukan
                String tempIdMember = updatedIdMember;
                String tempNamaMember = updatedNamaMember;
                reset();
                txtIdMember.setText(tempIdMember);
                txtNamaMember.setText(tempNamaMember);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pilih item yang akan diupdate.");
        }
    }//GEN-LAST:event_btnUpdateItemKeranjangActionPerformed

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
            java.util.logging.Logger.getLogger(TransaksiPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiPenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariCustomer;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnHapusCart;
    private javax.swing.JButton btnHapusSemuaCart;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahKeranjang;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateItemKeranjang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tKeranjang;
    private javax.swing.JTable tTransaksi;
    private javax.swing.JButton txtCariProduk;
    private javax.swing.JTextField txtHargaProduk;
    private javax.swing.JTextField txtIdMember;
    private javax.swing.JTextField txtIdProduk;
    private javax.swing.JTextField txtIdTransaksi;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtNamaMember;
    private javax.swing.JTextField txtNamaProduk;
    private javax.swing.JTextField txtStokProduk;
    private com.toedter.calendar.JDateChooser txtTanggalPenjualan;
    private javax.swing.JTextField txtTotalHarga;
    // End of variables declaration//GEN-END:variables
}
