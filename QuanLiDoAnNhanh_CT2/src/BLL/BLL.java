package BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAL.DAL;
import DTO.ChiTietPhieuNhap;
import DTO.DanhMuc;
import DTO.DoAn;
import DTO.NguyenLieu;
import DTO.PhieuNhap;
import DTO.TaiKhoan;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vu Xuan Thanh
 * branch Vũ Xuân Thành
 * lỗi pull request
 */
public class BLL {

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
    public int insertChiTietPhieuNhap(String soPhieu, String maNL, int soLNhap, double dGNhap) throws Exception{
        int t = 0;
        String sql ="insert into chitietphieunhap values ('"+soPhieu+"', '"+maNL+"', "+soLNhap+", "+dGNhap+")";
        t = dal.getStatement().executeUpdate(sql);
        return t;
    }
    public int deleteChiTietPhieuNhap(String soPhieu, String maNL) throws Exception{
        int t =0;
        String sql ="delete from chitietphieunhap where sophieu = '"+soPhieu+"' and manl ='"+maNL+"'";
        t = dal.getStatement().executeUpdate(sql);
        return t;
    }
    public ArrayList<PhieuNhap> showPhieuNhapTheoMaNCC(int maNCC) throws SQLException{
        ArrayList<PhieuNhap> list = new ArrayList<>();
        String sql ="select * from phieunhap where mancc ="+maNCC+"";
        ResultSet res = dal.getResultSet(sql);
        while(res.next()){
            PhieuNhap pn = new PhieuNhap(res.getString(1), res.getDate(2), res.getInt(3));
            list.add(pn);
        }
        return list;
    }
//    public ArrayList<HangHoa> showHangHoaCach2(){
//        ArrayList<HangHoa> ds = new ArrayList<>();
//        String sql ="select * from HANGHOA";
//        ResultSet res = dal.getResultSet(sql);
//        try {
//            while(res.next()){
//                HangHoa hh = new HangHoa(res.getString(1),
//                        res.getString(2), res.getString(3));
//                ds.add(hh);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
//            
//        }
//        return ds;
//    }
//    public int themHangHoa(String maHang, String tenHang, String noiSanXuat){
//        int t =0;
//        try {
//            t = dal.getStatement().executeUpdate("insert into HANGHOA values('"+maHang+"','"+tenHang+"', '"+noiSanXuat+"')");
//        } catch (Exception ex) {
//            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return t;
//    }
//    public int xoaHangHoa(String maHang){
//        int t =0;
//        try {
//            t = dal.getStatement().executeUpdate("delete from HANGHOA where MAHANG ='"+maHang+"'");
//        } catch (Exception ex) {
//            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return t;
//    }
//    public int updateHangHoa(String maHang, String tenHang, String noiSanXuat){
//        int t =0;
//        String sql ="update HANGHOA set TENHANG ='"+tenHang+"', NOISANXUAT='"+noiSanXuat+"' where MAHANG ='"+maHang+"'";
//        try {
//            t = dal.getStatement().executeUpdate(sql);
//        } catch (Exception ex) {
//            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return t;
//    }
}
