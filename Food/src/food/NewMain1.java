package food;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;

public class NewMain1 { 
    public static int weeknr;

    public static void main(String[] args) { 
        
        int month =java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
        int year =java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

        java.util.Calendar cal = new GregorianCalendar();

	System.out.println("\n"	+ cal.getDisplayName(java.util.Calendar.MONTH, java.util.Calendar.LONG,Locale.US) + " " + cal.get(java.util.Calendar.YEAR));

	int firstdayofMonth = cal.get(java.util.Calendar.DAY_OF_WEEK);
	int numberOfMonthDays = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

        Vector <Object> week1 = new Vector<>();
        Vector <Object> week2 = new Vector<>();
        Vector <Object> week3 = new Vector<>();
        Vector <Object> week4 = new Vector<>();
        Vector <Object> week5 = new Vector<>();
        Vector <Object> week6 = new Vector<>();

        for (int i=1;i<=(firstdayofMonth-1);i++){
        week1.add(0);        
        }
        for (int i=1;week1.size()<=6;i++){
        week1.add(i);        
        }
        
        int fdnw = (int) week1.lastElement()+1;
        for (int i=fdnw;week2.size()<=6;i++){
        week2.add(i);        
        }

        fdnw = (int) week2.lastElement()+1;
        for (int i=fdnw;week3.size()<=6;i++){
        week3.add(i);        
        }
        
        fdnw = (int) week3.lastElement()+1;
        for (int i=fdnw;week4.size()<=6&&i<=numberOfMonthDays;i++){
        week4.add(i);        
        }
        
        fdnw = (int) week4.lastElement()+1;
        for (int i=fdnw;week5.size()<=6&&i<=numberOfMonthDays;i++){
        week5.add(i);        
        }
        
        fdnw = (int) week5.lastElement()+1;
        for (int i=fdnw;week6.size()<=6&&i<=numberOfMonthDays;i++){
        week6.add(i);        
        }
        
        
        
         for (Object e:week1){
            System.out.print(e);
         }
         System.out.println();
         
        for (Object f:week2){
            System.out.print(f);
        }
        System.out.println();
       
        for (Object f:week3){
            System.out.print(f);
        }
        System.out.println();
        
        for (Object f:week4){
            System.out.print(f);
        }
        System.out.println();
        
        for (Object f:week5){
            System.out.print(f);
        }
        System.out.println();
        
        for (Object f:week6){
            System.out.print(f);
        }
        System.out.println();
        

    }

}
