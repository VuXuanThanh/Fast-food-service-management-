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
 * @author Vu Xuan Thanh
 */
public class BLL {
    DAL dal = new DAL();
    public ArrayList<TaiKhoan> showHangHoa(){
        ArrayList<TaiKhoan> ds = new ArrayList<>();
        String sql ="select * from TAIKHOAN";
        ds = dal.getData(sql);
        System.out.println("Vũ Xuân thành");
        return ds;
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
