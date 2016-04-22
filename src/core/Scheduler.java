package core;

import database.DBConnection_Scheduler;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Scheduler {
    private Ward ward;
    private ArrayList<Shift> shifts;
    private ArrayList<Employee> employees;
    private ArrayList<Shift_Employee> shift_employees = new ArrayList<Shift_Employee>();
    private ArrayList<Employee> tempList = new ArrayList<Employee>();
    private DBConnection_Scheduler dbConnection_scheduler = new DBConnection_Scheduler();


    public Scheduler(int wardIn) {
        this.ward = getWard(wardIn);
        this.employees = getEmployees();
        this.shifts = getShifts();
        this.shift_employees = getShiftEmployees();
    }

    public ArrayList<Employee> getEmployees() {
        employees = dbConnection_scheduler.getEmployees();
        return this.employees;
    }

    public ArrayList<Shift> getShifts() {
        shifts = dbConnection_scheduler.getShifts();
        return shifts;
    }

    public Ward getWard(int wardIn) {
        ward = dbConnection_scheduler.getWard(wardIn);
        return ward;
    }

    public ArrayList<Shift_Employee> getShiftEmployees() {
        return dbConnection_scheduler.getShift_Employees();
    }

    public Doctor getDoctor() {
        //code for selecting doctor from list
        return null;
    }

    public Nurse getNurse() {
        //code for selecting nurse from list
        return null;
    }

    public void filterEmployees(String employeeType) {
        tempList.clear();

        if (employeeType.equals("doctor")) {
            for (Employee emp : employees) {
                if (emp.getEmployee_type().equals(employeeType) && emp.getWard_ID() == ward.getWard_ID())
                    tempList.add(emp);
            }
        } else if (employeeType.equals("nurse")) {
            for (Employee emp : employees) {
                if (emp.getEmployee_type().equals(employeeType) && emp.getWard_ID() == ward.getWard_ID())
                    tempList.add(emp);
            }
        }
    }

    public void scheduleDoctors(Calendar calendarIn) {
        //declare date
        // Creating a calendar object and parsing the date from DB
        shift_employees.clear();

        int offShiftTracker = 0;
        int employeeTracker = 0;
        int lastEmployee = 0;

        filterEmployees("doctor");
        //for each ward
        Shift_Employee s;
        //for each shift
        for (int j = 0; j < shifts.size(); j++) {
            //checks if employee has reached max hours for week
            if (tempList.get(employeeTracker).getHoursWorked() == tempList.get(employeeTracker).getContractHours()) {
                employeeTracker++;
            }
            //checks if employee worked last shift
            if (tempList.get(employeeTracker).getLastShift().equals("true")) {
                employeeTracker++;
            }

            //resets lastshift for previous employee
            tempList.get(lastEmployee).setLastShift("false");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateAsString = format.format(calendarIn.getTime());

            s = new Shift_Employee(shifts.get(j).getShift_ID(), tempList.get(employeeTracker).getEmp_ID(), dateAsString);
            shift_employees.add(s);
            offShiftTracker++;
            if (offShiftTracker == 2) { // if not an even shift, increment day
                calendarIn.add(Calendar.DATE, 1);
                offShiftTracker = 0;
            }

            //increments hours worked for employee
            tempList.get(employeeTracker).setHoursWorked(tempList.get(employeeTracker).getHoursWorked() + 12);
            //sets last shift
            tempList.get(employeeTracker).setLastShift("true");

            lastEmployee = employeeTracker;

            //resets employee tracker
            if (employeeTracker == tempList.size() - 1) {
                employeeTracker = 0;
            }
        }


    }

    private void addToDB() {
        DBConnection_Scheduler dbcs = new DBConnection_Scheduler();
        for (Shift_Employee s_e : shift_employees) {
            dbcs.createShiftEmployee(s_e);
        }
    }

    public void scheduleNurses(Calendar calendarIn) {
        //declare date
        // Creating a calendar object and parsing the date from DB

        calendarIn.add(Calendar.DATE, -7);

        int offShiftTracker = 0;
        int employeeTracker = 0;
        int lastEmployee = 0;

        filterEmployees("nurse");
        //for each ward
        Shift_Employee s;
        //for each shift
        for (int j = 0; j < shifts.size(); j++) {
            //checks if employee has reached max hours for week
            if (tempList.get(employeeTracker).getHoursWorked() == tempList.get(employeeTracker).getContractHours()) {
                employeeTracker++;
            }
            //checks if employee worked last shift
            if (tempList.get(employeeTracker).getLastShift().equals("true")) {
                employeeTracker++;
            }

            //resets lastshift for previous employee
            tempList.get(lastEmployee).setLastShift("false");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateAsString = format.format(calendarIn.getTime());

            s = new Shift_Employee(shifts.get(j).getShift_ID(), tempList.get(employeeTracker).getEmp_ID(), dateAsString);
            shift_employees.add(s);
            offShiftTracker++;
            if (offShiftTracker == 2) { // if not an even shift, increment day
                calendarIn.add(Calendar.DATE, 1);
                offShiftTracker = 0;
            }

            //increments hours worked for employee
            tempList.get(employeeTracker).setHoursWorked(tempList.get(employeeTracker).getHoursWorked() + 12);
            //sets last shift
            tempList.get(employeeTracker).setLastShift("true");

            lastEmployee = employeeTracker;

            //resets employee tracker
            if (employeeTracker == tempList.size() - 1) {
                employeeTracker = 0;
            }
        }

    }

    public void addScheduleToDB() {
        DBConnection_Scheduler dbcs = new DBConnection_Scheduler();
        for (Shift_Employee s_e : shift_employees) {
            dbcs.createShiftEmployee(s_e);
        }
    }

    public void schedule(Calendar calendarIn) {
        if(checkDate(calendarIn)) {

            filterEmployees("doctor");
            ArrayList<Employee> test1 = tempList;

            filterEmployees("nurse");
            ArrayList<Employee> test2 = tempList;

            if (test1.size() >= 4 && test2.size() >= 4) {
                filterEmployees("doctor");
                scheduleDoctors(calendarIn);
                filterEmployees("nurse");
                scheduleNurses(calendarIn);
                addScheduleToDB();
                String startDate = shift_employees.get(0).getDate();
                String endDate = shift_employees.get(shift_employees.size() - 1).getDate();
                dbConnection_scheduler.setLastDate(endDate, ward.getWard_ID());


                JOptionPane.showMessageDialog(null, "Ward successfully scheduled from: " + startDate + " to " + endDate, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient Staff to schedule ward.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "Date already scheduled", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }
    public boolean checkDate(Calendar calendarIn){
        String lastDate=dbConnection_scheduler.getLastDate(ward.getWard_ID());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendarLastDate = Calendar.getInstance();

        try {
            calendarLastDate.setTime(sdf.parse(lastDate));
        } catch (ParseException e) {

        }

        if(calendarIn.before(calendarLastDate))
            return false;
        else if (calendarIn.equals(calendarLastDate))
            return false;
        else
            return true;

    }

}
