package database;

import core.Employee;
import core.Shift;
import core.Shift_Employee;
import core.Ward;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by garth on 10/04/2016.
 */
public class DBConnection_Scheduler {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DBConnection_Scheduler() {
    }

    /* Remote AWS database connection */
    private void getDBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://hospital-team-project.cxcy14kqrkxb.eu-west-1.rds.amazonaws.com/hospital", "team", "teamHospital16");

            statement = connection.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeStatement() {
        try {
            if (statement != null)
                statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeResultSet() {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createShiftEmployee(Shift_Employee shiftEmployee) {
        getDBConnection();
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO shift_employee VALUES(?, ?,?,?);");

            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, shiftEmployee.getShift_ID()); // employee id 0 as it's auto incremented in DB
            preparedStatement.setInt(3, shiftEmployee.getEmployee_ID());
            preparedStatement.setString(4, shiftEmployee.getDate());



            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
    }

    public ArrayList<Employee> getEmployees() {

        getDBConnection();

        ArrayList<Employee> employeeList = new ArrayList<>();

        try {
            String query = "select * from employee;";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int emp_id = resultSet.getInt("emp_ID");
                String fName = resultSet.getString("fName");
                String sName = resultSet.getString("sName");
                String DOB = resultSet.getString("DOB");
                String contactNum = resultSet.getString("contactNum");
                String email = resultSet.getString("email");
                int numHolidays = resultSet.getInt("numHolidays");
                int contractHours = resultSet.getInt("contractHours");
                int salary = resultSet.getInt("salary");
                Double onHoliday = resultSet.getDouble("onHoliday");
                Double offSick = resultSet.getDouble("offSick");
                String lastShift=resultSet.getString("lastShift");
                int ward_ID = resultSet.getInt("ward_ID");

                String password = resultSet.getString("password");
                String privilege = resultSet.getString("privilege");
                String employee_type=resultSet.getString("employee_type");

                Employee employee = new Employee();
                employee.setEmp_ID(emp_id);
                employee.setfName(fName);
                employee.setsName(sName);

                // Creating a calendar object and parsing the date from DB
                Calendar calendar = Calendar.getInstance();
                try { // try parsing the string to a Calendar object
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    calendar.setTime(dateFormat.parse(DOB));
                } catch (ParseException exception) {
                    exception.printStackTrace();
                }

                employee.setDOB(calendar);
                employee.setContactNum(contactNum);
                employee.setEmail(email);
                employee.setNumHolidays(numHolidays);
                employee.setContractHours(contractHours);
                employee.setSalary(salary);
                employee.setOnHoliday(onHoliday);
                employee.setOffSick(offSick);
                employee.setLastShift(lastShift);
                employee.setWard_ID(ward_ID);
                employee.setPassword(password);
                employee.setPrivilege(privilege);
                employee.setEmployee_type(employee_type);

                employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }

        return employeeList;
    }

    public Ward getWard(int wardIn){
        getDBConnection();
        String query="select * from ward where ward_id="+wardIn+";";
        Ward ward=new Ward();
        try {
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){

                ward.setWard_ID(resultSet.getInt("ward_ID"));
                ward.setReqNurses(resultSet.getInt("reqNurses"));
                ward.setReqDoctors(resultSet.getInt("reqDoctors"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ward;
    }
    public ArrayList<Shift> getShifts(){
        getDBConnection();
        String query="select * from shift;";
        ArrayList<Shift> shifts=new ArrayList<>();
        try {
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                Shift shift=new Shift();
                shift.setShift_ID(resultSet.getInt("shift_ID"));
                shift.setStartTime(resultSet.getString("startTime"));
                shift.setEndTime(resultSet.getString("endTime"));
                shift.setShiftType(resultSet.getString("shiftType"));
                shift.setWard_ID(resultSet.getInt("ward_ID"));
//                shift.setDayOfWeek(resultSet.getString("dayOfWeek"));
                shifts.add(shift);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return shifts;
    }
}
