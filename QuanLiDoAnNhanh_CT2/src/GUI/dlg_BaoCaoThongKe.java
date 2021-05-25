/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL;
import DTO.CustomTable_HoaDonBaoCaoTK;
import DTO.CustomTable_PhieuNhapBaoCaoTK;
import DTO.HoaDon;
import DTO.PhieuNhap;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Frame;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Vu Xuan Thanh
 */
public class dlg_BaoCaoThongKe extends javax.swing.JDialog {

    BLL bll = new BLL();
    ArrayList<HoaDon> list = new ArrayList<>();
    ArrayList<PhieuNhap> listPN = new ArrayList<>();
    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int month = localDate.getMonthValue();
    int year = localDate.getYear();

    public dlg_BaoCaoThongKe(Frame owner) {
        super(owner);
    }

    public dlg_BaoCaoThongKe(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cbxLoaiThoiGian.addItem("Thống kê theo ngày");
        cbxLoaiThoiGian.addItem("Thống kê theo tháng");
        cbxLoaiThoiGian.addItem("Thống kê theo năm");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        txtNgayBatDau.setText(dtf.format(now));
        txtNgayKetThuc.setText(dtf.format(now));
        thongKeHoaDon(dtf.format(now), dtf.format(now));
        thongKePhieuNhap(dtf.format(now), dtf.format(now));
    }

    public double thongKeHoaDon(String ngayBatDau, String ngayKetThuc) {
        try {
            list = bll.showDSCTHoaDon(ngayBatDau, ngayKetThuc);
            tblDSHoaDon.setModel(new CustomTable_HoaDonBaoCaoTK(list));
            double sumHD = 0;
            for (HoaDon hoaDon : list) {
                sumHD += hoaDon.getTongTien();
            }
            return sumHD;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn CSDL" + ex.getMessage());
            return 0;
        }
    }

    public double thongKePhieuNhap(String ngayBatDau, String ngayKetThuc) {
        try {
            double sumPN = 0;
            listPN = bll.showDSCTPhieuNhap(ngayBatDau, ngayKetThuc);
            tblChiPhiMuaNguyenLieu.setModel(new CustomTable_PhieuNhapBaoCaoTK(listPN));
            for (PhieuNhap phieuNhap : listPN) {
                sumPN += phieuNhap.getTongTien();
            }
            return sumPN;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn CSDL" + ex.getMessage());
            return 0;
        }
    }

    public double thongKeHoaDon(int thangHT) {
        try {
            list = bll.showDSCTHoaDon(thangHT);
            tblDSHoaDon.setModel(new CustomTable_HoaDonBaoCaoTK(list));
            double sumHD = 0;
            for (HoaDon hoaDon : list) {
                sumHD += hoaDon.getTongTien();
            }
            return sumHD;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn CSDL" + ex.getMessage());
            return 0;
        }
    }

    public double thongKePhieuNhap(int thangHT) {
        try {
            double sumPN = 0;
            listPN = bll.showDSCTPhieuNhap(thangHT);
            tblChiPhiMuaNguyenLieu.setModel(new CustomTable_PhieuNhapBaoCaoTK(listPN));
            for (PhieuNhap phieuNhap : listPN) {
                sumPN += phieuNhap.getTongTien();
            }
            return sumPN;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn CSDL" + ex.getMessage());
            return 0;
        }
    }

    public double thongKeHoaDon(int namHT, boolean flag) {
        try {
            list = bll.showDSCTHoaDon(namHT, flag);
            tblDSHoaDon.setModel(new CustomTable_HoaDonBaoCaoTK(list));
            double sumHD = 0;
            for (HoaDon hoaDon : list) {
                sumHD += hoaDon.getTongTien();
            }
            return sumHD;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn CSDL" + ex.getMessage());
            return 0;
        }
    }

    public double thongKePhieuNhap(int namHT, boolean flag) {
        try {
            double sumPN = 0;
            listPN = bll.showDSCTPhieuNhap(namHT, flag);
            tblChiPhiMuaNguyenLieu.setModel(new CustomTable_PhieuNhapBaoCaoTK(listPN));
            for (PhieuNhap phieuNhap : listPN) {
                sumPN += phieuNhap.getTongTien();
            }
            return sumPN;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn CSDL" + ex.getMessage());
            return 0;
        }
    }

    public void resetText() {
        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxLoaiThoiGian = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtNgayBatDau = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNgayKetThuc = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnThongKe = new javax.swing.JButton();
        btnInBaoCao = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDSHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChiPhiMuaNguyenLieu = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Báo cáo thống kê");

        jLabel2.setText("Loại thời gian: ");

        jLabel3.setText("Ngày bắt đầu");

        txtNgayBatDau.setText("jTextField1");

        jLabel4.setText("Ngày kết thúc");

        txtNgayKetThuc.setText("jTextField1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(63, 63, 63)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxLoaiThoiGian, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgayBatDau)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxLoaiThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Data-Filled-Filter-icon.png"))); // NOI18N
        btnThongKe.setText("Thống kê");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        btnInBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/printer-icon.png"))); // NOI18N
        btnInBaoCao.setText("In báo cáo");
        btnInBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCaoActionPerformed(evt);
            }
        });

        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Log-Out-icon.png"))); // NOI18N
        btnThoat.setText("Thoát");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tblDSHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblDSHoaDon);

        jLabel1.setText("Danh sách hóa đơn");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tblChiPhiMuaNguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblChiPhiMuaNguyenLieu);

        jLabel6.setText("Danh sách phiếu nhập");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCaoActionPerformed
        // TODO add your handling code here:
        double sumHD = 0;
        double sumPN = 0;
        if (cbxLoaiThoiGian.getSelectedItem().equals("Thống kê theo tháng")) {
            sumHD = thongKeHoaDon(month);
            sumPN = thongKePhieuNhap(month);
        }
        if (cbxLoaiThoiGian.getSelectedItem().equals("Thống kê theo ngày")) {
            sumHD = thongKeHoaDon(txtNgayBatDau.getText(), txtNgayKetThuc.getText());
            sumPN = thongKePhieuNhap(txtNgayBatDau.getText(), txtNgayKetThuc.getText());
        }
        if (cbxLoaiThoiGian.getSelectedItem().equals("Thống kê theo năm")) {
            sumHD = thongKeHoaDon(year, true);
            sumPN = thongKePhieuNhap(year, true);
        }
        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int x = j.showSaveDialog(this);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(path + "_baocao_cangtinso2.pdf"));
                document.open();
                Paragraph para = new Paragraph("                        BAO CAO DOANH THU CANG TIN SO 2 "
                        + cbxLoaiThoiGian.getSelectedItem().toString());
                para.spacingBefore();
                document.add(para);
                for (int i = 0; i < 4; i++) {
                    document.add(new Paragraph("        "));
                }
                document.add(new Paragraph("Danh sach chi tiet hoa don"));
                document.add(new Paragraph("  "));
                document.add(new Paragraph("  "));
                PdfPTable tbl = new PdfPTable(3);
                tbl.addCell("Ma hoa don");
                tbl.addCell("Ngay xuat");
                tbl.addCell("Tong tien");
                for (HoaDon hoaDon : list) {
                    String maHD = hoaDon.getMaHD();
                    Date ngayXuat = hoaDon.getNgayXuat();
                    double tongTien = hoaDon.getTongTien();

                    tbl.addCell(maHD);
                    tbl.addCell(ngayXuat.toString());
                    tbl.addCell(tongTien + "");

                }
                document.add(tbl);
                document.add(new Paragraph("                                   "
                        + "                               Tong thu = " + sumHD));

                PdfPTable tblNL = new PdfPTable(3);
                document.add(new Paragraph("  "));
                document.add(new Paragraph("  "));
                document.add(new Paragraph("Danh sach chi tiet phieu nhap"));
                document.add(new Paragraph("  "));
                document.add(new Paragraph("  "));
                tblNL.addCell("Ma phieu nhap");
                tblNL.addCell("Ngay nhap");
                tblNL.addCell("Tong tien");
                for (PhieuNhap pn : listPN) {
                    String maHD = pn.getSoPhieu();
                    Date ngayNhap = pn.getNgayNhap();
                    double tongTien = pn.getTongTien();

                    tblNL.addCell(maHD);
                    tblNL.addCell(ngayNhap.toString());
                    tblNL.addCell(tongTien + "");

                }
                document.add(tblNL);

                document.add(new Paragraph("                                   "
                        + "                               Tong chi = " + sumPN));
                if (sumHD >= sumPN) {
                    document.add(new Paragraph("                                   "
                            + "                               Lai = " + (sumHD - sumPN)));
                } else {
                    document.add(new Paragraph("                                   "
                            + "                               Lo = " + (sumPN - sumHD)));
                }

                JOptionPane.showMessageDialog(null, "Tạo thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi ko tìm thấy file", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } catch (DocumentException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi " + ex.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            }

            document.close();

        }
    }//GEN-LAST:event_btnInBaoCaoActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
        if (cbxLoaiThoiGian.getSelectedItem().equals("Thống kê theo tháng")) {
            thongKeHoaDon(month);
            thongKePhieuNhap(month);
            return;
        }
        if (cbxLoaiThoiGian.getSelectedItem().equals("Thống kê theo năm")) {
            thongKeHoaDon(year, true);
            thongKePhieuNhap(year, true);
            return;
        }
        try {
            String format = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String ngayBatDau = txtNgayBatDau.getText();
            String ngayKetThuc = txtNgayKetThuc.getText();
            if (ngayBatDau.isEmpty() || ngayKetThuc.isEmpty()) {
                throw new NullPointerException("Lỗi chưa nhập ngày để thống kê");
            }
            ngayBatDau.replace("/", "-");
            ngayKetThuc.replace("/", "-");
            sdf.setLenient(false);
            Date date = sdf.parse(ngayBatDau);
            Date date2 = sdf.parse(ngayKetThuc);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);
            String strDate1 = dateFormat.format(date2);
            thongKeHoaDon(strDate, strDate1);
            thongKePhieuNhap(strDate, strDate1);

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng năm-tháng-ngày");
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnThongKeActionPerformed

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
            java.util.logging.Logger.getLogger(dlg_BaoCaoThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlg_BaoCaoThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlg_BaoCaoThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlg_BaoCaoThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlg_BaoCaoThongKe dialog = new dlg_BaoCaoThongKe(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInBaoCao;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JComboBox<String> cbxLoaiThoiGian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblChiPhiMuaNguyenLieu;
    private javax.swing.JTable tblDSHoaDon;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    // End of variables declaration//GEN-END:variables
}
