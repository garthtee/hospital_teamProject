package database;

import core.Employee;
import core.Shift;
import core.Ward;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import java.security.spec.KeySpec;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Group 5 on 10/03/2016.
 */
public class DBConnection {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DBConnection() {}

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

    public void createEmployee(String fNameIn, String sNameIn, Calendar DOBIn, String contactNumIn, String emailIn,
                               double numHolidaysIn, double contractHoursIn, double salary, int ward_IDIn, String password, String privilege) {

        getDBConnection();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO employee VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            preparedStatement.setInt(1, 0); // employee id 0 as it's auto incremented in DB
            preparedStatement.setString(2, fNameIn);
            preparedStatement.setString(3, sNameIn);
            // create a date
            java.sql.Date sqlDate = new java.sql.Date(DOBIn.getTimeInMillis());
            // end creating date
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setString(5, contactNumIn);
            preparedStatement.setString(6, emailIn);
            preparedStatement.setDouble(7, numHolidaysIn);
            preparedStatement.setDouble(8, contractHoursIn);
            preparedStatement.setDouble(9, salary);
            preparedStatement.setInt(10, 0);
            preparedStatement.setInt(11, 0);
            preparedStatement.setString(12, "none");
            preparedStatement.setInt(13, ward_IDIn);
            preparedStatement.setString(14, password);
            preparedStatement.setString(15, privilege);
//            System.out.print("Details; \n" + fNameIn + " " + sNameIn + " " + sqlDate.toString() + " " + contactNumIn + " " + emailIn + " " + numHolidaysIn + " " + contractHoursIn + " " + salary + " " + ward_IDIn);
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

    public void removeEmployee(Employee employee) {

        getDBConnection();

        try {
            String name = employee.getfName();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("DELETE from employee WHERE emp_ID = ?;");
            preparedStatement.setInt(1, employee.getEmp_ID());
            int count = preparedStatement.executeUpdate();

            if (count > 0)
                JOptionPane.showMessageDialog(null, name + " has been removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Employee not removed.", "Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            e.getStackTrace();
        }
        finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
    }

    public void updateEmployee(Employee employee) {

        getDBConnection();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("UPDATE employee SET fName = ?, sName = ?, " +
                    "DOB = ?, contactNum = ?, email = ?, numHolidays = ?, contractHours = ?, " +
                    "salary = ?, onHoliday = ?, offSick = ?, lastShift = ?, ward_ID = ?, password = ?, privilege = ? WHERE emp_ID = " + employee.getEmp_ID() + ";");
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

        } catch (Exception e) {
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



    public void createClockInTime(int emp_ID, Calendar startTime){

        getDBConnection();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO employee_clockin VALUES (?, ?);");
            preparedStatement.setInt(1,emp_ID);

            java.sql.Date sqlDate = new java.sql.Date(startTime.getTimeInMillis());
            preparedStatement.setDate(2,sqlDate);


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

    public void createClockOutTime(int emp_ID, Calendar endTime){

        getDBConnection();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO employee_clockin VALUES (?, ?);");

            preparedStatement.setInt(1,emp_ID);

            java.sql.Date sqlDate = new java.sql.Date(endTime.getTimeInMillis());
            preparedStatement.setDate(2,sqlDate);

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

    private void encryptPassword(String password) {

        try {
            Random random = new Random();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            System.out.printf("salt: %s%n", enc.encodeToString(salt));
            System.out.printf("hash: %s%n", enc.encodeToString(hash));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Ward> getWards(){
        getDBConnection();
        String query="select * from ward;";
        ArrayList<Ward> wards=new ArrayList<>();
        try {
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                Ward ward=new Ward();
                ward.setWard_ID(resultSet.getInt("ward_ID"));
                ward.setReqNurses(resultSet.getInt("reqNurses"));
                ward.setReqDoctors(resultSet.getInt("reqDoctors"));
                wards.add(ward);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return wards;
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
