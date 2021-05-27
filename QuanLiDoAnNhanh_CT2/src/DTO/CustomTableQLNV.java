/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nguye
 */
public class CustomTableQLNV extends AbstractTableModel{
    private String name[] = {"Mã nhân viên", "Tên nhân viên", "Giới tính", "Số CMND", "Ngày sinh", "Địa chỉ", "Số ĐT"};
    private Class classes[] = {String.class, String.class, String.class, String.class, String.class, String.class, String.class};
    
    ArrayList<NhanVien> listNV = new ArrayList<NhanVien>();

    public CustomTableQLNV() 
    {
        
    }
    
    public CustomTableQLNV (ArrayList<NhanVien> dsNV)
    {
        this.listNV=dsNV;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
       return classes[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return name[column];
    }
    
    @Override
    public int getRowCount() {
       return listNV.size();
    }

    @Override
    public int getColumnCount() {
        return name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            case 0: return listNV.get(rowIndex).getMaNhanVien();
            case 1: return listNV.get(rowIndex).getHoTen();
            case 2: return listNV.get(rowIndex).getGioiTinh();
            case 3: return listNV.get(rowIndex).getSoCMND();
            case 4: return listNV.get(rowIndex).getNgaySinh();
            case 5: return listNV.get(rowIndex).getDiaChi();
            case 6: return listNV.get(rowIndex).getSoDT();

            default: return null;
        }
    }
}
