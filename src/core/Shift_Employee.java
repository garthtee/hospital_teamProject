package core;

public class Shift_Employee {
    private int employee_ID;
    private int shift_employee_ID;
    private int shift_ID;
    private String date;

    public Shift_Employee(){
    }

    public Shift_Employee(int shift_ID, int employee_ID, String date){
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

    public String getDate() {
        return date;
    }

    public void setEmployee_ID(int employee_ID) {
        this.employee_ID = employee_ID;
    }

    public void setShift_ID(int shift_ID) {
        this.shift_ID = shift_ID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getShift_employee_ID() {
        return shift_employee_ID;
    }

    public void setShift_employee_ID(int shift_employee_ID) {
        this.shift_employee_ID = shift_employee_ID;
    }

    @Override
    public String toString() {

        return "Shift_Employee{" +
                "employee_ID=" + employee_ID +
                ", shift_ID=" + shift_ID +
                ", date=" + date +
                '}';
    }
}
