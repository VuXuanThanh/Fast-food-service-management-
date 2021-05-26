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
public class TaiKhoan {
    private String tenTaiKhoan, matKhau;
    private int quyen;
    
    public TaiKhoan() {
    }

    public TaiKhoan(String tenTaiKhoan, String matKhau) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }

    public TaiKhoan(String tenTaiKhoan, String matKhau, int quyen) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.quyen = quyen;
    }
    
    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getQuyen() {
        return quyen;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.tenTaiKhoan);
        hash = 97 * hash + Objects.hashCode(this.matKhau);
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
        final TaiKhoan other = (TaiKhoan) obj;
        if (!Objects.equals(this.tenTaiKhoan, other.tenTaiKhoan)) {
            return false;
        }
        if (!Objects.equals(this.matKhau, other.matKhau)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" + "tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau + ", quyen=" + quyen + '}';
    }
    
    
    
    
}
