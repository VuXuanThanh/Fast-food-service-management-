/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

/**
 *
 * @author Vu Xuan Thanh
 */
public class frmMain extends javax.swing.JFrame {

    /**
     * Creates new form frmMain
     */
    public frmMain() {
        initComponents();
        setExtendedState(this.MAXIMIZED_BOTH);
        mainMnu.add(Box.createHorizontalGlue());
        JMenu tenTaiKhoanMenu = new JMenu(frmDangNhap.tenTKDN);
        tenTaiKhoanMenu.setMnemonic(KeyEvent.VK_S);
//        Font font = new Font(name,style, size);
        Font font = new Font(mnuQuanLi.getFont().getName(),
                mnuQuanLi.getFont().getStyle(),25);
        
        tenTaiKhoanMenu.setFont(font);
        mainMnu.add(tenTaiKhoanMenu);
        itemDangXuat.setMnemonic(KeyEvent.VK_X);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mainMnu = new javax.swing.JMenuBar();
        mnuTrangChu = new javax.swing.JMenu();
        itemDangXuat = new javax.swing.JRadioButtonMenuItem();
        mnuQuanLi = new javax.swing.JMenu();
        itemQLNhaCungCap = new javax.swing.JRadioButtonMenuItem();
        itemQLNhanVien = new javax.swing.JRadioButtonMenuItem();
        itemQLNguyenLieu = new javax.swing.JRadioButtonMenuItem();
        itemQLDanhMuc = new javax.swing.JRadioButtonMenuItem();
        itemQLTaiKhoan = new javax.swing.JRadioButtonMenuItem();
        mnuNhapKhoNguyenLieu = new javax.swing.JMenu();
        mnuBaoCaoThongKe = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm quản lí đồ ăn nhanh");

        jPanel1.setForeground(new java.awt.Color(243, 215, 94));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 915, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        mainMnu.setBackground(new java.awt.Color(95, 240, 81));
        mainMnu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        mnuTrangChu.setBackground(new java.awt.Color(98, 240, 99));
        mnuTrangChu.setText("Trang chủ");
        mnuTrangChu.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        mnuTrangChu.setMargin(new java.awt.Insets(0, 10, 0, 10));

        itemDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        itemDangXuat.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        itemDangXuat.setMnemonic('C');
        itemDangXuat.setSelected(true);
        itemDangXuat.setText("Đăng xuất");
        itemDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDangXuatActionPerformed(evt);
            }
        });
        mnuTrangChu.add(itemDangXuat);

        mainMnu.add(mnuTrangChu);

        mnuQuanLi.setText("Quản lí");
        mnuQuanLi.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        mnuQuanLi.setMargin(new java.awt.Insets(0, 10, 0, 10));

        itemQLNhaCungCap.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        itemQLNhaCungCap.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        itemQLNhaCungCap.setSelected(true);
        itemQLNhaCungCap.setText("Quản lí nhà cung cấp");
        itemQLNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemQLNhaCungCapActionPerformed(evt);
            }
        });
        mnuQuanLi.add(itemQLNhaCungCap);

        itemQLNhanVien.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        itemQLNhanVien.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        itemQLNhanVien.setSelected(true);
        itemQLNhanVien.setText("Quản lí nhân viên");
        mnuQuanLi.add(itemQLNhanVien);

        itemQLNguyenLieu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        itemQLNguyenLieu.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        itemQLNguyenLieu.setSelected(true);
        itemQLNguyenLieu.setText("Quản lí nguyên liệu");
        itemQLNguyenLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemQLNguyenLieuActionPerformed(evt);
            }
        });
        mnuQuanLi.add(itemQLNguyenLieu);

        itemQLDanhMuc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        itemQLDanhMuc.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        itemQLDanhMuc.setSelected(true);
        itemQLDanhMuc.setText("Quản lí danh mục");
        mnuQuanLi.add(itemQLDanhMuc);

        itemQLTaiKhoan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        itemQLTaiKhoan.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        itemQLTaiKhoan.setSelected(true);
        itemQLTaiKhoan.setText("Quản lí tài khoản");
        mnuQuanLi.add(itemQLTaiKhoan);

        mainMnu.add(mnuQuanLi);

        mnuNhapKhoNguyenLieu.setText("Nhập kho nguyên liệu");
        mnuNhapKhoNguyenLieu.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        mnuNhapKhoNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuNhapKhoNguyenLieuMouseClicked(evt);
            }
        });
        mainMnu.add(mnuNhapKhoNguyenLieu);

        mnuBaoCaoThongKe.setText("Báo cáo thống kê");
        mnuBaoCaoThongKe.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        mnuBaoCaoThongKe.setMargin(new java.awt.Insets(0, 10, 0, 10));
        mnuBaoCaoThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuBaoCaoThongKeMouseClicked(evt);
            }
        });
        mainMnu.add(mnuBaoCaoThongKe);

        setJMenuBar(mainMnu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("Phần mềm quản lí đồ ăn nhanh-căng tin số 2_HaUI");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDangXuatActionPerformed
        // TODO add your handling code here:
           int n = JOptionPane.showConfirmDialog(null, "Xác nhận đăng xuất?","Are you sure?",JOptionPane.YES_NO_OPTION);
        if(n==0){
           this.setVisible(false);
           new frmDangNhap().setVisible(true);
        } 
    }//GEN-LAST:event_itemDangXuatActionPerformed

    private void mnuBaoCaoThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuBaoCaoThongKeMouseClicked
        // TODO add your handling code here:
      new dlg_BaoCaoThongKe(this,true).setVisible(true);
    }//GEN-LAST:event_mnuBaoCaoThongKeMouseClicked

    private void mnuNhapKhoNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuNhapKhoNguyenLieuMouseClicked
        // TODO add your handling code here:
         new DlgQLPhieuNhap(this, true).setVisible(true);
 //      new frmQLPhieuNhap().setVisible(true);
       
    }//GEN-LAST:event_mnuNhapKhoNguyenLieuMouseClicked

    private void itemQLNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemQLNhaCungCapActionPerformed
        // TODO add your handling code here:
        new DlgQLNCC(this, true).setVisible(true);
    }//GEN-LAST:event_itemQLNhaCungCapActionPerformed

    private void itemQLNguyenLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemQLNguyenLieuActionPerformed
        // TODO add your handling code here:
        new DlgQLNguyenLieu(this, true).setVisible(true);
    }//GEN-LAST:event_itemQLNguyenLieuActionPerformed

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
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButtonMenuItem itemDangXuat;
    private javax.swing.JRadioButtonMenuItem itemQLDanhMuc;
    private javax.swing.JRadioButtonMenuItem itemQLNguyenLieu;
    private javax.swing.JRadioButtonMenuItem itemQLNhaCungCap;
    private javax.swing.JRadioButtonMenuItem itemQLNhanVien;
    private javax.swing.JRadioButtonMenuItem itemQLTaiKhoan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar mainMnu;
    private javax.swing.JMenu mnuBaoCaoThongKe;
    private javax.swing.JMenu mnuNhapKhoNguyenLieu;
    private javax.swing.JMenu mnuQuanLi;
    private javax.swing.JMenu mnuTrangChu;
    // End of variables declaration//GEN-END:variables
}
