
package DTO;

import java.util.Objects;

public class NhaCungCap {
    private String tenNCC, diaChi, soDT;
    private int maNCC;

    public NhaCungCap(int maNCC,String tenNCC, String diaChi, String soDT) {
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.maNCC = maNCC;
    }

    public NhaCungCap() {
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public int getMaNCC() {
        return maNCC;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.tenNCC);
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
        final NhaCungCap other = (NhaCungCap) obj;
        if (!Objects.equals(this.tenNCC, other.tenNCC)) {
            return false;
        }
        return true;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }
}
