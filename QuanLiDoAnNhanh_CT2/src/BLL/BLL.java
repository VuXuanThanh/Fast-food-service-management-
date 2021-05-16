package BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAL.DAL;
import DTO.DanhMuc;
import DTO.DoAn;
import DTO.TaiKhoan;
import DTO.TaiKhoanAdmin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vu Xuan Thanh
 */
public class BLL {

    DAL dal = new DAL();

    // form đăng nhập
    public ArrayList<TaiKhoanAdmin> showTaiKhoanAdmin() {
        ArrayList<TaiKhoanAdmin> ds = new ArrayList<>();
        String sql = "select * from taikhoanadmin";
        ds = dal.getDataTKAdmin(sql);
        return ds;
    }

    public ArrayList<TaiKhoan> showTaiKhoanNhanVien() throws SQLException {
        ArrayList<TaiKhoan> ds = new ArrayList<>();
        ResultSet res = null;
        String sql = "select tentaikhoan, matkhau from NhanVien";
        res = dal.getResultSet(sql);
        while (res.next()) {
            TaiKhoan sv = new TaiKhoan(res.getString(1), res.getString(2));
            ds.add(sv);
        }
        return ds;
    }

    // các phương thức cho form quản lí món ăn
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
         while(res.next()){
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
                + "madm =" + maDM + ", gia = " + gia + " where mamon ="+maMon+"";
        int t = dal.getStatement().executeUpdate(sql);
        return t;
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
