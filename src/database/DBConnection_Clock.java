package database;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.*;
import java.util.Date;

/**
 * Created by home on 08/04/2016.
 */
public class DBConnection_Clock {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DBConnection_Clock() {}

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

    public void createClockInTime(int emp_ID, Calendar startTime){

        getDBConnection();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO employee_clockin VALUES (?, ?, ?, ?);");
            preparedStatement.setInt(1,0);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dateAsString = dateFormat.format(startTime.getTime());


            preparedStatement.setString(2, dateAsString);
            java.sql.Date sqlDate2 = new java.sql.Date(startTime.getTimeInMillis());
            preparedStatement.setString(3, "0000-00-00 00:00:00");
            preparedStatement.setInt(4, emp_ID);

            preparedStatement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }

    }

    public void createClockOutTime(int emp_ID, Calendar endTime){

        if(emp_ID != -1) {
            getDBConnection();

            try {
                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement("UPDATE employee_clockin SET endTime = ? WHERE emp_ID = ?");

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String dateAsString = dateFormat.format(endTime.getTime());

                preparedStatement.setString(1, dateAsString);
                preparedStatement.setInt(2, emp_ID);
                System.out.println("-----> " +dateAsString);
                System.out.println("-----> " +emp_ID);

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
}
