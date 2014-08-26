/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itim;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author gabor
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String today = df.format(cal.getTime());
        String home = System.getProperty("user.home");
        System.out.println(home + "/Report_" + today + ".csv");
    } 
}
