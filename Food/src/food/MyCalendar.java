
package food;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MyCalendar {

    public static void main(String[] args) {

        int month =Calendar.getInstance().get(Calendar.MONTH);
        int year =Calendar.getInstance().get(Calendar.YEAR);

        Calendar cal = new GregorianCalendar();

		cal.clear();

		System.out.println("\n"	+ cal.getDisplayName(Calendar.MONTH, Calendar.LONG,Locale.US) + " " + cal.get(Calendar.YEAR));

		int firstWeekdayOfMonth = cal.get(Calendar.DAY_OF_WEEK);
                System.out.println(firstWeekdayOfMonth);

		int numberOfMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                System.out.println(numberOfMonthDays);

                
                int day = cal.DAY_OF_MONTH;
                System.out.println(day);
                
                
		// print anonymous calendar month based on the weekday of the first
		// day of the month and the number of days in month:
		printCalendar(numberOfMonthDays, firstWeekdayOfMonth);
	}

	private static void printCalendar(int numberOfMonthDays, int firstWeekdayOfMonth) {

		// reset index of weekday
		int weekdayIndex = 0;

		// print calendar weekday header
		System.out.println("Su  Mo  Tu  We  Th  Fr  Sa");

		// leave/skip weekdays before the first day of month
		for (int day = 1; day < firstWeekdayOfMonth; day++) {
			System.out.print("    ");
			weekdayIndex++;
		}

		// print the days of month in tabular format.
		for (int day = 1; day <= numberOfMonthDays; day++) {
			// print day
			System.out.printf("%1$2d", day);

			// next weekday
			weekdayIndex++;
			// if it is the last weekday
			if (weekdayIndex == 7) {
				// reset it
				weekdayIndex = 0;
				// and go to next line
				System.out.println();
			} else { // otherwise
				// print space
				System.out.print("  ");
			}
		}

		// print a final new-line.
		System.out.println();
	}
}