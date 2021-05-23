/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Vu Xuan Thanh
 */
public class HoaDon {
    private String maHD;
    private int maNV;
    private Date ngayXuat;
    private KhachHang khachHang;
    
    
    
    public static void main(String[] args) {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
//        LocalDateTime now = LocalDateTime.now();  
//       System.out.println(dtf.format(now).toString());  
//            double randomDouble = Math.random();
//            randomDouble = randomDouble * 100000 + 1;
//            int randomInt = (int) randomDouble;
//            System.out.println("Random number is : " + randomInt);
    }

    public HoaDon() {
    }

    public HoaDon(String maHD, int maNV, Date ngayXuat) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.ngayXuat = ngayXuat;
    }

    public HoaDon(String maHD, int maNV, Date ngayXuat, KhachHang khachHang) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.ngayXuat = ngayXuat;
        this.khachHang = khachHang;
    }
    
    
}
