package core;

import java.util.Calendar;

public class Shift_Employee {
    private int employee_ID;
    private int shift_ID;
    private Calendar date;

    public Shift_Employee(int shift, int employee, Calendar date){
        this.employee_ID = shift;
        this.shift_ID = employee;
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
        return "Shift_Employee{" +
                "employee_ID=" + employee_ID +
                ", shift_ID=" + shift_ID +
                '}';
    }
}
