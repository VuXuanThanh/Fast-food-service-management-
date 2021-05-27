/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author Vu Xuan Thanh
 */
public class ChiTietPhieuNhap {
    private String soPhieu, maNL;
    private int sLNhap;
    private double dGNhap;

    public ChiTietPhieuNhap() {
    }

    public ChiTietPhieuNhap(String soPhieu, String maNL) {
        this.soPhieu = soPhieu;
        this.maNL = maNL;
    }
    
    public ChiTietPhieuNhap(String soPhieu, String maNL, int sLNhap, double dGNhap) {
        this.soPhieu = soPhieu;
        this.maNL = maNL;
        this.sLNhap = sLNhap;
        this.dGNhap = dGNhap;
    }

    public String getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public String getMaNL() {
        return maNL;
    }

    public void setMaNL(String maNL) {
        this.maNL = maNL;
    }

    public int getsLNhap() {
        return sLNhap;
    }

    public void setsLNhap(int sLNhap) {
        this.sLNhap = sLNhap;
    }

    public double getdGNhap() {
        return dGNhap;
    }

    public void setdGNhap(double dGNhap) {
        this.dGNhap = dGNhap;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.soPhieu);
        hash = 61 * hash + Objects.hashCode(this.maNL);
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
        final ChiTietPhieuNhap other = (ChiTietPhieuNhap) obj;
        if (!Objects.equals(this.soPhieu, other.soPhieu)) {
            return false;
        }
        if (!Objects.equals(this.maNL, other.maNL)) {
            return false;
        }
        return true;
    }

   
    
    
    
}
