package core;

import database.DBConnection;
import database.DBConnection_Scheduler;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Scheduler {
    private ArrayList<Ward> wards;
    private ArrayList<Shift> shifts;
    private ArrayList<Employee> employees;
    private ArrayList<Shift_Employee> shift_employees;
    ArrayList<Shift_Employee> tempList;

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

    public void schedule() {
        //declare date
        // Creating a calendar object and parsing the date from DB

        Calendar calendar = Calendar.getInstance();
        tempList = new ArrayList<>();
        //for each ward
        Shift_Employee s;
        //for each shift
        for (int j=0; j<shifts.size(); j++){


//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            String dateAsString = format.format(calendar.getTime());
//            System.out.println(dateAsString);
            if(j%2!=0) { // if not an even shift, increment day
                calendar.add(Calendar.DATE, 1);
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateAsString = format.format(calendar.getTime());

            s = new Shift_Employee(shifts.get(j).getShift_ID(), employees.get(0).getEmp_ID(), dateAsString);
            tempList.add(s);

        }

        DBConnection_Scheduler dbcs=new DBConnection_Scheduler();
        for(Shift_Employee s_e : tempList){
            dbcs.createShiftEmployee(s_e);
        }
    }
}
