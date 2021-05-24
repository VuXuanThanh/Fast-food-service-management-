/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Vu Xuan Thanh
 */
public class Main {
    public static void main(String[] args) {
        String test = "30/4/1975";
        String format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(test);
            System.out.println(date);
//            if (!sdf.format(date).equals(test)) {Fri May 01 00:00:00 ICT 1998
//                throw new ParseException(test + " is not a valid format for " + format, 0);
//            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
}
