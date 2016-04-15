package core;

import database.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Group 5 on 04/03/2016.
 */
public class ApproveHolidayRequestPage extends JFrame  implements ActionListener{

    private JPanel listPanel, panelBtn, pendingPanel, approvedPanel, rejectedPanel;
    private JLabel lblTitle;
	 
    private JList<Request> pendingList;
    private JList<Request> approvedList;
	 private JList<Request> rejList ;
	 
    public DBConnection db = new DBConnection ();
    private ArrayList<Request> reqArray ;
    private ArrayList<Request> pendingArray ;
    private ArrayList<Request> approvedArray = new ArrayList<Request>();
	 private ArrayList<Request> rejArray = new ArrayList<>();
	 
    private JScrollPane pendingScroll ;
    private JScrollPane approvedScroll = new JScrollPane();
	 private JScrollPane rejScroll = new JScrollPane();
	  
    private JButton btnCancel, btnApprove, btnDecline;
   //  private DBConnection dbConnection = new DBConnection();

    public ApproveHolidayRequestPage() {

        setTitle("Holiday Requests");
        setLayout(new BorderLayout());	
        reqArray = new ArrayList<Request>();
        //reqArray = dbConnection.getRequests()
        
       	      
        
        //Right Panel with Buttons        
        panelBtn = new JPanel();
        panelBtn.setLayout( new GridLayout( 5, 1, 10, 10));                
        panelBtn.add(btnApprove = new JButton("Approve Request"));
		  btnApprove.addActionListener(this);
        panelBtn.add(btnDecline = new JButton("Decline Request"));
		  btnDecline.addActionListener(this);
        panelBtn.add(btnCancel = new JButton("Cancel"));   
		  btnCancel.addActionListener(this);     
        panelBtn.setBorder(new EmptyBorder(10,10,10,10));
        add(panelBtn, BorderLayout.EAST);
		  
         // Pending List
		  pendingPanel = new JPanel();
		  pendingArray = new ArrayList();
        pendingList = new JList(pendingArray.toArray());
	     pendingScroll = new JScrollPane();		  
        pendingScroll.getViewport().setView(pendingList);
        pendingScroll.setPreferredSize(new Dimension(160, 430));
        pendingList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        pendingList.setLayoutOrientation(JList.VERTICAL);
        pendingPanel.add(pendingScroll, BorderLayout.SOUTH);
       	pendingPanel.setBorder(BorderFactory.createTitledBorder("Pending Holiday Requests"));

        // Approved List
		  approvedPanel = new JPanel();
		  approvedList = new JList(); //data has type Object[]
		  approvedArray = new ArrayList();
	     approvedScroll = new JScrollPane();
        approvedScroll.getViewport().setView(approvedList);
        approvedScroll.setPreferredSize(new Dimension(160, 430));
        approvedList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        approvedList.setLayoutOrientation(JList.VERTICAL);
        approvedPanel.add(approvedScroll, BorderLayout.SOUTH);
        approvedPanel.setBorder(BorderFactory.createTitledBorder("Approved Holiday Requests"));
		 
		  
		  //Rejected List
		  rejectedPanel = new JPanel();
		  rejList = new JList(); //data has type Object[]
		  rejArray = new ArrayList();
	     rejScroll = new JScrollPane();
		  rejScroll.getViewport().setView(rejList);
        rejScroll.setPreferredSize(new Dimension(160, 430));
        rejList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        rejList.setLayoutOrientation(JList.VERTICAL);
        rejectedPanel.add(rejScroll, BorderLayout.SOUTH);

		  rejectedPanel.setBorder(BorderFactory.createTitledBorder("Rejected Holiday Requests"));
		  
		  
		  listPanel = new JPanel ();
		  listPanel.setLayout(new GridLayout(1, 3));
		  listPanel.add(pendingPanel);
		  listPanel.add(approvedPanel);
		  listPanel.add(rejectedPanel);

		  add(listPanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        ApproveHolidayRequestPage approveHolidayRequestPage = new ApproveHolidayRequestPage();
        approveHolidayRequestPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //approveHolidayRequestPage.pack();
        approveHolidayRequestPage.setSize(700, 500);
        approveHolidayRequestPage.setLocationRelativeTo(null);
        approveHolidayRequestPage.setVisible(true);
    }
    
    
	 
	public void actionPerformed(ActionEvent e) {
     if(e.getSource() == btnCancel)
	  {
		 E_ManagerPage mp = new E_ManagerPage();
		 mp.setVisible(true);
		 mp.pack();
		 //System.out.print("button pressed");
		 mp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 mp.setLocationRelativeTo(null);
		 this.dispose();
    }  
    
	 else if (e.getSource() == btnApprove){
	 System.out.print("Holiday Request Approved");
    JOptionPane.showMessageDialog(null, "Holiday request approved", " Success", JOptionPane.INFORMATION_MESSAGE);
    
	}
      else if (e.getSource() == btnDecline){
      System.out.print(" Holiday Request Denied");
      JOptionPane.showMessageDialog(null, "Holiday request denied", " Denied", JOptionPane.ERROR_MESSAGE);
      }
   
   //If no employee is selected and button is clicked error message: Please select a request
}
		 	 
	 
}

/*

  @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Cancel":
                this.dispose();
                AdminPage adminPage = new AdminPage();
                adminPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                adminPage.setSize(600, 500);
                adminPage.setLocationRelativeTo(null);
                adminPage.setVisible(true);
                break;
            case "Update":
                // Creating a calendar object and parsing the date text entered by user
                Calendar calendar = Calendar.getInstance();
                try { // try parsing the string to a Calendar object
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    calendar.setTime(dateFormat.parse(txtDOB.getText()));
                } catch (ParseException exception) {
                    exception.printStackTrace();
                }
                // Constructs new employee with updated information
                Employee employee = new Employee();
                employee.setEmp_ID(Integer.valueOf(txtID.getText()));
                employee.setfName(txtFName.getText());
                employee.setsName(txtSName.getText());
                employee.setDOB(calendar);
                employee.setContactNum(txtContactNum.getText());
                employee.setEmail(txtEmail.getText());
                employee.setNumHolidays(Integer.valueOf(txtNumHoldiays.getText()));
                employee.setContractHours(Integer.valueOf(txtContractHours.getText()));
                employee.setSalary(Double.valueOf(txtSalary.getText()));
                employee.setOnHoliday(Double.valueOf(txtOnHoliday.getText()));
                employee.setOffSick(Double.valueOf(txtOffSick.getText()));
                employee.setWard_ID(Integer.valueOf(txtWard_ID.getText()));
                employee.setPassword(txtPassword.getText());
                employee.setPrivilege(txtPrivilege.getText());
                // Update employee details in DB
                DBConnection dbConnection = new DBConnection();
                dbConnection.updateEmployee(employee);
                this.dispose();
                AdminPage adminPage1 = new AdminPage();
                adminPage1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                adminPage1.setSize(600, 500);
                adminPage1.setLocationRelativeTo(null);
                adminPage1.setVisible(true);
                break;
        }
    }
}


*/