package database;

import core.Employee;

import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

                employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            System.out.print("Details; \n" + fNameIn + " " + sNameIn + " " + sqlDate.toString() + " " + contactNumIn + " " + emailIn + " " + numHolidaysIn + " " + contractHoursIn + " " + salary + " " + ward_IDIn);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Catch");
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void removeEmployee(Employee employee) {
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
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("UPDATE employee SET fName = ?, sName = ?, " +
                    "DOB = ?, contactNum = ?, email = ?, numHolidays = ?, contractHours = ?, " +
                    "salary = ?, onHoliday = ?, offSick = ?, lastShift = ?, ward_ID = ? WHERE emp_ID = " + employee.getEmp_ID() + ";");
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
            int count = preparedStatement.executeUpdate();

            if (count > 0)
                JOptionPane.showMessageDialog(null, "Employee updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Employee not updated.", "Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void closeDatabase() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
