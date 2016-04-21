package core;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Garth Toland on 10/04/2016.
 * Description:
 */
public class DateValidator implements Serializable {

    private int day;
    private int month;
    private int year;
    static Calendar calendar = Calendar.getInstance();
    private static int thisYear = calendar.get(Calendar.YEAR); // Set current year here
    private static int thisMonth = calendar.get(Calendar.MONTH); // Set current month here
    private static int thisDay = calendar.get(Calendar.DAY_OF_MONTH); // Set current day here

    public DateValidator() {
    }

    public DateValidator(int year, int month, int day) throws IllegalArgumentException {

        if ((day <= 0 || day > 31) || (month <= 0 || month > 12) || (year <= 0))
            throw new IllegalArgumentException("Incorrect date.");

        // No exception thrown
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String toString() {
        return this.year + "-" + this.month + "-" + this.day;
    }

    public boolean equals(DateValidator dateIn) {
        if (day == dateIn.getDay() && month == dateIn.getMonth() && year == dateIn.getYear())
            return true;
        else
            return false;
    }

    public int getDay() {
        return day;
    }

    public DateValidator setDay(String dayAsString) throws IllegalArgumentException {
        char dayAsString1 = dayAsString.charAt(8);
        char dayAsString2 = dayAsString.charAt(9);
        String newDayAsString = "";
        newDayAsString += dayAsString1;
        newDayAsString += dayAsString2;
        int day;
        try{
            day = Integer.valueOf(newDayAsString);
        } catch (NumberFormatException e) {
            return null;
        }

        if ((day <= 0 || (day > 31)) || day > thisDay)
            throw new IllegalArgumentException("Day is an incorrect value.");

        // No exception thrown
        this.day = day;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public DateValidator setMonth(String monthAsString) throws IllegalArgumentException {
        char monthAsString1 = monthAsString.charAt(5);
        char monthAsString2 = monthAsString.charAt(6);
        String newMonthAsString = "";
        newMonthAsString += monthAsString1;
        newMonthAsString += monthAsString2;
        int month;
        try{
            month = Integer.valueOf(newMonthAsString);
        } catch (NumberFormatException e) {
            return null;
        }

        if ((month <= 0) || (month > 12) || month > thisMonth)
            throw new IllegalArgumentException("Month is an incorrect value.");

        // No exception thrown
        this.month = month;

        return this;
    }

    public int getYear() {
        return year;
    }

    public DateValidator setYear(String yearAsString) throws IllegalArgumentException {
        yearAsString = yearAsString.substring(0, Math.min(yearAsString.length(), 4));
        int year;
        try{
            year = Integer.valueOf(yearAsString);
        } catch (NumberFormatException e) {
            return null;
        }

        if ((year <= 1900 || year > thisYear))
            throw new IllegalArgumentException("Year is an incorrect value.");

        // No exception thrown
        this.year = year;

        return this;
    }
}