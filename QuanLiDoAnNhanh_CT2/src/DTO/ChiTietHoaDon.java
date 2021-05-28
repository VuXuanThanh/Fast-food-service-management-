/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import BLL.BLL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vu Xuan Thanh
 */
public class ChiTietHoaDon {
    private String maHD;
    private int maMon, soLuong;
    private double gia;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String maHD, int maMon, int soLuong,double gia) {
        this.maHD = maHD;
        this.maMon = maMon;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public DoAn getDoAn(){
        BLL bll = new BLL();
        ArrayList<DoAn> listDoAn = new ArrayList<>();
        String dK = "mamon = "+maMon+"";
        listDoAn = bll.showDoAnTheoDK(dK);
        return listDoAn.get(0);
    }
    
    public String getTenMon(){
        return getDoAn().getTenMon();
    }
    
    public String getDonViTinh(){
        return getDoAn().getDonViTinh();
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
    
    
    public double thanhTien(){
        double gia = getDoAn().getGia();
        return gia*soLuong;
    }
}
