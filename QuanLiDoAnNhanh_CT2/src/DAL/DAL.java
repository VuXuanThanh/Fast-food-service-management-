package DAL;


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
 */
public class DAL {

    Connection con = null;
    Statement sta = null;
    ResultSet res = null;

    String url = "jdbc:derby://localhost:1527/QuanLiDoAn";
    String user = "THANH";
    String pass = "123";

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
   

}
