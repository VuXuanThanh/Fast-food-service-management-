/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL;
import DTO.ChiTietHoaDon;
import DTO.CustomTable_ChiTietHoaDon;
import DTO.CustomTable_DoAnNhanh;
import DTO.DanhMuc;
import DTO.DoAn;
import DTO.DongHoHeThong;
import DTO.HinhAnh;
import DTO.HoaDon;
import DTO.KhachHang;
import DTO.NhanVien;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author phamt
 */
public class DlgDatDoAn extends javax.swing.JDialog {

    BLL bll = new BLL();
    ArrayList<DanhMuc> listDM = new ArrayList<>();
    ArrayList<DoAn> listDoAn = new ArrayList<>();
    ArrayList<ChiTietHoaDon> listCTHD = new ArrayList<>();;
    DecimalFormat dingDangTien = new DecimalFormat("###,###,### VNĐ");
    long khachTra;
    long phiGiaoHang;
    /**
     * Creates new form NewJDialog
     */
    public DlgDatDoAn(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        try {
            listDoAn = bll.showDoAn();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        
        listDM = bll.showDanhMuc();
        nhanVien();
        hienNgayGio();
        loadCmbDanhMuc();
        loadDoAnNhanh(listDoAn);
        
        hienThiMacDinh();
    }

    private void hienThiMacDinh() {
        khachTra = 0;
        phiGiaoHang = 0;
        listCTHD = new ArrayList<>();
        MacDinh();
        pnlKH.setVisible(false);
        loadChiTietHoaDon(listCTHD);
        taoHoaDon();
        thanhToan();
    }

    private void loadCmbDanhMuc(){
        Vector<String> tam = new Vector<>();
        tam.add("Tất cả");
        listDM.forEach((t) -> {
            tam.add(t.getTenDM());
        });
        cmbDanhMuc.setModel(new DefaultComboBoxModel<>(tam));
    }
    
    private void loadDoAnNhanh(ArrayList<DoAn> listDoAn){
        tblDoAn.setModel(new CustomTable_DoAnNhanh(listDoAn));
    }
    
    private void loadChiTietHoaDon(ArrayList<ChiTietHoaDon> listCTHD){
        tblChiTietHoaDon.setModel(new CustomTable_ChiTietHoaDon(listCTHD));
    }
    
    private void MacDinh(){
        lblMaMon.setText("");
        lblTenMon.setText("");
        lblDonViTinh.setText("");
        lblDanhMuc.setText("");
        lblGia.setText("");
        lblAnh.setIcon(null);
        lblThongBao.setText("");
        spnSoLuong.setValue(1);
        
        btnThem.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        
        if(listCTHD.size()<1){
            btn1K.setEnabled(false);
            btn2K.setEnabled(false);
            btn5K.setEnabled(false);
            btn10K.setEnabled(false);
            btn20K.setEnabled(false);
            btn50K.setEnabled(false);
            btn1L.setEnabled(false);
            btn2L.setEnabled(false);
            btn5L.setEnabled(false);
            
            txtKhachTra.setEnabled(false);
            btnThanhToan.setEnabled(false);
            btnInHD.setEnabled(false);
        }else{
            btn1K.setEnabled(true);
            btn2K.setEnabled(true);
            btn5K.setEnabled(true);
            btn10K.setEnabled(true);
            btn20K.setEnabled(true);
            btn50K.setEnabled(true);
            btn1L.setEnabled(true);
            btn2L.setEnabled(true);
            btn5L.setEnabled(true);
            
            txtKhachTra.setEnabled(true);
            btnThanhToan.setEnabled(true);
            btnInHD.setEnabled(true);
        }
        
    }
    
    private void nhanVien(){ 
        String tenTKDN = frmDangNhap.tenTK;
        NhanVien nhanVien = new NhanVien();
        String dK = "tentaikhoan = '"+tenTKDN+"'";
        nhanVien = bll.showNhanVienTheoDK(dK).get(0);
        lblMaNV.setText(nhanVien.getMaNhanVien());
        lblHoTenNV.setText(nhanVien.getHoTen());
    }
    
    private void taoHoaDon(){
        ArrayList<HoaDon> listHD = new ArrayList<>();
        listHD = bll.showHoaDon();
        int kt = listHD.size();
        HoaDon hoaDon = new HoaDon();
        
        if(kt > 0){
            SimpleDateFormat sdfNgay = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String tam1 = sdfNgay.format(d);
            String tam2 = listHD.get(kt-1).getNgayXuat();
            if(tam1.equals(tam2)){
                String maHD = listHD.get(kt-1).getMaHD();
                String[] listMa = maHD.split("\\-");
                int ma = Integer.parseInt(listMa[1]);
                hoaDon.setMaHD(ma+1);
            }else{
                hoaDon.setMaHD(1);
            }
        }else{
            hoaDon.setMaHD(1);
        }
        lblMaHoaDon.setText(hoaDon.getMaHD());
    }
    
    private void hienNgayGio(){
        DongHoHeThong th = new DongHoHeThong(lblGio,lblNgay);
        th.start();
    }
    
    private void hienThongTinDoAn(DoAn doAn){
        lblMaMon.setText(doAn.getMaMon()+"");
        lblTenMon.setText(doAn.getTenMon());
        lblDonViTinh.setText(doAn.getDonViTinh());
        lblGia.setText(dingDangTien.format(doAn.getGia()));
        lblDanhMuc.setText(doAn.getTenDM());
        
        File f = new File(doAn.getAnh());
        ImageIcon icon = new ImageIcon(f.getPath());
        Image img = HinhAnh.resize(icon.getImage(), lblAnh.getWidth(), lblAnh.getHeight());
        ImageIcon iconMoi = new ImageIcon(img);
        lblAnh.setIcon(iconMoi);
    }
    
    
    private void kiemTraCTHD() {
        String maHD = lblMaHoaDon.getText();
        int maMon = Integer.parseInt(lblMaMon.getText());
        int soLuong = (int)spnSoLuong.getValue();
        int kT = 0;
        Iterator<ChiTietHoaDon> it = listCTHD.iterator();
        while (it.hasNext()) {
            ChiTietHoaDon next = it.next();
            int ma = next.getMaMon();
            if(maMon==ma){
                next.setSoLuong(next.getSoLuong()+soLuong);
                kT = 1;
                break;
            }
        }
        if(kT==0){
            ChiTietHoaDon cTHD = new ChiTietHoaDon();
            cTHD.setMaMon(maMon);
            listCTHD.add(new ChiTietHoaDon(maHD, maMon, soLuong,cTHD.getDoAn().getGia()));
        }
        
    }
    
    private long tienHang(ArrayList<ChiTietHoaDon> listCTHD){
        Iterator<ChiTietHoaDon> it = listCTHD.iterator();
        long tongTien=0;
        while (it.hasNext()) {
            ChiTietHoaDon next = it.next();
            tongTien+=next.thanhTien();
        }
        return tongTien;
    }
    
    private void thanhToan(){
        long tienHang = tienHang(listCTHD);
        long tongTien = tienHang+phiGiaoHang;
        long tienThua = khachTra - tongTien;
        lblTienHang.setText(dingDangTien.format(tienHang));
        lblPhiGiaoHang.setText(dingDangTien.format(phiGiaoHang));
        lblTongTien.setText(dingDangTien.format(tongTien));
        txtKhachTra.setText(khachTra+"");
        lblTienThua.setForeground(Color.BLACK);
        lblTienThua.setText(dingDangTien.format(tienThua));
    }
    
    private void themHDVaoCSDL() {
        try{
            String maHD = lblMaHoaDon.getText();
            String maNV = lblMaNV.getText();
            DongHoHeThong dH = new DongHoHeThong(lblNgay.getText());
            String ngayXuat = dH.getNgayVaoCSDL();
            String thoiGian = lblGio.getText();
            String soDT = "Khách lẻ";
            int kT = 0;
            if(radDatGiao.isSelected()){
                    String hoTenKH = txtHoTenKH.getText();
                    String soDTKH = txtDienThoaiKH.getText();
                    String diaChiKH = txtDiaChiKH.getText();

                    if(hoTenKH.isEmpty() || soDTKH.isEmpty() || diaChiKH.isEmpty()){
                        throw new NullPointerException(
                                "Thông tin khách hàng không được để trống");
                    }
                    soDT = soDTKH;
                    if(soDT.length() > 10){
                        throw new Exception("Số điện thoại khách hàng không được quá 10 số");
                    }
                    if(!soDT.matches("\\d+")){
                        throw new Exception("Số điện thoại khách hàng phải là số");
                    }
                    if(bll.getSoKhachHang(soDTKH).size() == 0){
                        kT = bll.insertKhachHang(soDTKH, hoTenKH, diaChiKH);
                    }
            }
            kT = bll.insertHoaDon(maHD, maNV, ngayXuat, thoiGian, soDT);
            if(kT==1){
                listCTHD.forEach((t) -> {
                    bll.insertChiTietHoaDon(t.getMaHD(), t.getMaMon(),
                            t.getSoLuong(),t.getGia());
                });
                JOptionPane.showMessageDialog(null, "Hóa đơn đã được tạo",
                        "Thành công", JOptionPane.INFORMATION_MESSAGE);
                radKhachLe.setSelected(true);
                hienThiMacDinh();
            }
        }catch(NullPointerException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),
            "Lỗi", JOptionPane.ERROR_MESSAGE);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),
            "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupLoaiHD = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        pnlKH = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtDienThoaiKH = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtHoTenKH = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChiKH = new javax.swing.JTextField();
        pnlThanhNgang = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pnlDoAn = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoAn = new javax.swing.JTable();
        pnlTimKiem = new javax.swing.JPanel();
        btnTimKiem = new javax.swing.JButton();
        jPanelTimKiem = new javax.swing.JPanel();
        pnlDanhMuc = new javax.swing.JPanel();
        jLabelDanhMuc = new javax.swing.JLabel();
        cmbDanhMuc = new javax.swing.JComboBox<>();
        jPanelTenDoAn = new javax.swing.JPanel();
        jLabelTenDoAn = new javax.swing.JLabel();
        txtTenMon = new javax.swing.JTextField();
        pnlThongTinDoAn = new javax.swing.JPanel();
        jPanelThongTinDoAn = new javax.swing.JPanel();
        pnlMaMon = new javax.swing.JPanel();
        jLabelMaMon = new javax.swing.JLabel();
        lblMaMon = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lblTenMon = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        lblDanhMuc = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblDonViTinh = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        spnSoLuong = new javax.swing.JSpinner();
        pnlButton = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        lblThongBao = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        pnlThanhToan = new javax.swing.JPanel();
        pnlSoTien = new javax.swing.JPanel();
        btn1K = new javax.swing.JButton();
        btn2K = new javax.swing.JButton();
        btn5K = new javax.swing.JButton();
        btn10K = new javax.swing.JButton();
        btn20K = new javax.swing.JButton();
        btn50K = new javax.swing.JButton();
        btn1L = new javax.swing.JButton();
        btn2L = new javax.swing.JButton();
        btn5L = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        lblTienHang = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        lblPhiGiaoHang = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblTongTien = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtKhachTra = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        lblTienThua = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        btnInHD = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        pnlNhanVien = new javax.swing.JPanel();
        pnlMaNV = new javax.swing.JPanel();
        jLabelMaNV = new javax.swing.JLabel();
        lblMaNV = new javax.swing.JLabel();
        pnlHoTenNV = new javax.swing.JPanel();
        jLabelTenNV = new javax.swing.JLabel();
        lblHoTenNV = new javax.swing.JLabel();
        pnlLoaiHD = new javax.swing.JPanel();
        jLabelKhachMua = new javax.swing.JLabel();
        radKhachLe = new javax.swing.JRadioButton();
        radDatGiao = new javax.swing.JRadioButton();
        pnlNHoaDon = new javax.swing.JPanel();
        jLabelMaMaHoaDon = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        jLabelMaMaHoaDon1 = new javax.swing.JLabel();
        lblNgay = new javax.swing.JLabel();
        jLabelMaMaHoaDon2 = new javax.swing.JLabel();
        lblGio = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietHoaDon = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        pnlKH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlKH.setLayout(new java.awt.GridLayout(3, 0));

        jLabel3.setText("Điện thoại:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDienThoaiKH, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addComponent(txtDienThoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlKH.add(jPanel12);

        jLabel2.setText("Họ tên khách hàng:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHoTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addComponent(txtHoTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlKH.add(jPanel10);

        jLabel5.setText("Địa chỉ:");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiaChiKH, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addComponent(txtDiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlKH.add(jPanel13);

        jPanel8.add(pnlKH);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Đặt đồ ăn");

        javax.swing.GroupLayout pnlThanhNgangLayout = new javax.swing.GroupLayout(pnlThanhNgang);
        pnlThanhNgang.setLayout(pnlThanhNgangLayout);
        pnlThanhNgangLayout.setHorizontalGroup(
            pnlThanhNgangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlThanhNgangLayout.setVerticalGroup(
            pnlThanhNgangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        pnlDoAn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblDoAn.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDoAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoAnMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDoAn);

        pnlTimKiem.setLayout(new java.awt.GridLayout(1, 0));

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search-icon (1).png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });
        pnlTimKiem.add(btnTimKiem);

        jPanelTimKiem.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        jLabelDanhMuc.setText("Danh mục: ");

        cmbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDanhMucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDanhMucLayout = new javax.swing.GroupLayout(pnlDanhMuc);
        pnlDanhMuc.setLayout(pnlDanhMucLayout);
        pnlDanhMucLayout.setHorizontalGroup(
            pnlDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhMucLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlDanhMucLayout.setVerticalGroup(
            pnlDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addComponent(cmbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelTimKiem.add(pnlDanhMuc);

        jLabelTenDoAn.setText("Tên đồ ăn: ");

        javax.swing.GroupLayout jPanelTenDoAnLayout = new javax.swing.GroupLayout(jPanelTenDoAn);
        jPanelTenDoAn.setLayout(jPanelTenDoAnLayout);
        jPanelTenDoAnLayout.setHorizontalGroup(
            jPanelTenDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTenDoAnLayout.createSequentialGroup()
                .addComponent(jLabelTenDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenMon, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelTenDoAnLayout.setVerticalGroup(
            jPanelTenDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTenDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelTenDoAn, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addComponent(txtTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelTimKiem.add(jPanelTenDoAn);

        javax.swing.GroupLayout pnlDoAnLayout = new javax.swing.GroupLayout(pnlDoAn);
        pnlDoAn.setLayout(pnlDoAnLayout);
        pnlDoAnLayout.setHorizontalGroup(
            pnlDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoAnLayout.createSequentialGroup()
                .addComponent(jPanelTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        pnlDoAnLayout.setVerticalGroup(
            pnlDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoAnLayout.createSequentialGroup()
                .addGroup(pnlDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );

        pnlThongTinDoAn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanelThongTinDoAn.setLayout(new java.awt.GridLayout(6, 0));

        jLabelMaMon.setText("Mã món:");

        lblMaMon.setText("ND");

        javax.swing.GroupLayout pnlMaMonLayout = new javax.swing.GroupLayout(pnlMaMon);
        pnlMaMon.setLayout(pnlMaMonLayout);
        pnlMaMonLayout.setHorizontalGroup(
            pnlMaMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaMonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelMaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMaMon, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlMaMonLayout.setVerticalGroup(
            pnlMaMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaMonLayout.createSequentialGroup()
                .addGroup(pnlMaMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82))
        );

        jPanelThongTinDoAn.add(pnlMaMon);

        jLabel17.setText("Tên món:");

        lblTenMon.setText("ND");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenMon, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelThongTinDoAn.add(jPanel40);

        jLabel22.setText("Danh mục:");

        lblDanhMuc.setText("ND");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelThongTinDoAn.add(jPanel41);

        jLabel9.setText("Đơn vị tính:");

        lblDonViTinh.setText("ND");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDonViTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        jPanelThongTinDoAn.add(jPanel38);

        jLabel30.setText("Giá:");

        lblGia.setText("ND");

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGia, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82))
        );

        jPanelThongTinDoAn.add(jPanel42);

        jLabel32.setText("Số lượng");

        spnSoLuong.setModel(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82))
        );

        jPanelThongTinDoAn.add(jPanel43);

        pnlButton.setLayout(new java.awt.GridLayout(3, 0, 0, 10));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Button-Add-icon.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnlButton.add(btnThem);

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Actions-document-edit-icon.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnlButton.add(btnSua);

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/trash-icon.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlButton.add(btnXoa);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblThongBao.setForeground(new java.awt.Color(0, 153, 51));
        lblThongBao.setText("ND");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThongBao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThongBao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlThongTinDoAnLayout = new javax.swing.GroupLayout(pnlThongTinDoAn);
        pnlThongTinDoAn.setLayout(pnlThongTinDoAnLayout);
        pnlThongTinDoAnLayout.setHorizontalGroup(
            pnlThongTinDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinDoAnLayout.createSequentialGroup()
                .addComponent(jPanelThongTinDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlThongTinDoAnLayout.setVerticalGroup(
            pnlThongTinDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinDoAnLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlThongTinDoAnLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelThongTinDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlThanhToan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pnlSoTien.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Số tiền khách trả"));
        pnlSoTien.setLayout(new java.awt.GridLayout(3, 3));

        btn1K.setText("1,000");
        btn1K.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1KActionPerformed(evt);
            }
        });
        pnlSoTien.add(btn1K);

        btn2K.setText("2,000");
        btn2K.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2KActionPerformed(evt);
            }
        });
        pnlSoTien.add(btn2K);

        btn5K.setText("5,000");
        btn5K.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5KActionPerformed(evt);
            }
        });
        pnlSoTien.add(btn5K);

        btn10K.setText("10,000");
        btn10K.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10KActionPerformed(evt);
            }
        });
        pnlSoTien.add(btn10K);

        btn20K.setText("20,000");
        btn20K.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn20KActionPerformed(evt);
            }
        });
        pnlSoTien.add(btn20K);

        btn50K.setText("50,000");
        btn50K.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn50KActionPerformed(evt);
            }
        });
        pnlSoTien.add(btn50K);

        btn1L.setText("100,000");
        btn1L.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1LActionPerformed(evt);
            }
        });
        pnlSoTien.add(btn1L);

        btn2L.setText("200,000");
        btn2L.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2LActionPerformed(evt);
            }
        });
        pnlSoTien.add(btn2L);

        btn5L.setText("500,000");
        btn5L.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5LActionPerformed(evt);
            }
        });
        pnlSoTien.add(btn5L);

        jPanel2.setPreferredSize(new java.awt.Dimension(250, 140));
        jPanel2.setLayout(new java.awt.GridLayout(5, 0));

        lblTienHang.setText("ND");

        jLabel10.setText("Tiền hàng:");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTienHang, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(lblTienHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel16);

        lblPhiGiaoHang.setText("ND");

        jLabel11.setText("Phí giao hàng:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPhiGiaoHang, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(lblPhiGiaoHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel17);

        lblTongTien.setText("ND");

        jLabel1.setText("Tổng tiền:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4);

        jLabel8.setText("Khách trả:");

        txtKhachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKhachTraKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel15);

        lblTienThua.setText("ND");

        jLabel6.setText("Tiền thừa:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(lblTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel6);

        jPanel3.setLayout(new java.awt.GridLayout(3, 0, 0, 10));

        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Dollar-USD-icon.png"))); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel3.add(btnThanhToan);

        btnInHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/printer-icon.png"))); // NOI18N
        btnInHD.setText("In hóa đơn");
        btnInHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHDActionPerformed(evt);
            }
        });
        jPanel3.add(btnInHD);

        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/arrow-back-icon_1.png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jPanel3.add(btnThoat);

        javax.swing.GroupLayout pnlThanhToanLayout = new javax.swing.GroupLayout(pnlThanhToan);
        pnlThanhToan.setLayout(pnlThanhToanLayout);
        pnlThanhToanLayout.setHorizontalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(pnlSoTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlThanhToanLayout.setVerticalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
            .addGroup(pnlThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSoTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlNhanVien.setLayout(new java.awt.GridLayout(3, 0));

        jLabelMaNV.setText("Mã nhân viên:");

        lblMaNV.setText("ND");

        javax.swing.GroupLayout pnlMaNVLayout = new javax.swing.GroupLayout(pnlMaNV);
        pnlMaNV.setLayout(pnlMaNVLayout);
        pnlMaNVLayout.setHorizontalGroup(
            pnlMaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaNVLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlMaNVLayout.setVerticalGroup(
            pnlMaNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(jLabelMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlNhanVien.add(pnlMaNV);

        jLabelTenNV.setText("Họ tên nhân viên:");

        lblHoTenNV.setText("ND");

        javax.swing.GroupLayout pnlHoTenNVLayout = new javax.swing.GroupLayout(pnlHoTenNV);
        pnlHoTenNV.setLayout(pnlHoTenNVLayout);
        pnlHoTenNVLayout.setHorizontalGroup(
            pnlHoTenNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoTenNVLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHoTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlHoTenNVLayout.setVerticalGroup(
            pnlHoTenNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(lblHoTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlNhanVien.add(pnlHoTenNV);

        jLabelKhachMua.setText("Khách hàng:");

        btnGroupLoaiHD.add(radKhachLe);
        radKhachLe.setSelected(true);
        radKhachLe.setText("Khách lẻ");
        radKhachLe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radKhachLeActionPerformed(evt);
            }
        });

        btnGroupLoaiHD.add(radDatGiao);
        radDatGiao.setText("Đặt giao");
        radDatGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDatGiaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLoaiHDLayout = new javax.swing.GroupLayout(pnlLoaiHD);
        pnlLoaiHD.setLayout(pnlLoaiHDLayout);
        pnlLoaiHDLayout.setHorizontalGroup(
            pnlLoaiHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoaiHDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelKhachMua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radKhachLe, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(radDatGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlLoaiHDLayout.setVerticalGroup(
            pnlLoaiHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoaiHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(radKhachLe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(radDatGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabelKhachMua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlNhanVien.add(pnlLoaiHD);

        pnlNHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlNHoaDon.setLayout(new java.awt.GridLayout(3, 2, 10, 0));

        jLabelMaMaHoaDon.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelMaMaHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelMaMaHoaDon.setText("Mã hóa đơn:");
        pnlNHoaDon.add(jLabelMaMaHoaDon);

        lblMaHoaDon.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblMaHoaDon.setText("ND");
        pnlNHoaDon.add(lblMaHoaDon);

        jLabelMaMaHoaDon1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelMaMaHoaDon1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelMaMaHoaDon1.setText("Ngày:");
        pnlNHoaDon.add(jLabelMaMaHoaDon1);

        lblNgay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNgay.setText("ND");
        pnlNHoaDon.add(lblNgay);

        jLabelMaMaHoaDon2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelMaMaHoaDon2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelMaMaHoaDon2.setText("Giờ:");
        pnlNHoaDon.add(jLabelMaMaHoaDon2);

        lblGio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblGio.setText("ND");
        pnlNHoaDon.add(lblGio);

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblChiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        tblChiTietHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChiTietHoaDon);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlThanhNgang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlDoAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlNHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(pnlNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pnlThongTinDoAn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(pnlThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(13, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlThanhNgang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlNHoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlNhanVien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlThongTinDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlDoAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDoAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoAnMouseClicked
        // TODO add your handling code here:
        try{
            lblThongBao.setText("");
            int dong = tblDoAn.getSelectedRow();
            
            btnThem.setEnabled(true);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            
            DoAn doAn = new DoAn();
            doAn = listDoAn.get(dong);
            hienThongTinDoAn(doAn);
            spnSoLuong.setValue(1);
        }
        catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Dòng chọn không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblDoAnMouseClicked

    private void cmbDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDanhMucActionPerformed
        // TODO add your handling code here:
        String tam = (String) cmbDanhMuc.getSelectedItem();
        if (tam.equals("Tất cả")) {
            try {
                listDoAn = bll.showDoAn();
            } catch (SQLException ex) {
                Logger.getLogger(DlgDatDoAn.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            listDM.forEach((t) -> {
                if(tam.equals(t.getTenDM())){
                    String dK = "madm = "+t.getMaDM()+"";
                    listDoAn = bll.showDoAnTheoDK(dK);
                }
            });
        }
        loadDoAnNhanh(listDoAn);
        txtTenMon.setText("");
    }//GEN-LAST:event_cmbDanhMucActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        try{
            kiemTraCTHD();
            loadChiTietHoaDon(listCTHD);
            thanhToan();
            MacDinh();
            lblThongBao.setText("Thêm thành công");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int dong = tblChiTietHoaDon.getSelectedRow();
        int soLuong = (int) spnSoLuong.getValue();
        listCTHD.get(dong).setSoLuong(soLuong);
        loadChiTietHoaDon(listCTHD);
        thanhToan();
        MacDinh();
        lblThongBao.setText("Sửa thành công");
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int dong = tblChiTietHoaDon.getSelectedRow();
        listCTHD.remove(dong);
        loadChiTietHoaDon(listCTHD);
        thanhToan();
        MacDinh();
        lblThongBao.setText("Xóa thành công");
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String tenMon= txtTenMon.getText();
        try{
            if(tenMon.isEmpty()){
                throw new NullPointerException("Tên món nhập vào bị trống");
            }else{
                ArrayList<DoAn> list = new ArrayList<>();
                Iterator<DoAn> it = listDoAn.iterator();
                while (it.hasNext()) {
                    DoAn next = it.next();
                    String tam = next.getTenMon();
                    if(tam.equalsIgnoreCase(tenMon)){
                        list.add(next);
                    }
                }
                loadDoAnNhanh(list);
            }
        }catch(NullPointerException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btn1KActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1KActionPerformed
        // TODO add your handling code here:
        khachTra+=1000;
        thanhToan();
    }//GEN-LAST:event_btn1KActionPerformed

    private void btn2KActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2KActionPerformed
        // TODO add your handling code here:
        khachTra+=2000;
        thanhToan();
    }//GEN-LAST:event_btn2KActionPerformed

    private void btn5KActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5KActionPerformed
        // TODO add your handling code here:
        khachTra+=5000;
        thanhToan();
    }//GEN-LAST:event_btn5KActionPerformed

    private void btn10KActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10KActionPerformed
        // TODO add your handling code here:
        khachTra+=10000;
        thanhToan();
    }//GEN-LAST:event_btn10KActionPerformed

    private void btn20KActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn20KActionPerformed
        // TODO add your handling code here:
        khachTra+=20000;
        thanhToan();
    }//GEN-LAST:event_btn20KActionPerformed

    private void btn50KActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn50KActionPerformed
        // TODO add your handling code here:
        khachTra+=50000;
        thanhToan();
    }//GEN-LAST:event_btn50KActionPerformed

    private void btn1LActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1LActionPerformed
        // TODO add your handling code here:
        khachTra+=100000;
        thanhToan();
    }//GEN-LAST:event_btn1LActionPerformed

    private void btn2LActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2LActionPerformed
        // TODO add your handling code here:
        khachTra+=200000;
        thanhToan();
    }//GEN-LAST:event_btn2LActionPerformed

    private void btn5LActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5LActionPerformed
        // TODO add your handling code here:
        khachTra+=500000;
        thanhToan();
    }//GEN-LAST:event_btn5LActionPerformed

    private void tblChiTietHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietHoaDonMouseClicked
        // TODO add your handling code here:
        try{
            btnThem.setEnabled(false);
            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);
            lblThongBao.setText("");
            
            int dong = tblChiTietHoaDon.getSelectedRow();
            hienThongTinDoAn(listCTHD.get(dong).getDoAn());
            spnSoLuong.setValue(listCTHD.get(dong).getSoLuong());
        }
        catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Dòng chọn không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblChiTietHoaDonMouseClicked

    private void radKhachLeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radKhachLeActionPerformed
        // TODO add your handling code here:
        pnlKH.setVisible(false);
        phiGiaoHang = 0;
        thanhToan();
    }//GEN-LAST:event_radKhachLeActionPerformed

    private void radDatGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDatGiaoActionPerformed
        // TODO add your handling code here:
        pnlKH.setVisible(true);
        phiGiaoHang = 5000;
        thanhToan();
    }//GEN-LAST:event_radDatGiaoActionPerformed

    private void txtKhachTraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKhachTraKeyReleased
        // TODO add your handling code here:
        try{
            if (txtKhachTra.getText().isEmpty()) {
                throw new NullPointerException("Số tiền khách trả không được để trống");
            }else{
                if(!txtKhachTra.getText().matches("\\d+")){
                    throw new NumberFormatException("Số tiền khách trả phải là một số dương");
                }
            }
            khachTra = Long.parseLong(txtKhachTra.getText());
            thanhToan();
        }catch(NullPointerException | NumberFormatException ex){
            lblTienThua.setForeground(Color.RED);
            lblTienThua.setText(ex.getMessage());
        }
    }//GEN-LAST:event_txtKhachTraKeyReleased

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        themHDVaoCSDL();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnInHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHDActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnInHDActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        int kt =JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn thoát. Dữ liệu đang nhập sẽ không được lưu",
                "Vui lòng xác nhận", JOptionPane.YES_NO_OPTION);
        if(kt == JOptionPane.YES_OPTION){
            this.setVisible(false);
//            new .setVisible(true);
        }
    }//GEN-LAST:event_btnThoatActionPerformed

    

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
            java.util.logging.Logger.getLogger(DlgDatDoAn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgDatDoAn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgDatDoAn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgDatDoAn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgDatDoAn dialog = new DlgDatDoAn(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn10K;
    private javax.swing.JButton btn1K;
    private javax.swing.JButton btn1L;
    private javax.swing.JButton btn20K;
    private javax.swing.JButton btn2K;
    private javax.swing.JButton btn2L;
    private javax.swing.JButton btn50K;
    private javax.swing.JButton btn5K;
    private javax.swing.JButton btn5L;
    private javax.swing.ButtonGroup btnGroupLoaiHD;
    private javax.swing.JButton btnInHD;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cmbDanhMuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDanhMuc;
    private javax.swing.JLabel jLabelKhachMua;
    private javax.swing.JLabel jLabelMaMaHoaDon;
    private javax.swing.JLabel jLabelMaMaHoaDon1;
    private javax.swing.JLabel jLabelMaMaHoaDon2;
    private javax.swing.JLabel jLabelMaMon;
    private javax.swing.JLabel jLabelMaNV;
    private javax.swing.JLabel jLabelTenDoAn;
    private javax.swing.JLabel jLabelTenNV;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelTenDoAn;
    private javax.swing.JPanel jPanelThongTinDoAn;
    private javax.swing.JPanel jPanelTimKiem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblDanhMuc;
    private javax.swing.JLabel lblDonViTinh;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblGio;
    private javax.swing.JLabel lblHoTenNV;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblMaMon;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblPhiGiaoHang;
    private javax.swing.JLabel lblTenMon;
    private javax.swing.JLabel lblThongBao;
    private javax.swing.JLabel lblTienHang;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlDanhMuc;
    private javax.swing.JPanel pnlDoAn;
    private javax.swing.JPanel pnlHoTenNV;
    private javax.swing.JPanel pnlKH;
    private javax.swing.JPanel pnlLoaiHD;
    private javax.swing.JPanel pnlMaMon;
    private javax.swing.JPanel pnlMaNV;
    private javax.swing.JPanel pnlNHoaDon;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlSoTien;
    private javax.swing.JPanel pnlThanhNgang;
    private javax.swing.JPanel pnlThanhToan;
    private javax.swing.JPanel pnlThongTinDoAn;
    private javax.swing.JPanel pnlTimKiem;
    private javax.swing.JRadioButton radDatGiao;
    private javax.swing.JRadioButton radKhachLe;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTable tblChiTietHoaDon;
    private javax.swing.JTable tblDoAn;
    private javax.swing.JTextField txtDiaChiKH;
    private javax.swing.JTextField txtDienThoaiKH;
    private javax.swing.JTextField txtHoTenKH;
    private javax.swing.JTextField txtKhachTra;
    private javax.swing.JTextField txtTenMon;
    // End of variables declaration//GEN-END:variables
}
