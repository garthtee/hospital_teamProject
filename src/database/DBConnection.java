package database;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import core.Employee;
import core.Request;
import core.Shift;
import core.Ward;

import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Group 5 on 10/03/2016.
 */
public class DBConnection {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DBConnection() {
    }

    /* Remote AWS database connection */
    private void getDBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://hospital-team-project.cxcy14kqrkxb.eu-west-1.rds.amazonaws.com/hospital", "team", "teamHospital16");

            statement = connection.createStatement();

        } catch (CommunicationsException e){
            JOptionPane.showMessageDialog(null, "No internet connection.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (NullPointerException e) {
        }catch (SQLException e) {
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

    /* Employees */

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
                int ward_ID = resultSet.getInt("ward_ID");
                String password = resultSet.getString("password");
                String employee_type = resultSet.getString("employee_type");
                String privilege = resultSet.getString("privilege");

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
                employee.setWard_ID(ward_ID);
                employee.setPassword(password);
                employee.setEmployee_type(employee_type);
                employee.setPrivilege(privilege);

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

    public void createEmployee(Employee employee) {

        getDBConnection();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO employee VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            preparedStatement.setInt(1, 0); // employee id 0 as it's auto incremented in DB
            preparedStatement.setString(2, employee.getfName());
            preparedStatement.setString(3, employee.getsName());
            // create a date
            java.sql.Date sqlDate = new java.sql.Date(employee.getDOB().getTimeInMillis());
            // end creating date
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setString(5, employee.getContactNum());
            preparedStatement.setString(6, employee.getEmail());
            preparedStatement.setDouble(7, employee.getNumHolidays());
            preparedStatement.setDouble(8, employee.getContractHours());
            preparedStatement.setDouble(9, employee.getSalary());
            preparedStatement.setInt(10, 0);
            preparedStatement.setInt(11, 0);
            preparedStatement.setString(12, "none");
            preparedStatement.setInt(13, employee.getWard_ID());
            preparedStatement.setString(14, employee.getPassword());
            preparedStatement.setString(15, employee.getPrivilege());
            preparedStatement.setString(16, employee.getEmployee_type());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Catch");
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
    }

    public boolean removeEmployee(Employee employee) {
        boolean shiftsAssigned = false;
        shiftsAssigned = removeEmployeeFromShift_Employee(employee);
        boolean employeeRemoved = false;

        if (!shiftsAssigned) {
            getDBConnection();

            try {
                String name = employee.getfName();
                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement("DELETE from employee WHERE emp_ID = ?;");
                preparedStatement.setInt(1, employee.getEmp_ID());
                int count = preparedStatement.executeUpdate();

                if (count > 0) {
                    JOptionPane.showMessageDialog(null, name + " has been removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    employeeRemoved = true;
                }

            } catch (Exception e) {
                e.getStackTrace();
            } finally {
                closeResultSet();
                closeStatement();
                closeConnection();
            }
        }

        return employeeRemoved;

    }

    private boolean removeEmployeeFromShift_Employee(Employee employee) {

        boolean shiftsAssigned = false;

        getDBConnection();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("SELECT * from shift_employee WHERE emp_ID = ?;");
            preparedStatement.setInt(1, employee.getEmp_ID());
            int results = preparedStatement.executeUpdate();

            if (results > 0)
                shiftsAssigned = true;
            else
                shiftsAssigned = false;

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }

        return shiftsAssigned;
    }

    public void updateEmployee(Employee employee) {

        getDBConnection();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("UPDATE employee SET fName = ?, sName = ?, " +
                    "DOB = ?, contactNum = ?, email = ?, numHolidays = ?, contractHours = ?, " +
                    "salary = ?, onHoliday = ?, offSick = ?, lastShift = ?, ward_ID = ?, password = ?, privilege = ?, employee_type = ? WHERE emp_ID = " + employee.getEmp_ID() + ";");
            preparedStatement.setString(1, employee.getfName());
            preparedStatement.setString(2, employee.getsName());

            java.sql.Date sqlDate = new java.sql.Date(employee.getDOB().getTimeInMillis()); // create a date

            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4, employee.getContactNum());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setDouble(6, employee.getNumHolidays());
            preparedStatement.setDouble(7, employee.getContractHours());
            preparedStatement.setDouble(8, employee.getSalary());
            preparedStatement.setDouble(9, employee.isOnHoliday());
            preparedStatement.setDouble(10, employee.isOffSick());
            preparedStatement.setString(11, employee.getLastShift());
            preparedStatement.setInt(12, employee.getWard_ID());
            preparedStatement.setString(13, employee.getPassword());
            preparedStatement.setString(14, employee.getPrivilege());
            preparedStatement.setString(15, employee.getEmployee_type());
            int count = preparedStatement.executeUpdate();

            if (count > 0)
                JOptionPane.showMessageDialog(null, "Employee updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Employee not updated.", "Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
    }

    public ArrayList<String> getLoginExistence(int username, String password) {

        getDBConnection();

        String exists = "false";
        String privilege = "null";

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE emp_id = ? AND password = ?");
            preparedStatement.setInt(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                privilege = resultSet.getString("privilege");
            }

            resultSet.beforeFirst();

            if (resultSet.next())
                exists = "true";
            else
                JOptionPane.showMessageDialog(null, "Login incorrect or no user exists.", "Incorrect Login", JOptionPane.ERROR_MESSAGE);

        } catch (NullPointerException e) {}
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
        ArrayList<String> resultList = new ArrayList<>();
        resultList.add(exists);
        resultList.add(privilege);
        return resultList;
    }

    /* SHIFTS */

    public ArrayList<Shift> getShifts() {
        getDBConnection();
        String query = "select * from shift;";
        ArrayList<Shift> shifts = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Shift shift = new Shift();
                shift.setShift_ID(resultSet.getInt("shift_ID"));
                shift.setStartTime(resultSet.getString("startTime"));
                shift.setEndTime(resultSet.getString("endTime"));
                shift.setShiftType(resultSet.getString("shiftType"));
                shift.setWard_ID(resultSet.getInt("ward_ID"));
                shifts.add(shift);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
        return shifts;
    }

    public void removeShift(Shift shift) {

        getDBConnection();

        try {
            int id = shift.getShift_ID();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("DELETE from shift WHERE shift_ID = ?;");
            preparedStatement.setInt(1, shift.getShift_ID());
            int count = preparedStatement.executeUpdate();

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Shift " + id + " has been removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
    }


    public void addShift(Shift shift) {

        getDBConnection();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO shift VALUES(?,?,?,?,?);");
            preparedStatement.setInt(1, shift.getShift_ID()); // shift id 0 as it's auto incremented in DB
            preparedStatement.setString(2, shift.getStartTime());
            preparedStatement.setString(3, shift.getEndTime());
            preparedStatement.setString(4, shift.getShiftType());
            preparedStatement.setInt(5, shift.getWard_ID());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
    }

    public void updateShift(Shift shift) {

        getDBConnection();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("UPDATE shift SET startTime = ?, endTime = ?, " +
                    "shiftType = ?, ward_ID = ? WHERE shift_ID = " + shift.getShift_ID() + ";");
            preparedStatement.setString(1, shift.getStartTime());
            preparedStatement.setString(2, shift.getEndTime());
            preparedStatement.setString(3, shift.getShiftType());
            preparedStatement.setInt(4, shift.getWard_ID());
            int count = preparedStatement.executeUpdate();

            if (count > 0)
                JOptionPane.showMessageDialog(null, "Shift updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Shift not updated.", "Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
    }

    /* WARDS */

    public ArrayList<Ward> getWards() {
        getDBConnection();
        String query = "select * from ward;";
        ArrayList<Ward> wards = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Ward ward = new Ward();
                ward.setWard_ID(resultSet.getInt("ward_ID"));
                ward.setWardType(resultSet.getString("wardType"));
                ward.setReqNurses(resultSet.getInt("reqNurses"));
                ward.setReqDoctors(resultSet.getInt("reqDoctors"));
                ward.setScheduled(resultSet.getString("scheduled"));
                wards.add(ward);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
        return wards;
    }

    public void addWard(Ward ward) {

        getDBConnection();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO ward VALUES(?,?,?,?,?);");
            preparedStatement.setInt(1, 0); // ward id 0 as it's auto incremented in DB
            preparedStatement.setString(2, ward.getWardType());
            preparedStatement.setInt(3, ward.getReqNurses());
            preparedStatement.setInt(4, ward.getReqDoctors());
            preparedStatement.setString(5, ward.getScheduled());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
    }

    public void removeWard(Ward ward) {

        getDBConnection();

        try {
            String wardType = ward.getWardType();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("DELETE from ward WHERE ward_ID = ?;");
            preparedStatement.setInt(1, ward.getWard_ID());
            int count = preparedStatement.executeUpdate();

            if (count > 0)
                JOptionPane.showMessageDialog(null, wardType + " has been removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Ward not removed.", "Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
    }

    public void updateWard(Ward ward) {

        getDBConnection();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("UPDATE ward SET wardType = ?, reqNurses = ?, " +
                    "reqDoctors = ?, scheduled = ? WHERE ward_ID = " + ward.getWard_ID() + ";");
            preparedStatement.setString(1, ward.getWardType());
            preparedStatement.setInt(2, ward.getReqNurses());
            preparedStatement.setInt(3, ward.getReqDoctors());
            preparedStatement.setString(4, ward.getScheduled());
            int count = preparedStatement.executeUpdate();

            if (count > 0)
                JOptionPane.showMessageDialog(null, "Ward updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Ward not updated.", "Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
    }

    /* REQUESTS */

    public ArrayList<Request> getReq() {

        getDBConnection();

        ArrayList<Request> requestList = new ArrayList<>();

        try {
            String query = "select * from request;";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int emp_id = resultSet.getInt("request_ID");
                String startDate = resultSet.getString("dateFrom");
                String endDate = resultSet.getString("dateTo");
                String status = resultSet.getString("status");
                String empID = resultSet.getString("emp_ID");
                int employeeId = Integer.parseInt(empID);

                Request request = new Request(startDate, endDate, employeeId);
                requestList.add(request);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }

        return requestList;
    }
}
