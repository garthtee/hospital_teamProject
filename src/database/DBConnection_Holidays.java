package database;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by home on 11/04/2016.
 */
public class DBConnection_Holidays {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DBConnection_Holidays() {}

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

    public void createRequest(int emp_ID, Calendar startDate, Calendar endDate){
        getDBConnection();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO request VALUES (?, ?, ?, ?, ?);");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateAsString = dateFormat.format(startDate.getTime());
            String dateAsString2 = dateFormat.format(endDate.getTime());

            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, dateAsString);
            //java.sql.Date sqlDate2 = new java.sql.Date(startDate.getTimeInMillis());
            preparedStatement.setString(3, dateAsString2);
            preparedStatement.setString(4, "awaiting approval");
            preparedStatement.setInt(5, emp_ID);

            preparedStatement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
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
}
