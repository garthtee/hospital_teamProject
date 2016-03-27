package database;

import core.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by garth on 10/03/2016.
 */
public class DBConnection {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            statement = connection.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> getEmployees() {

        ArrayList<Employee> employeeList = new ArrayList<>();

        try {
            String query = "select * from employee;";
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                int emp_id = resultSet.getInt("emp_ID");
                String fName = resultSet.getString("fName");
                String sName = resultSet.getString("sName");
                String contactNum = resultSet.getString("contactNum");
                String email = resultSet.getString("email");
                Employee employee = new Employee();
                employee.setEmp_ID(emp_id);
                employee.setfName(fName);
                employee.setsName(sName);
                employee.setContactNum(contactNum);
                employee.setEmail(email);
                employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    public void createEmployee(String fNameIn, String sNameIn, Calendar DOBIn, String contactNumIn, String emailIn,
                               double numHolidaysIn, double contractHoursIn, double salary, int ward_IDIn) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO employee VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);");
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
            System.out.print("Details; \n" + fNameIn +" " + sNameIn +" " + sqlDate.toString() +" " + contactNumIn +" " + emailIn +" " + numHolidaysIn +" " + contractHoursIn +" " + salary +" " + ward_IDIn);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Catch");
        }
    }

    public void removeEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("DELETE from employee WHERE emp_ID = ?;");
            preparedStatement.setInt(1, employee.getEmp_ID());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void updateEmployee(int emp_ID, String fNameIn, String sNameIn, Calendar DOBIn, String contactNumIn, String emailIn,
                               double numHolidaysIn, double contractHoursIn, double salary, int onHoliday, int offSick,
                               String lastShift, int ward_ID) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("UPDATE employee SET fName = ?, sName = ?, " +
                    "DOB = ?, contactNum = ?, email = ?, numHolidays = ?, contractHours = ?, " +
                    "salary = ?, onHoliday = ?, offSick = ?, lastShift = ?, ward_ID = ?) WHERE emp_ID = " + emp_ID +";");
            preparedStatement.setString(1, fNameIn);
            preparedStatement.setString(2, sNameIn);

            java.sql.Date sqlDate = new java.sql.Date(DOBIn.getTimeInMillis()); // create a date

            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4, contactNumIn);
            preparedStatement.setString(5, emailIn);
            preparedStatement.setDouble(6, numHolidaysIn);
            preparedStatement.setDouble(7, contractHoursIn);
            preparedStatement.setDouble(8, salary);
            preparedStatement.setInt(9, onHoliday);
            preparedStatement.setInt(10, offSick);
            preparedStatement.setString(11, lastShift);
            preparedStatement.setInt(12, ward_ID);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
