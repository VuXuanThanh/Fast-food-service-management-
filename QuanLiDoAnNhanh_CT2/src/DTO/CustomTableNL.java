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
public class CustomTableNL extends AbstractTableModel
{
    //Khai báo xâu chứa tiêu đề của bảng.
    private String name[]={"Mã nguyên liệu","Tên nguyên liệu","Đơn vị tính","Hạn sử dụng (Ngày)"};
    //Khai báo lớp Chứa kiểu dữ liệu của từng trường tương ứng.
    private Class classes[]={String.class,String.class,String.class, Integer.class};
    //Tạo một đối tượng arrayList có tên listThiSinh.
    ArrayList<NguyenLieu> nl=new ArrayList<>();

    //phương thức khởi tạo cho class có tham số truyền vào.
    public CustomTableNL(ArrayList<NguyenLieu> listPTB2)
   {
       this.nl=listPTB2;
   }
    //lấy số phần tử của listThiSinh
    @Override
    public int getRowCount()
    {
        return nl.size();
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
            case 0: return nl.get(rowIndex).getMaNL();
            //Cột b
            case 1: return nl.get(rowIndex).getTenNL();
            //cột c
            case 2: return nl.get(rowIndex).getDonViTinh();
            
             case 3: return nl.get(rowIndex).getHanSD();
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
