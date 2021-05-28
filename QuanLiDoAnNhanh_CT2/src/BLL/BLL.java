package BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAL.DAL;
import DTO.ChiTietHoaDon;
import DTO.ChiTietPhieuNhap;
import DTO.DanhMuc;
import DTO.DoAn;
import DTO.HoaDon;
import DTO.KhachHang;
import DTO.Luong;
import DTO.NguyenLieu;
import DTO.NhaCungCap;
import DTO.NhanVien;
import DTO.PhieuNhap;
import DTO.TaiKhoan;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vu Xuan Thanh branch Vũ Xuân Thành lỗi pull request---------thành vũ xuân push code
 */
public class BLL {
   
    //Nguyen Tuan Thanh sua cho nay
//    DAL dal;
//    public BLL(){
//        dal = new DAL();
//    }
    //
    DAL dal = new DAL();

    // form đăng nhập
    public ArrayList<TaiKhoan> showTaiKhoan() throws SQLException {
        ArrayList<TaiKhoan> ds = new ArrayList<>();
        ResultSet res = null;
        String sql = "select * from taikhoan";
        res = dal.getResultSet(sql);
        while (res.next()) {
            TaiKhoan sv = new TaiKhoan(res.getString(1), res.getString(2), res.getInt(3));
            ds.add(sv);
        }
        return ds;
    }

    // ---------------------Nguyễn Trung Thành---------------------
    public ArrayList<NhaCungCap> showNCC() {
        ArrayList<NhaCungCap> ds = new ArrayList<>();
        String sql = "select * from nhacungcap";
        ResultSet res = dal.getResultSet(sql);
        try {
            while (res.next()) {
                NhaCungCap ncc = new NhaCungCap(res.getInt(1),
                        res.getString(2), res.getString(3), res.getString(4));
                ds.add(ncc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }

    public boolean checkTenNCC(String ten) {
        ArrayList<NhaCungCap> ds = new ArrayList<>();
        String sql = "select * from nhacungcap WHERE tenNCC = '" + ten + "'";
        ResultSet res = dal.getResultSet(sql);
        try {
            if (res.next()) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean checkMaNL(String manl) {
        ArrayList<NhaCungCap> ds = new ArrayList<>();
        String sql = "select * from nguyenlieu WHERE tenNL = '" + manl + "'";
        ResultSet res = dal.getResultSet(sql);
        try {
            if (res.next()) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public int themNCC(String tenNCC, String diachi, String soDT) {
        int t = 0;
        try {
            t = dal.getStatement().executeUpdate("insert into nhacungcap (tenNCC,diachi,soDT) values('" + tenNCC + "', '" + diachi + "', '" + soDT + "')");
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public int xoaNCC(int maNCC) {
        int t = 0;
        try {
            t = dal.getStatement().executeUpdate("delete from nhacungcap where mancc =" + maNCC);
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public int suaNCC(int maNCC, String tenNCC, String diachi, String soDT) {
        int t = 0;
        String sql = "update nhacungcap set tenncc ='" + tenNCC + "', diachi='" + diachi + "', sodt='" + soDT + "' where mancc =" + maNCC;
        try {
            t = dal.getStatement().executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public ArrayList<NguyenLieu> showNL() {
        ArrayList<NguyenLieu> ds = new ArrayList<>();
        String sql = "select * from nguyenlieu";
        ResultSet res = dal.getResultSet(sql);
        try {
            while (res.next()) {
                NguyenLieu nl = new NguyenLieu(res.getString(1),
                        res.getString(2), res.getString(3), res.getInt(4));
                ds.add(nl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }

    public int themNL(String maNL, String tenNL, String donvitinh, int hanSD) {
        int t = 0;
        try {
            t = dal.getStatement().executeUpdate("insert into nguyenlieu (manl,tennl,donvitinh,hansudung) values('" + maNL + "', '" + tenNL + "', '" + donvitinh + "'," + hanSD + ")");
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public int xoaNL(String maNL) {
        int t = 0;
        try {
            t = dal.getStatement().executeUpdate("delete from nguyenlieu where manl ='" + maNL + "'");
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public int suaNL(String maNL, String tenNL, String donvitinh, int hanSD) {
        int t = 0;
        String sql = "update nguyenlieu set tennl ='" + tenNL + "', donvitinh='" + donvitinh + "', hansudung=" + hanSD + " where manl ='" + maNL + "'";
        try {
            t = dal.getStatement().executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    // -----------------------------------------

    // ----------------Vũ Xuân Thành
    // các phương thức cho form quản lí thực đơn
    public ArrayList<DanhMuc> showTenDanhMuc() throws SQLException {
        ArrayList<DanhMuc> ds = new ArrayList<>();
        ResultSet res = null;
        String sql = "select tendm from danhmuc";
        res = dal.getResultSet(sql);
        while (res.next()) {
            DanhMuc dm = new DanhMuc(res.getString(1));
            ds.add(dm);
        }
        return ds;
    }

    public String showTenDanhMuc2(int madm) throws SQLException {
        String kp = "";
        ResultSet res = null;
        String sql = "select tendm from danhmuc where madm =" + madm + "";
        res = dal.getResultSet(sql);
        while (res.next()) {
            kp = res.getString(1);
        }
        return kp;
    }

    public int getMaDMTheoTen(String tendm) throws SQLException {
        int ma = 0;
        String sql = "select madm from danhmuc where tendm ='" + tendm + "'";
        ResultSet res = null;
        res = dal.getResultSet(sql);
        while (res.next()) {

            ma = res.getInt(1);
        }
        return ma;
    }

    public ArrayList<DoAn> showDoAn() throws SQLException {
        ArrayList<DoAn> ds = new ArrayList<>();
        ResultSet res = null;
        String sql = "select * from doan";
        res = dal.getResultSet(sql);
        while (res.next()) {
            DoAn item = new DoAn(res.getInt(1), res.getString(2), res.getString(3),
                    res.getString(4), res.getInt(5), res.getDouble(6));
            ds.add(item);
        }
        return ds;
    }

    public int insertDoAnNhanh(String tenMon, String anh, String donViTinh,
            int maDM, double gia) throws Exception {
        String sql = "insert into doan (tenmon, anh, donvitinh, madm, gia)"
                + " values ('" + tenMon + "','" + anh + "','" + donViTinh + "'," + maDM + "," + gia + ")";
        int t = dal.getStatement().executeUpdate(sql);
        return t;
    }

    public int removeDoAnNhanh(int maMon) throws Exception {
        String sql = "delete from doan where mamon =" + maMon + "";
        int t = dal.getStatement().executeUpdate(sql);
        return t;
    }

    public int updateDoAnNhanh(int maMon, String tenMon, String anh, String donViTinh,
            int maDM, double gia) throws Exception {
        String sql = "update doan set tenmon ='" + tenMon + "', anh='" + anh + "', "
                + "donvitinh='" + donViTinh + "', "
                + "madm =" + maDM + ", gia = " + gia + " where mamon =" + maMon + "";
        int t = dal.getStatement().executeUpdate(sql);
        return t;
    }

//     các phương thức cho form quản lí phiếu nhập nguyên liệu
    public ArrayList<String> showTenNguyenLieu() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        String sql = "select tennl from nguyenlieu";
        ResultSet res = dal.getResultSet(sql);
        while (res.next()) {
            list.add(res.getString(1));
        }
        return list;
    }

    public ArrayList<String> showTenNCC() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        String sql = "select tenncc from nhacungcap";
        ResultSet res = dal.getResultSet(sql);
        while (res.next()) {
            list.add(res.getString(1));
        }
        return list;
    }

    public int showMaCCTheoTen(String tenncc) throws SQLException {
        int mancc = 0;
        String sql = "select mancc from nhacungcap where tenncc ='" + tenncc + "'";
        ResultSet res = dal.getResultSet(sql);
        while (res.next()) {
            mancc = res.getInt(1);
        }
        return mancc;
    }

    public String showMaNLTheoTen(String tenNL) throws SQLException {
        String maNL = "";
        String sql = "select manl from nguyenlieu where tennl ='" + tenNL + "'";
        ResultSet res = dal.getResultSet(sql);
        while (res.next()) {
            maNL = res.getString(1);
        }
        return maNL;
    }

    public int insertPhieuNhap(String soPhieu, String ngayNhap, int maNCC) throws Exception {
        int t = 0;
        String sql = "insert into phieunhap values ('" + soPhieu + "', date('" + ngayNhap + "'), " + maNCC + ")";
        t = dal.getStatement().executeUpdate(sql);
        return t;
    }

    public ArrayList<ChiTietPhieuNhap> showChiTietPhieuNhap() throws Exception {
        ArrayList<ChiTietPhieuNhap> list = new ArrayList<>();
        String sql = "select * from chitietphieunhap";
        ResultSet res = dal.executeQuery(sql);
        while (res.next()) {
            ChiTietPhieuNhap item = new ChiTietPhieuNhap(res.getString(1),
                    res.getString(2), res.getInt(3), res.getDouble(4));
            list.add(item);
        }
        return list;
    }

    public int insertChiTietPhieuNhap(String soPhieu, String maNL, int soLNhap, double dGNhap) throws Exception {
        int t = 0;
        String sql = "insert into chitietphieunhap values ('" + soPhieu + "', '" + maNL + "', " + soLNhap + ", " + dGNhap + ")";
        t = dal.getStatement().executeUpdate(sql);
        return t;
    }

    public int deleteChiTietPhieuNhap(String soPhieu, String maNL) throws Exception {
        int t = 0;
        String sql = "delete from chitietphieunhap where sophieu = '" + soPhieu + "' and manl ='" + maNL + "'";
        t = dal.getStatement().executeUpdate(sql);
        return t;
    }

    public ArrayList<PhieuNhap> showPhieuNhapTheoMaNCC(int maNCC) throws SQLException {
        ArrayList<PhieuNhap> list = new ArrayList<>();
        String sql = "select * from phieunhap where mancc =" + maNCC + "";
        ResultSet res = dal.getResultSet(sql);
        while (res.next()) {
            PhieuNhap pn = new PhieuNhap(res.getString(1), res.getDate(2), res.getInt(3));
            list.add(pn);
        }
        return list;
    }

    // các phương thức cho form báo cáo thống kê
    // 
    public ArrayList<HoaDon> showDSCTHoaDon(String ngayBatDau, String ngayKetThuc) throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select hoadon.mahd, ngayxuat, sum(soluong*gia) as tongtien "
                + " from hoadon inner join chitiethoadon "
                + "on hoadon.MAHD = CHITIETHOADON.MAHD "
                + "where ngayxuat >= date('" + ngayBatDau + "') and ngayxuat <= date('" + ngayKetThuc + "') "
                + "group by hoadon.mahd, ngayxuat";
        ResultSet res = dal.getResultSet(sql);
        while (res.next()) {
            HoaDon hoaDon = new HoaDon(res.getString(1), res.getDate(2), res.getDouble(3));
            list.add(hoaDon);
        }
        return list;
    }

    public ArrayList<PhieuNhap> showDSCTPhieuNhap(String ngayBatDau, String ngayKetThuc) throws SQLException {
        ArrayList<PhieuNhap> list = new ArrayList<>();
        String sql = "select phieunhap.sophieu, ngaynhap, sum(slnhap*dgnhap) as tongtien "
                + "from phieunhap inner join chitietphieunhap "
                + "on phieunhap.SOPHIEU = chitietphieunhap.sophieu "
                + "where ngaynhap >= date('" + ngayBatDau + "') and ngaynhap <='" + ngayKetThuc + "' "
                + "group by phieunhap.SOPHIEU, ngaynhap";
        ResultSet res = dal.getResultSet(sql);
        while (res.next()) {
            PhieuNhap pn = new PhieuNhap(res.getString(1), res.getDate(2), res.getDouble(3));
            list.add(pn);
        }
        return list;
    }

    // 
    public ArrayList<PhieuNhap> showDSCTPhieuNhap(int thangHienTai) throws SQLException {
        ArrayList<PhieuNhap> list = new ArrayList<>();
        String sql = "select phieunhap.sophieu, ngaynhap, sum(slnhap*dgnhap) as tongtien "
                + "from phieunhap inner join chitietphieunhap "
                + "on phieunhap.SOPHIEU = chitietphieunhap.sophieu "
                + "where month(ngaynhap) = " + thangHienTai + " "
                + "group by phieunhap.SOPHIEU, ngaynhap";
        ResultSet res = dal.getResultSet(sql);
        while (res.next()) {
            PhieuNhap pn = new PhieuNhap(res.getString(1), res.getDate(2), res.getDouble(3));
            list.add(pn);
        }
        return list;
    }

    public ArrayList<HoaDon> showDSCTHoaDon(int thangHienTai) throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select hoadon.mahd, ngayxuat, sum(soluong*gia) as tongtien "
                + " from hoadon inner join chitiethoadon "
                + "on hoadon.MAHD = CHITIETHOADON.MAHD "
                + "where month(ngayxuat) = " + thangHienTai + " "
                + "group by hoadon.mahd, ngayxuat";
        ResultSet res = dal.getResultSet(sql);
        while (res.next()) {
            HoaDon hoaDon = new HoaDon(res.getString(1), res.getDate(2), res.getDouble(3));
            list.add(hoaDon);
        }
        return list;
    }

    public ArrayList<PhieuNhap> showDSCTPhieuNhap(int namHT, boolean flag) throws SQLException {
        ArrayList<PhieuNhap> list = new ArrayList<>();
        String sql = "select phieunhap.sophieu, ngaynhap, sum(slnhap*dgnhap) as tongtien "
                + "from phieunhap inner join chitietphieunhap "
                + "on phieunhap.SOPHIEU = chitietphieunhap.sophieu "
                + "where year(ngaynhap) = " + namHT + " "
                + "group by phieunhap.SOPHIEU, ngaynhap";
        ResultSet res = dal.getResultSet(sql);
        while (res.next()) {
            PhieuNhap pn = new PhieuNhap(res.getString(1), res.getDate(2), res.getDouble(3));
            list.add(pn);
        }
        return list;
    }

    public ArrayList<HoaDon> showDSCTHoaDon(int namHT, boolean flag) throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select hoadon.mahd, ngayxuat, sum(soluong*gia) as tongtien "
                + " from hoadon inner join chitiethoadon "
                + "on hoadon.MAHD = CHITIETHOADON.MAHD "
                + "where year(ngayxuat) = " + namHT + " "
                + "group by hoadon.mahd, ngayxuat";
        ResultSet res = dal.getResultSet(sql);
        while (res.next()) {
            HoaDon hoaDon = new HoaDon(res.getString(1), res.getDate(2), res.getDouble(3));
            list.add(hoaDon);
        }
        return list;
    }

    public ArrayList getDataChiTietPhieuNhap() {
        String sql = "select * from chitietphieunhap";
        ArrayList list = dal.getDataCTPN(sql);
        return list;
    }
    //--------------------------------------------------
    

    // Nguyễn Trung Thành - DM
    public ArrayList<DanhMuc> showDM(){
        ArrayList<DanhMuc> ds = new ArrayList<>();
        String sql ="select * from danhmuc";
        ResultSet res = dal.getResultSet(sql);
        try {
            while(res.next()){
                DanhMuc dm = new DanhMuc(res.getInt(1),
                        res.getString(2));
                ds.add(dm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    public int themDM(String tenDM){
        int t =0;
        try {
            t = dal.getStatement().executeUpdate("insert into danhmuc (tendm) values('"+tenDM+"')");
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    public boolean checkTenDM(String tendm){
        String sql ="select * from danhmuc WHERE tendm = '"+tendm+"'";
        ResultSet res = dal.getResultSet(sql);
        try {
            if(res.next()){
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public int suaDM(int maDM, String tendm){
        int t =0;
        String sql ="update danhmuc set tendm ='"+tendm+"' where madm ="+maDM+"";
        try {
            t = dal.getStatement().executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    public int xoaDM(int madm){
        int t =0;
        try {
            t = dal.getStatement().executeUpdate("delete from danhmuc where madm ="+madm+"");
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

     //nguyen tuan thanh
    public void themnv(NhanVien nv) throws Exception
    {
       String sql = "Insert into NHANVIEN values('"+nv.getMaNhanVien()+"', '"+nv.getHoTen()+"', '"+nv.getGioiTinh()+"', '"+
               nv.getSoCMND()+"', '"+nv.getNgaySinh()+"', '"+nv.getDiaChi()+"', '"+nv.getSoDT()+"' )";   
       dal.doSQL(sql);
       
       
       sql = "Insert into TAIKHOAN values ('"+nv.getMaNhanVien()+"', '123456', 1)";
       dal.doSQL(sql);     
 
    }
    
    public void suanv(NhanVien nv) throws Exception
    {
        String sql = "Update NHANVIEN set TENNHANVIEN = '"+nv.getHoTen()+"', GIOITINH = '"+nv.getGioiTinh()+"', SOCMND = '"+
               nv.getSoCMND()+"', NGAYSINH =  '"+nv.getNgaySinh()+"', DIACHI = '"+nv.getDiaChi()+"',SODT = '"+nv.getSoDT()+"' where MANHANVIEN = '"+nv.getMaNhanVien()+"'";
        dal.doSQL(sql);
    }
    
    public void xoanv(String maNhanVien) throws Exception
    {
        String sql = "Delete from TAIKHOAN where TENTAIKHOAN = '"+maNhanVien+"'";
        dal.doSQL(sql);
        
        sql = "Delete from NHANVIEN where MANHANVIEN = '"+maNhanVien+"'";
        dal.doSQL(sql);
    }
    
   public void tinhLuong(Luong l) throws Exception
   {
       String sql = "Insert into LUONG values('"+l.getMaLuong()+"', " +l.getThang()+",'"+l.getMaNhanVien()+"', "+l.getSoGioLam()+", "+l.tinhLuong()+")";
       dal.doSQL(sql);
   }
   
   public void doimk(String tk, String mk){
       String sql = "update TAIKHOAN set MATKHAU = '" + mk + "' where TENTAIKHOAN ='" +tk+ "'";
       dal.doSQL(sql);
   }
   
   public ArrayList<Luong> bangLuong()
   {
       ArrayList<Luong> ds = new ArrayList<>();
       String sql = "select * from LUONG";
       ds = dal.getDataLuong(sql);
       return ds;
   }
   
   public ArrayList<NhanVien> bangNhanVien()
   {
       ArrayList<NhanVien> ds = new ArrayList<>();
       String sql = "select * from NHANVIEN";
       ds = dal.getDataNhanVien(sql);
       return ds;
   }
   
//   Phạm Trung Thế
//    Các phương thức đặt đồ ăn
    public ArrayList<KhachHang> showKhachHangTheoDK(String dK){
        ArrayList<KhachHang> ds = new ArrayList<>();
        ResultSet res = null;
        String sql = "select * from khachhang where "+dK+"";
        res = dal.getResultSet(sql);
        try {
            while (res.next()) {
                KhachHang item = new KhachHang(res.getString(1), 
                        res.getString(2),res.getString(3));
                ds.add(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return ds;
    }
    
    public ArrayList<ChiTietHoaDon> showChiTietHoaDon(String maHD){
        ArrayList<ChiTietHoaDon> ds = new ArrayList<>();
        ResultSet res = null;
        String sql = "select * from chitiethoadon where mahd = '"+maHD+"'";
        res = dal.getResultSet(sql);
        try {
            while (res.next()) {
                ChiTietHoaDon item = new ChiTietHoaDon(res.getString(1), res.getInt(2),res.getInt(3));
                ds.add(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return ds;
    }
    
    public ArrayList<DanhMuc> showDanhMuc(){
        ArrayList<DanhMuc> ds = new ArrayList<>();
        ResultSet res = null;
        String sql = "select * from danhmuc";
        res = dal.getResultSet(sql);
        try {
            while (res.next()) {
                DanhMuc dm = new DanhMuc(res.getInt(1),res.getString(2));
                ds.add(dm);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return ds;
    }
    
    public String getTenDM(int madm){
        String kp = "";
        ResultSet res = null;
        String sql = "select tendm from danhmuc where madm =" + madm + "";
        res = dal.getResultSet(sql);
        try {
            while (res.next()) {
                kp = res.getString(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return kp;
    }
    
    public ArrayList<DoAn> showDoAnTheoDK(String dK){
        ArrayList<DoAn> ds = new ArrayList<>();
        ResultSet res = null;
        String sql = "select * from doan where "+dK+"";
        res = dal.getResultSet(sql);
        try {
            while (res.next()) {
                DoAn item = new DoAn(res.getInt(1), res.getString(2), res.getString(3),
                        res.getString(4), res.getInt(5), res.getDouble(6));
                ds.add(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return ds;
    }
    
    public int insertKhachHang(String hoTen, String soDT, String diaChi){
        String sql ="insert into khachhang values ('"+soDT+"', '"+hoTen+"', '"+diaChi+"')";
        int t = 0;
        try {
            t = dal.getStatement().executeUpdate(sql);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return t;
    }
    
    public int getSoKhachHang (String soDT){
        int d=0;
        String sql = "select count(*) from khachhang where sodt ='" + soDT + "'";
        ResultSet res = null;
        res = dal.getResultSet(sql);
        try {
            d=res.getInt(1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return d;
    }
    
    public int insertHoaDon(String maHD, String maNV, String ngayXuat, String thoiGian,String soDT){
            String sql ="insert into hoadon values ('"+maHD+"', '"+maNV+"', '"
                    +ngayXuat+"', '"+thoiGian+"', '"+soDT+"')";
        int t = 0;
        try {
            t = dal.getStatement().executeUpdate(sql);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return t;
    }
    
    public void insertChiTietHoaDon(String maHD, int maMon, int soLuong){
            String sql ="insert into chitiethoadon values ('"+maHD+"', "+maMon+", "
                    +soLuong+")";
        try {
            int t = 0;
            t = dal.getStatement().executeUpdate(sql);
            if(t==0){
                throw new Exception("Không thêm được vào CSDL");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
//    Các phương thức xem thông tin hóa đơn
        public ArrayList<HoaDon> showHoaDon(){
        ArrayList<HoaDon> ds = new ArrayList<>();
        ResultSet res = null;
        String sql = "select * from hoadon";
        res = dal.getResultSet(sql);
        try {
            while (res.next()) {
                HoaDon item = new HoaDon(res.getString(1), 
                        res.getString(2),res.getDate(3), res.getTime(4),
                        res.getString(5));
                ds.add(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return ds;
    }
    
    public String getTenNV(String maNV){
        String kp = "";
        ResultSet res = null;
        String sql = "select tennhanvien from nhanvien where manhanvien ='" + maNV + "'";
        res = dal.getResultSet(sql);
        try {
            while (res.next()) {
                kp = res.getString(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return kp;
    }
    
    public ArrayList<HoaDon> showHoaDonTheoDK(String dK){
        ArrayList<HoaDon> ds = new ArrayList<>();
        ResultSet res = null;
        String sql = "select * from hoadon where "+dK+"";
        res = dal.getResultSet(sql);
        try {
            while (res.next()) {
                HoaDon item = new HoaDon(res.getString(1), 
                        res.getString(2),res.getDate(3), res.getTime(4),
                        res.getString(5));
                ds.add(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return ds;
    }
}
