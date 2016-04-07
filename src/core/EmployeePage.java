package core;

import database.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private DBConnection dbConnection;
	private ArrayList<Employee> listOfShifts = new ArrayList<>();
	
	
	
		public EmployeePage(){
		
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
	        
	        
	     // Panel Left //
	        center = new JPanel();
	        center.add(title, BorderLayout.NORTH);
	        center.add(listOfHours, BorderLayout.CENTER);
	        add(center, BorderLayout.CENTER);
	        
	        
	     // Panel 2 //
	        btnPanel = new JPanel();
	        btnPanel.setLayout(new GridLayout(4, 1, 10, 10));
	        btnPanel.add(btnClockIn = new JButton("Clock In/ Clock Out"));
	        btnPanel.add(btnBookHoliday = new JButton("Request Holiday"));
	        btnPanel.add(lblSpace = new JLabel(""));
	        btnPanel.add(btnLogout = new JButton("Log Out"));
	        btnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
	        add(btnPanel, BorderLayout.EAST);

	        // Add action listeners on each button
	        btnClockIn.addActionListener(this);
	        btnBookHoliday.addActionListener(this);

	        btnLogout.addActionListener(this);
	        
		
	}
	
	
	public static void main(String[] args) {
       EmployeePage gui = new EmployeePage();
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(600, 500);
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		long startTime=0, endTime=0;

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
		switch (e.getActionCommand()) {
			case "Clock In":

				startTime = System.currentTimeMillis();
				calendar.setTimeInMillis(startTime);
				formatter.format(calendar.getTime());
				btnClockIn.setText("Clock Out");
				JOptionPane.showMessageDialog( null,"Clock in time: " +formatter.format(calendar.getTime()));

				dbConnection.createClockInTime(1, calendar); //just passing the value one rather than actual user ID

				break;
			case "Clock Out":

				endTime= System.currentTimeMillis();
				JOptionPane.showMessageDialog( null,"Clock out time: "+ formatter.format(calendar.getTime()));
				calendar.setTimeInMillis(endTime);
				formatter.format(calendar.getTime());
				btnClockIn.setText("Clock In");

				//System.getProperty("user_ID");
				dbConnection.createClockOutTime(1,calendar);

				break;
			case "Request Holiday":
				bookHolidays sp = new bookHolidays();
				sp.setVisible(true);
				sp.pack();
				sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				sp.setLocationRelativeTo(null);
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
