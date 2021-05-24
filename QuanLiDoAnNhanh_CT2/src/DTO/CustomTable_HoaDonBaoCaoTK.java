package DTO;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

public class CustomTable_HoaDonBaoCaoTK extends AbstractTableModel
{
    //Khai báo xâu chứa tiêu đề của bảng.
    private String name[]={"Mã hóa đơn","Ngày xuất","Tổng tiền"
   };
    //Khai báo lớp Chứa kiểu dữ liệu của từng trường tương ứng.
    private Class classes[]={String.class,Date.class,Double.class,
    };
    //Tạo một đối tượng arrayList có tên listThiSinh.
    ArrayList<HoaDon> dsThiSinh=new ArrayList<>();

    //phương thức khởi tạo cho class có tham số truyền vào.
    public CustomTable_HoaDonBaoCaoTK(ArrayList<HoaDon> listPTB2)
   {
       this.dsThiSinh=listPTB2;
   }
    //lấy số phần tử của listThiSinh
    @Override
    public int getRowCount()
    {
        return dsThiSinh.size();
    }
    //Lấy số lượng tiêu để của mảng.
    @Override
    public int getColumnCount()
    {
        return name.length;
    }
    //Đưa thông tin của phần tử trong arrayList lên jTable
    public Object getValueAt(int rowIndex,int columnIndex)
    {
        
        switch(columnIndex)
        {
            //Cột số a
            case 0: return dsThiSinh.get(rowIndex).getMaHD();
            //Cột b
            case 1: return dsThiSinh.get(rowIndex).getNgayXuat();
            //cột c
            case 2: return dsThiSinh.get(rowIndex).getTongTien();
            //cột kq      
            default :return null;
        }
    }
    @Override
    public Class getColumnClass(int columnIndex)
    {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int column)
    {
        return name[column];
    }
}
