/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

/**
 *
 * @author Vu Xuan Thanh
 */
public class frmMainNhanVien extends javax.swing.JFrame {

    /**
     * Creates new form frmMainNhanVien
     */
    public frmMainNhanVien() {
        initComponents();
        setExtendedState(this.MAXIMIZED_BOTH);
         mainMnu.add(Box.createHorizontalGlue());
        JMenu tenTaiKhoanMenu = new JMenu(frmDangNhap.tenTKDN);
        tenTaiKhoanMenu.setMnemonic(KeyEvent.VK_S);
//        Font font = new Font(name,style, size);
        Font font = new Font(mnuDoiMatKhau.getFont().getName(),
                mnuDoiMatKhau.getFont().getStyle(),25);
        
        tenTaiKhoanMenu.setFont(font);
        mainMnu.add(tenTaiKhoanMenu);
       // itemDangXuat.setMnemonic(KeyEvent.VK_X);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMnu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuItemDangXuat = new javax.swing.JCheckBoxMenuItem();
        mnuQLThucDon = new javax.swing.JMenu();
        mnuOrderDoAn = new javax.swing.JMenu();
        mnuDoiMatKhau = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm quản lí đồ ăn nhanh");

        jMenu1.setText("Trang chủ");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenu1.setMargin(new java.awt.Insets(5, 5, 5, 10));

        mnuItemDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        mnuItemDangXuat.setSelected(true);
        mnuItemDangXuat.setText("Đăng xuất");
        mnuItemDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemDangXuatActionPerformed(evt);
            }
        });
        jMenu1.add(mnuItemDangXuat);

        mainMnu.add(jMenu1);

        mnuQLThucDon.setText("Quản lí thực đơn");
        mnuQLThucDon.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        mnuQLThucDon.setMargin(new java.awt.Insets(5, 5, 5, 10));
        mnuQLThucDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuQLThucDonMouseClicked(evt);
            }
        });
        mainMnu.add(mnuQLThucDon);

        mnuOrderDoAn.setText("Order đồ ăn");
        mnuOrderDoAn.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        mnuOrderDoAn.setMargin(new java.awt.Insets(5, 5, 5, 10));
        mnuOrderDoAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuOrderDoAnMouseClicked(evt);
            }
        });
        mainMnu.add(mnuOrderDoAn);

        mnuDoiMatKhau.setText("Đổi mật khẩu");
        mnuDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        mnuDoiMatKhau.setMargin(new java.awt.Insets(5, 5, 5, 10));
        mnuDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuDoiMatKhauMouseClicked(evt);
            }
        });
        mainMnu.add(mnuDoiMatKhau);

        setJMenuBar(mainMnu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuQLThucDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuQLThucDonMouseClicked
        // TODO add your handling code here:
     //   new dlgQLThucDon(this).setVisible(true);
     new dlgQLThucDon(this, true).setVisible(true);
       
    }//GEN-LAST:event_mnuQLThucDonMouseClicked

    private void mnuItemDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemDangXuatActionPerformed
        // TODO add your handling code here:
        int n = JOptionPane.showConfirmDialog(null, "Xác nhận đăng xuất?","Are you sure?",JOptionPane.YES_NO_OPTION);
        if(n==0){
           this.setVisible(false);
           new frmDangNhap().setVisible(true);
        } 
    }//GEN-LAST:event_mnuItemDangXuatActionPerformed

    private void mnuOrderDoAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuOrderDoAnMouseClicked
        // TODO add your handling code here:
        new DlgDatDoAn(this, true).setVisible(true);
//        new DlgQLPhieuNhap(this, true).setVisible(true);
    }//GEN-LAST:event_mnuOrderDoAnMouseClicked

    private void mnuDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuDoiMatKhauMouseClicked
        new frmDoiMatKhau().setVisible(true);
    }//GEN-LAST:event_mnuDoiMatKhauMouseClicked

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
            java.util.logging.Logger.getLogger(frmMainNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMainNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMainNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMainNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMainNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar mainMnu;
    private javax.swing.JMenu mnuDoiMatKhau;
    private javax.swing.JCheckBoxMenuItem mnuItemDangXuat;
    private javax.swing.JMenu mnuOrderDoAn;
    private javax.swing.JMenu mnuQLThucDon;
    // End of variables declaration//GEN-END:variables
}
