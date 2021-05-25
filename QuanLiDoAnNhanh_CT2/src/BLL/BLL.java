package BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAL.DAL;


import DTO.TaiKhoan;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vu Xuan Thanh branch Vũ Xuân Thành lỗi pull request
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

    public ArrayList<NhaCungCap> showNCC(){
        ArrayList<NhaCungCap> ds = new ArrayList<>();
        String sql ="select * from nhacungcap";
        ResultSet res = dal.getResultSet(sql);
        try {
            while(res.next()){
                NhaCungCap ncc = new NhaCungCap(res.getInt(1),
                        res.getString(2), res.getString(3),res.getString(4));
                ds.add(ncc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    public boolean checkTenNCC(String ten){
        ArrayList<NhaCungCap> ds = new ArrayList<>();
        String sql ="select * from nhacungcap WHERE tenNCC = '"+ten+"'";
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
    public boolean checkMaNL(String manl){
        ArrayList<NhaCungCap> ds = new ArrayList<>();
        String sql ="select * from nguyenlieu WHERE tenNL = '"+manl+"'";
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
    public int themNCC(String tenNCC, String diachi, String soDT){
        int t =0;
        try {
            t = dal.getStatement().executeUpdate("insert into nhacungcap (tenNCC,diachi,soDT) values('"+tenNCC+"', '"+diachi+"', '"+soDT+"')");
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    public int xoaNCC(int maNCC){
        int t =0;
        try {
            t = dal.getStatement().executeUpdate("delete from nhacungcap where mancc ="+maNCC);
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    public int suaNCC(int maNCC, String tenNCC, String diachi, String soDT){
        int t =0;
        String sql ="update nhacungcap set tenncc ='"+tenNCC+"', diachi='"+diachi+"', sodt='"+soDT+"' where mancc ="+maNCC;
        try {
            t = dal.getStatement().executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    public ArrayList<NguyenLieu> showNL(){
        ArrayList<NguyenLieu> ds = new ArrayList<>();
        String sql ="select * from nguyenlieu";
        ResultSet res = dal.getResultSet(sql);
        try {
            while(res.next()){
                NguyenLieu nl = new NguyenLieu(res.getString(1),
                        res.getString(2), res.getString(3),res.getInt(4));
                ds.add(nl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    public int themNL(String maNL, String tenNL, String donvitinh, int hanSD){
        int t =0;
        try {
            t = dal.getStatement().executeUpdate("insert into nguyenlieu (manl,tennl,donvitinh,hansudung) values('"+maNL+"', '"+tenNL+"', '"+donvitinh+"',"+hanSD+")");
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    public int xoaNL(String maNL){
        int t =0;
        try {
            t = dal.getStatement().executeUpdate("delete from nguyenlieu where manl ='"+maNL+"'");
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    public int suaNL(String maNL, String tenNL, String donvitinh, int hanSD){
        int t =0;
        String sql ="update nguyenlieu set tennl ='"+tenNL+"', donvitinh='"+donvitinh+"', hansudung="+hanSD+" where manl ='"+maNL+"'";
        try {
            t = dal.getStatement().executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

}
