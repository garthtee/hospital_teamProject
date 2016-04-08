package core;

import database.DBConnection;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Scheduler {
    private ArrayList<Ward> wards;
    private ArrayList<Shift> shifts;
    private ArrayList<Employee> employees;
    private ArrayList<Shift_Employee> shift_employees;

    public Scheduler() {
        this.wards = getWards();
        this.employees = getEmployees();
        this.shifts = getShifts();
        this.shift_employees = new ArrayList<Shift_Employee>();
    }

    public ArrayList<Employee> getEmployees() {
        DBConnection dbc=new DBConnection();
        employees=dbc.getEmployees();
        return this.employees;
    }

    public ArrayList<Shift> getShifts() {
        DBConnection dbc = new DBConnection();
        ArrayList<Shift> shifts = dbc.getShifts();
        return shifts;
    }

    public ArrayList<Ward> getWards() {
        DBConnection dbc=new DBConnection();
        ArrayList<Ward> wards=dbc.getWards();
        return wards;
    }

    public ArrayList<Shift_Employee> getShiftEmployees(){
        return this.shift_employees;
    }

    public Doctor getDoctor(){
        //code for selecting doctor from list
        return null;
    }

    public Nurse getNurse(){
        //code for selecting nurse from list
        return null;
    }

    public void shedule(){
        //declare date
        // Creating a calendar object and parsing the date from DB
        Calendar calendar = Calendar.getInstance();
        try { // try parsing the string to a Calendar object
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            calendar.setTime(dateFormat.parse("2015-01-01"));
        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        //for each ward

        //for each shift
        for (int j=0; j<shifts.size(); j++){
            Shift_Employee se1=new Shift_Employee(shifts.get(j).getShift_ID(), employees.get(0).getEmp_ID(), calendar);
            shift_employees.add(se1);
            Shift_Employee se2=new Shift_Employee(shifts.get(j).getShift_ID(), employees.get(1).getEmp_ID(), calendar);
            shift_employees.add(se2);
            Shift_Employee se3=new Shift_Employee(shifts.get(j).getShift_ID(), employees.get(2).getEmp_ID(), calendar);
            shift_employees.add(se3);

            if(j%2!=0) // if not an even shift, increment day
                calendar.add(Calendar.DAY_OF_WEEK, 1);
        }

        DBConnection dbc=new DBConnection();
        for(int k=0; k<shift_employees.size(); k++){
            dbc.createShiftEmployee(shift_employees.get(k));
        }
    }

}
