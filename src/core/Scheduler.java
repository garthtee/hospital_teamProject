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
        calendar.set(2015, 1, 1);
        tempList = new ArrayList<>();

        int offShiftTracker =0;
        int employeeTracker=0;
        int lastEmployee=0;
        //for each ward
        Shift_Employee s;
        //for each shift
        for (int j=0; j<shifts.size(); j++) {
            //checks if employee has reached max hours for week
            if (employees.get(employeeTracker).getHoursWorked() == employees.get(employeeTracker).getContractHours()){
                employeeTracker++;
            }
            //checks if employee worked last shift
            if(employees.get(employeeTracker).getLastShift().equals("true")){
                employeeTracker++;
            }

            //resets lastshift for previous employee
            employees.get(lastEmployee).setLastShift("false");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateAsString = format.format(calendar.getTime());

            s = new Shift_Employee(shifts.get(j).getShift_ID(), employees.get(employeeTracker).getEmp_ID(), dateAsString);
            shift_employees.add(s);
            offShiftTracker++;
            if (offShiftTracker == 2) { // if not an even shift, increment day
                calendar.add(Calendar.DATE, 1);
                offShiftTracker = 0;
            }

            //increments hours worked for employee
            employees.get(employeeTracker).setHoursWorked(employees.get(employeeTracker).getHoursWorked() + 12);
            //sets last shift
            employees.get(employeeTracker).setLastShift("true");

            lastEmployee=employeeTracker;

            //resets employee tracker
            if(employeeTracker==employees.size()-1){
                employeeTracker=0;
            }
        }

        DBConnection_Scheduler dbcs=new DBConnection_Scheduler();
        for(Shift_Employee s_e : shift_employees){
            dbcs.createShiftEmployee(s_e);
        }
    }
}
