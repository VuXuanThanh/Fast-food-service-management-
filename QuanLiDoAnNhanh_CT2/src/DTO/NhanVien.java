/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author nguye
 */
public class NhanVien extends Nguoi{
    private String maNhanVien;
    private String gioiTinh;
    private String soCMND;
    private String ngaySinh;
    private String tenTK;
    
    public NhanVien()
    {
               
    }

    public NhanVien(String maNhanVien, String hoTen, String gioiTinh, String soCMND, String ngaySinh, String diaChi, String tenTK, String soDT) {
        super(hoTen, soDT, diaChi);
        this.maNhanVien = maNhanVien;
        this.gioiTinh = gioiTinh;
        this.soCMND = soCMND;
        this.ngaySinh = ngaySinh;   
        this.tenTK= tenTK;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.maNhanVien);
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
        final NhanVien other = (NhanVien) obj;
        if (!Objects.equals(this.maNhanVien, other.maNhanVien)) {
            return false;
        }
        return true;
    }


    
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
  
    @Override
    public String toString() {
        return "Nh??n vi??n: " + "T??n = " + hoTen + ", Gi???i t??nh = " + gioiTinh + ", S??? CMND = " + soCMND + ", Ng??y Sinh = " + ngaySinh + ", S??? ??T =" +soDT+", ?????a ch??? ="+diaChi+"}";
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public NhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    
 


    

}
