package core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import database.DBConnection;
import database.DBConnection_Clock;
import database.DBConnection_Holidays;


public class bookHolidays extends JFrame implements ActionListener {

	private JButton btnBook, btnCancel;
	private JLabel lblTitle;
	private JLabel lblstart, lblend;
	private JPanel pnlmain, btnPanel, pnltitle;
	private String startDate, endDate;
	private JTextField endField;
    private int passed_in_id;
    private JTextField startField;

    private DBConnection dbConnection = new DBConnection();
    private DBConnection_Holidays dbConnection_hol = new DBConnection_Holidays();
	
	public bookHolidays(int id_in){
		setLayout(new BorderLayout());
        setTitle("Place holiday booking");
	
        passed_in_id = id_in;

        pnltitle = new JPanel();
        JLabel lblTitle = new JLabel("Book Holiday");
        lblTitle.setFont(new Font("Sans Serif", Font.ITALIC, 24));
        pnltitle.add(lblTitle, BorderLayout.CENTER);
        add(pnltitle, BorderLayout.NORTH);
        
        
        pnlmain= new JPanel();
        pnlmain.setLayout(new GridLayout(2, 2, 10, 10));
        JLabel lblstart = new JLabel("Start Date");
        lblstart.setFont(new Font("Sans Serif", Font.ITALIC, 24));      
        startField= new JTextField();
        pnlmain.add(lblstart);
        pnlmain.add(startField);
        JLabel lblend = new JLabel("End Date");
        lblend.setFont(new Font("Sans Serif", Font.ITALIC, 24));
        endField= new JTextField();
        pnlmain.add(lblend);   
        pnlmain.add(endField);
        add(pnlmain, BorderLayout.CENTER);
        
              
        
        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(1, 2, 10, 10));
        btnPanel.add(btnBook = new JButton("Place Request"));
        btnPanel.add(btnCancel = new JButton("Cancel"));
        btnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(btnPanel, BorderLayout.SOUTH);
        
        
        
        btnBook.addActionListener(this);
        btnCancel.addActionListener(this);
        
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		switch (e.getActionCommand()) {
        
        case "Place Request":

            Calendar calendarStart = Calendar.getInstance();
            Calendar calendarEnd = Calendar.getInstance();
            Calendar cur = Calendar.getInstance();
            //System.out.print("staryfield = " + startField.getText());
            try { // try parsing the string to a Calendar object
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                calendarStart.setTime(dateFormat.parse(startField.getText()));
                calendarEnd.setTime(dateFormat.parse(endField.getText()));
            } catch (ParseException exception) {
                exception.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
                System.out.print(exception);
            }
            long current= System.currentTimeMillis();
            cur.setTimeInMillis(current);

            if(startField.getText().equals("") || endField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter dates into each field.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else if(calendarEnd.before(calendarStart)) {
                JOptionPane.showMessageDialog(null, "End date can not be before Start date.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
                }
            else if(calendarStart.before(cur)){
                JOptionPane.showMessageDialog(null, "You can not book a holiday in the past.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else{
                    JOptionPane.showMessageDialog(null, "Request submitted!", "Request", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();

                    dbConnection_hol.createRequest(passed_in_id, calendarStart, calendarEnd);

//                    int daysBetween = calendarEnd.DAY_OF_MONTH - calendarStart.DAY_OF_MONTH;

                    int daysBetween = daysBetween(calendarStart.getTime(), calendarEnd.getTime());


                    boolean isSuccessful = dbConnection_hol.updateHolidayHours(daysBetween,passed_in_id);

                if (!isSuccessful) {
                    JOptionPane.showMessageDialog(null, "Something unexpected happened.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }

                    // Select dates between the two dates
                    // ("select Date, TotalAllowance from Calculation where EmployeeId = 1
                    // and Date >= '2011/02/25' and Date <= '2011/02/27'")


                    break;
                }
        case "Cancel":
            this.dispose();
        	
        	break;
		
		
		
		
	}

	
	}

    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
