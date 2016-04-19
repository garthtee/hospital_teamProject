package core;

import database.DBConnection;
import database.DBConnection_Clock;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EmployeePage extends JFrame implements ActionListener {


    private JPanel center, title;
    private JPanel listOfHours, btnPanel;
    private JList<Shift> list;
    private JScrollPane scrollPane = new JScrollPane();
    private JButton btnBookHoliday, btnClockIn, btnLogout;
    private JLabel lblSpace;
    private Employee loggedInEmp;
    private DBConnection dbConnection = new DBConnection();
    private DBConnection_Clock dbConnection_clock = new DBConnection_Clock();
    private ArrayList<Shift> listOfShifts = new ArrayList<>();
    public int employee_id_in;

    private JButton btnViewRequest;




    private DefaultListModel<Shift> defaultListModel;


	public void createShiftModel(){
		listOfShifts = dbConnection.getShifts();


		defaultListModel = new DefaultListModel<>();


		for (Shift shift:listOfShifts)
			defaultListModel.addElement(shift);

	}
//    public void createRequestModel(){
//        listOfRequests = dbConnection.getRequests();
//
//
//        requestDefaultListModelModel = new DefaultListModel<>();
//
//
//        for (Request request:listOfRequests)
//            requestDefaultListModelModel.addElement(request);
//
//    }

    public EmployeePage(int emp_ID_In) {

        employee_id_in = emp_ID_In;

        setLayout(new BorderLayout());
        setTitle("Employee Home Page");

        title = new JPanel();
        JLabel lblTitle = new JLabel("Staff Home Page");
        lblTitle.setFont(new Font("Sans Serif", Font.ITALIC, 24));
        title.add(lblTitle, BorderLayout.CENTER);


        listOfHours = new JPanel();
        list = new JList(listOfShifts.toArray()); //data has type Object[]
        scrollPane.getViewport().setView(list);
        scrollPane.setPreferredSize(new Dimension(400, 360));
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);

        listOfHours.add(scrollPane, BorderLayout.SOUTH);
        listOfHours.setBorder(BorderFactory.createTitledBorder("Hours for week"));

//        listOfReq= new JPanel();
//        reqList= new JList(listOfRequests.toArray());
//        scrollPaneReq.getViewport().setView(reqList);
//        //scrollPaneReq.setPreferredSize(new Dimension(400, 360));
//        reqList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        reqList.setLayoutOrientation(JList.VERTICAL);
//
//        listOfReq.setBorder(BorderFactory.createTitledBorder("Requests made"));
//
//        listOfReq.add(scrollPane, BorderLayout.SOUTH);


//        bothLists=new JPanel();
//        bothLists.setLayout(new GridLayout(2,1));
//        bothLists.add(listOfHours,BorderLayout.NORTH);
//        bothLists.add(listOfReq, BorderLayout.SOUTH);

        // Panel Left //
        center = new JPanel();
        center.add(title, BorderLayout.NORTH);
        center.add(listOfHours, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);


        // Panel 2 //
        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(5, 1, 10, 10));
        btnPanel.add(btnClockIn = new JButton("Clock In"));
        btnPanel.add(btnBookHoliday = new JButton("Request Holiday"));
        btnPanel.add(btnViewRequest = new JButton("View Requests"));

        btnPanel.add(lblSpace = new JLabel(""));
        btnPanel.add(btnLogout = new JButton("Log Out"));
        btnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

       ;
        add(btnPanel, BorderLayout.EAST);

        // Add action listeners on each button
        btnClockIn.addActionListener(this);
        btnBookHoliday.addActionListener(this);

        btnLogout.addActionListener(this);
        btnViewRequest.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        long startTime = 0, endTime = 0;

        Calendar calendar = Calendar.getInstance();
        switch (e.getActionCommand()) {
            case "Clock In":

                startTime = System.currentTimeMillis();
                calendar.setTimeInMillis(startTime);
                System.out.print("CALENDAR OBJECT = " + calendar);
                btnClockIn.setText("Clock Out");
                JOptionPane.showMessageDialog(null, "Clock in time: " + calendar.getTime());

                dbConnection_clock.createClockInTime(1, calendar); //just paassing the value one rather than actual user ID


                break;
            case "Clock Out":

                endTime = System.currentTimeMillis();
                JOptionPane.showMessageDialog(null, "Clock out time: " + calendar.getTime());
                calendar.setTimeInMillis(endTime);
                btnClockIn.setText("Clock In");

                //System.getProperty("user_ID");
                dbConnection_clock.createClockOutTime(1, calendar, employee_id_in);

                break;
            case "Request Holiday":
                bookHolidays sp = new bookHolidays(employee_id_in);
                sp.setVisible(true);
                sp.pack();
                sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                sp.setLocationRelativeTo(null);
                break;

            case "View Requests":

                viewRequest vr= new viewRequest(employee_id_in);
                vr.setVisible(true);
                vr.pack();
                vr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                vr.setLocationRelativeTo(null);
                break;

            case "Log Out":
                LogInForm logInForm = new LogInForm();
                logInForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                logInForm.setSize(300, 250);
                logInForm.setLocationRelativeTo(null);
                logInForm.setVisible(true);
                this.dispose();
                break;


        }

    }
}
