package database;

import core.Shift_Employee;

import java.sql.*;
import java.util.LongSummaryStatistics;

/**
 * Created by garth on 10/04/2016.
 */
public class DBConnection_Scheduler {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DBConnection_Scheduler() {
    }

    private void getDBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
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
            preparedStatement = connection.prepareStatement("INSERT INTO shift_employee VALUES(?,?,?);");
            preparedStatement.setInt(1, shiftEmployee.getShift_ID()); // employee id 0 as it's auto incremented in DB
            preparedStatement.setInt(2, shiftEmployee.getEmployee_ID());


            preparedStatement.setString(3, shiftEmployee.getDate());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
    }
}
