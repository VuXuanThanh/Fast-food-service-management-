/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.BLL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vu Xuan Thanh
 */

public class RandomString {

    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }

        return sb.toString();
    }
    public static void main(String[] args) throws ParseException, Exception {
         // Get the size n
        int n = 18;
        BLL bll = new BLL();
        // Get and display the alphanumeric string
          int maNCC = 1;
          String soPhieu = "4sbbẻ434343";
          String ngayNhap = "2021-18-05";
        //    String maNL = bll.showMaNLTheoTen(lstNguyenLieu.getSelectedValue().toString());
         //   System.out.println(maNCC+maNL);
         
            int t = bll.insertPhieuNhap(soPhieu, ngayNhap, maNCC);
    }
}
