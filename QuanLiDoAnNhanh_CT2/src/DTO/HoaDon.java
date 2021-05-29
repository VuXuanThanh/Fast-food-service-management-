/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import BLL.BLL;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Vu Xuan Thanh
 */
public class HoaDon {
    private String maHD;
    private String maNV;
    private Date ngayXuat;
    private Time thoiGian;
    private String soDT;
    private double tongTien;
    
    public HoaDon() {
    }

    public HoaDon(String maHD, String maNV, Date ngayXuat) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.ngayXuat = ngayXuat;
    }

    public HoaDon(String maHD, String maNV, Date ngayXuat, String soDT) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.ngayXuat = ngayXuat;
        this.soDT = soDT;
    }

    public HoaDon(String maHD, Date ngayXuat, double tongTien) {
        this.maHD = maHD;
        this.ngayXuat = ngayXuat;
        this.tongTien = tongTien;
    }
    

    public HoaDon(String maHD, String maNV, Date ngayXuat, Time thoiGian, String soDT) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.ngayXuat = ngayXuat;
        this.thoiGian = thoiGian;
        BLL bll = new BLL();
        this.soDT= soDT;
    }
    
    public void setMaHD(int so) {
        DecimalFormat dingDangHD = new DecimalFormat("0000");
        SimpleDateFormat sdfNgay = new SimpleDateFormat("ddMMyy");
        Date d = new Date();
        
        this.maHD = "HD"+sdfNgay.format(d)+"-"+dingDangHD.format(so);
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getNgayXuat() {
        SimpleDateFormat sdfNgay = new SimpleDateFormat("yyyy-MM-dd");
        return sdfNgay.format(ngayXuat);
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public String getThoiGian() {
        SimpleDateFormat sdfGio = new SimpleDateFormat("HH:mm:ss");
        return sdfGio.format(thoiGian);
    }

    public void setThoiGian(Time thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    
    public double getTongTien() {
        tongTien =0;
        BLL bll = new BLL();
        ArrayList<ChiTietHoaDon> listCTHD = new ArrayList<>();
        listCTHD = bll.showChiTietHoaDon(maHD);
        listCTHD.forEach((t) -> {
            tongTien+= (t.getSoLuong()*t.getDoAn().getGia());
        });
        if(!soDT.equalsIgnoreCase("Khách lẻ")){
            tongTien+= 5000;
        }
        return tongTien;
    }
     public Date getNgayXuat1() {
        return ngayXuat;
    }
}
