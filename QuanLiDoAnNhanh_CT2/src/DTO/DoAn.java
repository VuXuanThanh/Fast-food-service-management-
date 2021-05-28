/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import BLL.BLL;
import java.util.Objects;

/**
 *
 * @author Vu Xuan Thanh
 */
public class DoAn {
    private int maMon, maDM;
    private String tenMon, anh, donViTinh;
    private double gia;

    public DoAn() {
    }

    public DoAn(String tenMon) {
        this.tenMon = tenMon;
    }

    public DoAn(int maMon, String tenMon, String anh, String donViTinh, int maDM, double gia) {
        this.maMon = maMon;
        this.maDM = maDM;
        this.tenMon = tenMon;
        this.anh = anh;
        this.donViTinh = donViTinh;
        this.gia = gia;
    }

    
    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }

    public int getMaDM() {
        return maDM;
    }

    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getTenDM(){
        BLL bll = new BLL();
        return bll.getTenDM(maDM);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.tenMon);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DoAn other = (DoAn) obj;
        if (!Objects.equals(this.tenMon, other.tenMon)) {
            return false;
        }
        return true;
    }
}
