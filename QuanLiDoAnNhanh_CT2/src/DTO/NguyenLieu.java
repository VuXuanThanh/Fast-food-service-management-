package DTO;

import java.util.Objects;

public class NguyenLieu {
    private String manl, tennl, donvitinh;
    private int hansd;

    public NguyenLieu() {
    }

    public NguyenLieu(String manl, String tennl, String donvitinh, int hansd) {
        this.manl = manl;
        this.tennl = tennl;
        this.donvitinh = donvitinh;
        this.hansd = hansd;
    }

    public NguyenLieu(String manl) {
        this.manl = manl;
    }

    public String getManl() {
        return manl;
    }

    public String getTennl() {
        return tennl;
    }

    public String getDonvitinh() {
        return donvitinh;
    }

    public int getHansd() {
        return hansd;
    }

    public void setManl(String manl) {
        this.manl = manl;
    }

    public void setTennl(String tennl) {
        this.tennl = tennl;
    }

    public void setDonvitinh(String donvitinh) {
        this.donvitinh = donvitinh;
    }

    public void setHansd(int hansd) {
        this.hansd = hansd;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.manl);
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
        final NguyenLieu other = (NguyenLieu) obj;
        if (!Objects.equals(this.manl, other.manl)) {
            return false;
        }
        return true;
    }
    
}
