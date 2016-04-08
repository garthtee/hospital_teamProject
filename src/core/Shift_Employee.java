package core;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Shift_Employee {
    private int employee_ID;
    private int shift_ID;
    private Calendar date;

    public Shift_Employee(int shift_ID, int employee_ID, Calendar date){
        this.employee_ID = employee_ID;
        this.shift_ID = shift_ID;
        this.date=date;
    }

    public int getShift_ID() {
        return shift_ID;
    }
    public int getEmployee_ID() {
        return employee_ID;
    }

    public Calendar getDate() {
        return date;
    }

    @Override
    public String toString() {

        /*
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateAsString = format.format(date.getTime());
        */
        return "Shift_Employee{" +
                "employee_ID=" + employee_ID +
                ", shift_ID=" + shift_ID +
                ", date=" + date +
                '}';
    }
}
