package core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import database.DBConnection;

public class ApproveHolidayRequestPage extends JFrame  implements ActionListener{

    private JPanel listPanel, panelBtn, pendingPanel, approvedPanel, rejectedPanel;
    private JLabel lblTitle;
	 
    private JList<Request> pendingList;
    private JList<Request> approvedList;
	 private JList<Request> rejList ;
    
    private String empID;
    private int empIdInt ;
	 
    public DBConnection dbConnection = new DBConnection ();
	 
    private ArrayList<Request> reqArray ;
    private ArrayList<Request> pendingArray ;
    private ArrayList<Request> approvedArray;
	 private ArrayList<Request> rejArray;
	 
    private JScrollPane pendingScroll ;
    private JScrollPane approvedScroll = new JScrollPane();
	 private JScrollPane rejScroll = new JScrollPane();
	  
    private JButton btnCancel, btnApprove, btnDecline;
    //private DBConnection dbConnection = new DBConnection();

    public ApproveHolidayRequestPage() {

        setTitle("Holiday Requests");
        setLayout(new BorderLayout());	
        reqArray = new ArrayList<Request>();
        reqArray = dbConnection.getReq();

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
         String status = "";
         pendingArray = new ArrayList();
         approvedArray = new ArrayList();
         rejArray = new ArrayList();   
        
		  for(Request r : reqArray)
		  {
            status = r.getStatus();
            
            if(status.equals("pending"))
            {
               pendingArray.add(r);
               System.out.println(r.toString());
            }
            if(status.equals("approved"))
            {
               approvedArray.add(r);
               System.out.println(r.toString());
            }
            if(status.equals("declined"))
            {
               rejArray.add(r);
               System.out.println(r.toString());
            }
		  }
        
		  pendingPanel = new JPanel();
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
		  approvedList = new JList(approvedArray.toArray()); //data has type Object[]
	     approvedScroll = new JScrollPane();
        approvedScroll.getViewport().setView(approvedList);
        approvedScroll.setPreferredSize(new Dimension(160, 430));
        approvedList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        approvedList.setLayoutOrientation(JList.VERTICAL);
        approvedPanel.add(approvedScroll, BorderLayout.SOUTH);
        approvedPanel.setBorder(BorderFactory.createTitledBorder("Approved Holiday Requests"));
		 
		  //Rejected List
		  rejectedPanel = new JPanel();
		  rejList = new JList(rejArray.toArray()); //data has type Object[]		  
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
        approveHolidayRequestPage.pack();
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
		 mp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 mp.setLocationRelativeTo(null);
		 this.dispose();
    }  
    
	 else if (e.getSource() == btnApprove){
       if(pendingList.getSelectedValue() != null)
       {
         String empID = pendingList.getSelectedValue().toString();  

          //get the selected employee from list, use that employee
          //edit the Request -> so call the "updateStatus" method
          //then updateEmployee method 
          //dbConnection.updateEmployee(Employee e); //this employee will have all the same ,  except the status  
          
         empID = pendingList.getSelectedValue().toString();                  
         String beforeFirstSpace = empID.split(" ")[0];
         empIdInt = Integer.parseInt(beforeFirstSpace);
  
        
          System.out.println("employee selected = " +empIdInt );
          dbConnection.updateRequest( empIdInt, "approved");//passed request ID, and new status
          System.out.print("Holiday Request Approved");
          JOptionPane.showMessageDialog(null, "Holiday request approved", " Success", JOptionPane.INFORMATION_MESSAGE);
          ApproveHolidayRequestPage rq = new ApproveHolidayRequestPage();   //refresh page after req is chosen and moved
   		 rq.setVisible(true);
   		 rq.pack();
   		 rq.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   		 rq.setLocationRelativeTo(null);
   		 this.dispose();
          
        }else{
         JOptionPane.showMessageDialog(null, "Please select a request", " ", JOptionPane.ERROR_MESSAGE);
        }
    
	}
      else if (e.getSource() == btnDecline){
        if(pendingList.getSelectedValue() != null)
         {
      
             empID = pendingList.getSelectedValue().toString();                  
            String beforeFirstSpace = empID.split(" ")[0];
             empIdInt = Integer.parseInt(beforeFirstSpace);
            
                      System.out.println("employee selected = " + empIdInt);
              dbConnection.updateRequest(empIdInt, "declined"); //passed request ID, and new status      
               System.out.print(" Holiday Request Denied");
               ApproveHolidayRequestPage rq = new ApproveHolidayRequestPage();   //refresh page after req is chosen and moved
      		 rq.setVisible(true);
      		 rq.pack();
      		 rq.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      		 rq.setLocationRelativeTo(null);
      		 this.dispose();
   
               JOptionPane.showMessageDialog(null, "Holiday request denied", " Denied", JOptionPane.ERROR_MESSAGE);
            }else{
               JOptionPane.showMessageDialog(null, "Please select a request", " ", JOptionPane.ERROR_MESSAGE);
   
            }
      }
      
   
      
   //If no employee is selected and button is clicked error message: Please select a request
   //else if(){
   //JOptionPane.showMessageDialog(null, "Please select a request", " ", JOptionPane.ERROR_MESSAGE);

   
}
		 	 
	 
}

