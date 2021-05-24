/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Vu Xuan Thanh
 */
public class PhieuNhap {
    private String soPhieu;
    private Date ngayNhap;
    private int maNCC;
    private double tongTien;
    
    public PhieuNhap() {
    }

    public PhieuNhap(String soPhieu, Date ngayNhap, int maNCC) {
        this.soPhieu = soPhieu;
        this.ngayNhap = ngayNhap;
        this.maNCC = maNCC;
    }

    public PhieuNhap(String soPhieu, Date ngayNhap, double tongTien) {
        this.soPhieu = soPhieu;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
    }

    
    public String getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    
    
}
