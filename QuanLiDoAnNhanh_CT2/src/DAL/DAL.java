package DAL;


import DTO.ChiTietPhieuNhap;
import DTO.Luong;
import DTO.NhanVien;
import DTO.TaiKhoan;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vu Xuan Thanh
 * add for Phạm Trung Thế
 */
public class DAL {

    // push code từ branch VuXuanThanh
    Connection con = null;
    Statement sta = null;
    ResultSet res = null;

    String url = "jdbc:derby://localhost:1527/QLDoAN";
    String user = "thanh";
    String pass = "123456";

    public DAL() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            System.out.println("Không thể kết nối đến database " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("lỗi kết nối " + ex.getMessage());
        }
    }

    public Statement getStatement() throws Exception {
        if (this.sta == null || this.sta.isClosed()) {
            //khoi tao statement moi
            this.sta = this.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
        }
        return this.sta;
    }

    public ResultSet executeQuery(String qr) throws Exception {
        try {
            //thuc thi cau lenh
            this.res = getStatement().executeQuery(qr);
        } catch (Exception e) {
            throw new Exception("Cau lenh Query khong duoc thuc thi");
        }
        return this.res;
        //van mo ket noi de lay thong tin
    }

    public int executeUpdate(String qr) throws Exception {
        int ketqua = 0;
        try {
            // thuc thi cau lenh
            ketqua = getStatement().executeUpdate(qr);
        } catch (Exception e) {
            throw new Exception("Loi queryUpdate khong duoc thuc hien");
        } finally {
            this.con.close();
        }
        return ketqua;
    }
    public ResultSet getResultSet(String sql){
        ResultSet kp = null;
        try{
        Statement st = con.createStatement();
        kp = st.executeQuery(sql);
        
        }
        catch(Exception ex){
            System.err.println("Lỗi tại getresultset: "+ex.getMessage());
        }
        return kp;
    }
//    public ResultSet GetData(String jtable) throws SQLException
//    {
//        ResultSet kq=null;
//        Statement st=con.createStatement();
//        kq=st.executeQuery("select * from HCN");
//        return kq;
//    }
    public void doSQL(String sql)  {
        try {
            sta = con.createStatement();
            sta.execute(sql);
        } catch (SQLException ex) {
            System.out.println(" khong thuc hien dc cau lẹnh sql\n"+sql);
        }
        
    }
     public ArrayList getDataCTPN(String sql)  {
        ArrayList<ChiTietPhieuNhap> ds = new ArrayList<>();
        try {
                sta = con.createStatement();
                res = sta.executeQuery(sql);
                while (res.next()) {
                    ChiTietPhieuNhap sv = new ChiTietPhieuNhap(res.getString(1), 
                            res.getString(2), res.getInt(3), res.getDouble(4));
                    ds.add(sv);                        
                }                                
        } catch (Exception ex) {           
            System.out.println("loi getData "+ ex.toString());
            return null;
        }
        return ds;
    }  
    
     //Nguyen Tuan Thanh (Error)
     public ArrayList<Luong> getDataLuong(String sql) {
        ArrayList<Luong> ds = new ArrayList<>();
        try{
            sta = getStatement();
            res = executeQuery(sql);

            while (res.next()) {
                Luong l = new Luong(
                       res.getString(1),res.getInt(2), res.getString(3), res.getInt(4), res.getDouble(5));
                ds.add(l);
            }

        } catch (Exception ex) {
            System.out.println("Lỗi getData " + ex.toString());
            return null;
        }
        return ds;
        }
    public ArrayList<NhanVien> getDataNhanVien(String sql) {
        ArrayList<NhanVien> ds = new ArrayList<>();
        try{
            sta = getStatement();
            res = executeQuery(sql);

            while (res.next()) {
                NhanVien nv = new NhanVien( res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5).toString(), res.getString(6),res.getString(7), res.getString(8));
                ds.add(nv);
            }
        } catch (Exception ex) {
            System.out.println("Lỗi getData " + ex.toString());
            return null;
        }
        return ds;
        }
    ///
}
