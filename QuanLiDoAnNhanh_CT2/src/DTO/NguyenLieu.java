
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
public class NguyenLieu {
    private String maNL, tenNL;
    private int hanSD;
    private String donViTinh;

    public NguyenLieu() {
    }

    public NguyenLieu(String tenNL) {
        this.tenNL = tenNL;
    }

    public NguyenLieu(String maNL, String tenNL, String donViTinh,  int hanSD) {
        this.maNL = maNL;
        this.tenNL = tenNL;
        this.hanSD = hanSD;
        this.donViTinh = donViTinh;
    }

    public String getMaNL() {
        return maNL;
    }

    public void setMaNL(String maNL) {
        this.maNL = maNL;
    }

    public String getTenNL() {
        return tenNL;
    }

    public void setTenNL(String tenNL) {
        this.tenNL = tenNL;
    }

    public int getHanSD() {
        return hanSD;
    }

    public void setHanSD(int hanSD) {
        this.hanSD = hanSD;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }
    
    
    
}


