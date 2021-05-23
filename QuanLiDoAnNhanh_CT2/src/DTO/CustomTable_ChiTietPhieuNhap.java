package DTO;


import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
//import PhongThi_ThiSinh.ThiSinh;
/**
 *Data model đảm nhận nhiệm vụ cung cấp dữ liệu hiển thị cho JTable. 
 * Sử dụng data model giúp ứng dụng “MVC” hơn bằng việc tách riêng phần 
 * data và phần UI, tạo ra sự custom tốt hơn cho những bài toán phức tạp.
 Về cơ bản, một data model được cài đặt 9 phương thức do interface 
 * TableModel 
 * định nghĩa. Các phương thức đó được liệt kê trong hình dưới đây:
 getRowCout():int
 * getColumnCount():int
 * getValueAt(row, column): object
 * getColumnName(column:int):String
 * getColumnClass(column:int):class
 * isCellEditable(row:int, column:int):boolean
 * addTableModelListener(listener:TableModelListener):void
 * removeTableeModelListener(listener:tablleModelListener):void
 
 */
public class CustomTable_ChiTietPhieuNhap extends AbstractTableModel
{
    //Khai báo xâu chứa tiêu đề của bảng.
    private String name[]={"Số phiếu","Mã nguyên liệu","Số lượng nhập","Đơn giá nhập",
   };
    //Khai báo lớp Chứa kiểu dữ liệu của từng trường tương ứng.
    private Class classes[]={String.class,String.class,Integer.class,Double.class,
    };
    //Tạo một đối tượng arrayList có tên listThiSinh.
    ArrayList<ChiTietPhieuNhap> dsThiSinh=new ArrayList<>();

    //phương thức khởi tạo cho class có tham số truyền vào.
    public CustomTable_ChiTietPhieuNhap(ArrayList<ChiTietPhieuNhap> listPTB2)
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
            case 0: return dsThiSinh.get(rowIndex).getSoPhieu();
            //Cột b
            case 1: return dsThiSinh.get(rowIndex).getMaNL();
            //cột c
            case 2: return dsThiSinh.get(rowIndex).getsLNhap();
            //cột kq
            case 3: return dsThiSinh.get(rowIndex).getdGNhap();
            
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
