package DTO;




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
public class CustomTable extends AbstractTableModel
{
    //Khai báo xâu chứa tiêu đề của bảng.
    private String name[]={"Mã tài khoản","Tên tài khoản","Mật khẩu","Quyền"};
    //Khai báo lớp Chứa kiểu dữ liệu của từng trường tương ứng.
    private Class classes[]={Integer.class,String.class,String.class, Integer.class};
    //Tạo một đối tượng arrayList có tên listThiSinh.
    ArrayList<TaiKhoan> dsThiSinh=new ArrayList<>();
    ArrayList<Object> object = new ArrayList<>();
    //phương thức khởi tạo cho class có tham số truyền vào.
    public CustomTable(ArrayList<TaiKhoan> listPTB2)
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
            case 0: return dsThiSinh.get(rowIndex).getID();
            //Cột b
            case 1: return dsThiSinh.get(rowIndex).getTenTaiKhoan();
            //cột c
            case 2: return dsThiSinh.get(rowIndex).getMatKhau();
            
             case 3: return dsThiSinh.get(rowIndex).getQuyen();
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
