/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author nguye
 */
public class Luong {
     private String maLuong;
    private int Thang;
    private String maNhanVien;
    private int soGioLam;
    private double thuong;
    private double luong;
    public Luong()
    {
        
    }
    
    public Luong(String maLuong, int Thang, String maNhanVien, int soGioLam)
    {
        this.maLuong=maLuong;
        this.Thang=Thang;
        this.maNhanVien=maNhanVien;
        this.soGioLam=soGioLam;
        luong=tinhLuong();
    }

    public Luong(String maLuong, int Thang, String maNhanVien, int soGioLam, double luong) {
        this.maLuong=maLuong;
        this.Thang = Thang;
        this.maNhanVien = maNhanVien;
        this.soGioLam = soGioLam;
        this.luong = luong;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public String getMaLuong() {
        return maLuong;
    }

    public void setMaLuong(String maLuong) {
        this.maLuong = maLuong;
    }
    
    public double tinhLuong()
    {
        double luong;
        double luongMotGio = 150000;
        if (soGioLam >= 30) thuong = 2000000;
        else if (soGioLam < 30 && soGioLam >= 20) thuong = 1500000;
        else if (soGioLam < 20) thuong = 200000;
        
        luong = soGioLam*luongMotGio+thuong;
        return luong;
    }

    public int getThang() {
        return Thang;
    }

    public void setThang(int Thang) {
        this.Thang = Thang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getSoGioLam() {
        return soGioLam;
    }

    public void setSoGioLam(int soGioLam) {
        this.soGioLam = soGioLam;
    }
    
}
