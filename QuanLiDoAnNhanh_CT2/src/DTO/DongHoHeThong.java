/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author phamt
 */
public class DongHoHeThong extends Thread{
    private JLabel lblGio,lblNgay;
    private int day,month,year;

    public DongHoHeThong(JLabel lblGio, JLabel lblNgay) {
        this.lblGio = lblGio;
        this.lblNgay = lblNgay;
    }
    
    public DongHoHeThong(String chuoi) {
        String[] tam = chuoi.split("\\/|-");
        this.day = Integer.parseInt(tam[0]);
        this.month = Integer.parseInt(tam[1]);
        this.year = Integer.parseInt(tam[2]);
        if(tam[0].length() == 4){
            this.day = Integer.parseInt(tam[2]);
            this.year = Integer.parseInt(tam[0]);
        }
    }
    
    public String getNgayVaoCSDL(){
        DecimalFormat dF = new DecimalFormat("00");
        return year+"-"+dF.format(month)+"-"+dF.format(day);
    }
    
    public String getNgayVaoHoaDon(){
        DecimalFormat dF = new DecimalFormat("00");
        return dF.format(day)+"/"+dF.format(month)+"/"+year;
    }
    
    @Override
    public void run() {
        SimpleDateFormat sdfGio = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdfNgay = new SimpleDateFormat("dd/MM/yyyy");
        
        Date ngayHienTai = new Date();
        String ngay = sdfNgay.format(ngayHienTai);
        lblNgay.setText(ngay);
        while (true) {            
            Date gioHienTai = new Date();
            String st = sdfGio.format(gioHienTai);
            
            lblGio.setText(st);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
