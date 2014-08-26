package db2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Naptar {

    public static void main(String[] args) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        System.out.println(df.format(cal.getTime()));
        String today = df.format(cal.getTime());
        System.out.println("today is: " + today);

        cal.add(Calendar.DATE, 1);
        System.out.println(df.format(cal.getTime()));
        String tomorrow = df.format(cal.getTime());
        System.out.println("tomorrow is: " + tomorrow);
    }
}
