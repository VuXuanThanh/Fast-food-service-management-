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
public class CustomTableQLLUONG extends AbstractTableModel{
     private String name[] = {"Mã lương", "Tháng", "Mã nhân viên", "Số giờ làm", "Lương"};
    private Class classes[] = {String.class, Integer.class, String.class, Integer.class, Double.class};
    
    ArrayList<Luong> listL = new ArrayList<Luong>();
    
    public CustomTableQLLUONG()
    {
        
    }
    
    public CustomTableQLLUONG(ArrayList<Luong> dsLuong)
    {
        this.listL=dsLuong;
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
        return listL.size();
    }

    @Override
    public int getColumnCount() {
        return name.length;
    }

    
    public Object getValueAt(int rowIndex, int columnIndex) {
       switch(columnIndex)
       {
            case 0: return listL.get(rowIndex).getMaLuong();
            case 1: return listL.get(rowIndex).getThang();
            case 2: return listL.get(rowIndex).getMaNhanVien();
            case 3: return listL.get(rowIndex).getSoGioLam();
            case 4: return listL.get(rowIndex).getLuong();
            
            default: return null;
       }
        
    }
}
