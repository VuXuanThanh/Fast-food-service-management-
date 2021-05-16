/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

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

    public ChiTietHoaDon(String maHD, int maMon, int soLuong, double gia) {
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

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
    public double thanhTien(){
        return gia*soLuong;
    }
    
}
