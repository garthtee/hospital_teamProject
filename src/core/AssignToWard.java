package core;

import database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AssignToWard extends JFrame implements ActionListener{
	
	private JPanel employeePanel, panelBtn, assignedPanel, listPanel;
   
	private JList<Employee> empList;
   private JList<Employee> assignedList;
   
	private JButton btnAssign, btnShowAll, btnBack;
   
   private ArrayList<Employee> employees = new ArrayList<Employee>();
	private ArrayList<Employee>  dataUnassigned = new ArrayList();
   private ArrayList<Employee> assignedArray = new ArrayList<Employee>();
   
   private String status = "";
   
	private String[] wards={"1A", "1B", "2A", "2B", "Theatre"};
	private JComboBox wardList=new JComboBox(wards);
	private JScrollPane scrollPane, aScrollPane;
	private JLabel lbl1, lbl2;
	private Shift shift1;
   private int wardType;
   
    private DBConnection dbConnection = new DBConnection();
	
	public AssignToWard(){
		
		setTitle("Assign to Ward");		
		setLayout(new BorderLayout());
      
      populateList();  

      
		employeePanel=  new JPanel();		
		empList= new JList(dataUnassigned.toArray());
      scrollPane = new JScrollPane();
      scrollPane.getViewport().setView(empList);
      scrollPane.setPreferredSize(new Dimension(250, 520));
		empList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		empList.setLayoutOrientation(JList.VERTICAL);
      employeePanel.add(scrollPane, BorderLayout.SOUTH);
		employeePanel.setBorder(BorderFactory.createTitledBorder("Unassigned Employees"));
		
      
     // Assigned Employees Panel
      assignedPanel = new JPanel();
      assignedList = new JList(assignedArray.toArray()); //data has type Object[]
      aScrollPane = new JScrollPane();
      aScrollPane.getViewport().setView(assignedList);
      aScrollPane.setPreferredSize(new Dimension(250, 520));
      assignedList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
      assignedList.setLayoutOrientation(JList.VERTICAL);
      assignedPanel.add(aScrollPane, BorderLayout.SOUTH);
      assignedPanel.setBorder(BorderFactory.createTitledBorder("Assigned Employees"));
      
      
      
      
		//scrollPane = new JScrollPane(empList);
		//employeePanel.add(empList);
              
		//Right panel with buttons
		panelBtn=new JPanel();
		panelBtn.setLayout(new GridLayout(6, 1));		
		btnAssign=new JButton("Assign to Ward");
      btnBack=new JButton("Back");
      btnBack.addActionListener(this);		
		panelBtn.add(wardList);
		panelBtn.add(btnAssign);
		//panelBtn.add(btnShowAll);
		panelBtn.add(lbl1=new JLabel());
		panelBtn.add(lbl2=new JLabel());
		panelBtn.add(btnBack);		
		add(employeePanel, BorderLayout.CENTER);
		add(panelBtn, BorderLayout.EAST);
      
      listPanel = new JPanel();
      listPanel.setLayout(new GridLayout(1, 2));
      listPanel.add(employeePanel);
      listPanel.add(assignedPanel);
      add(listPanel, BorderLayout.CENTER);
      
		 // Add Action Listeners
        btnAssign.addActionListener(this);
        btnBack.addActionListener(this);

		
	}
   
   public void populateList(){
      try 
		{
			ArrayList<Employee> employees = null;
			employees = dbConnection.getEmployees();
         System.out.println("Employees added to list .." + employees.size());
         
         
         for( Employee e : employees)      
         {
            wardType = e.getWard_ID();
            System.out.println(" " +wardType);
                     
            if(wardType == 0)
            {
               dataUnassigned.add(e);
               System.out.println(e.toString());
            }
            if(wardType > 0)
            {
               assignedArray.add(e);
               System.out.println(e.toString());
   
            }
           }
                
      }catch(Exception e){
         e.printStackTrace();
      }

   }
	
	public static void main(String []args){
		AssignToWard frame=new AssignToWard();
		frame.setSize(700, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
   
   
   public void actionPerformed(ActionEvent e ) {
      if(e.getSource() == btnBack)
      {
         E_ManagerPage mp = new E_ManagerPage();
         mp.setVisible(true);
         mp.pack();
         mp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         mp.setLocationRelativeTo(null);
         this.dispose();
        }
        else if (e.getSource() == btnAssign) 
        {
         if(empList.getSelectedValue() != null && wardList.getSelectedItem() != null  )
         {

         String selectedEmployee = empList.getSelectedValue().toString();
         String selectedItem = (String)wardList.getSelectedItem();
         int wardId = 0 ;
         String beforeFirstSpace = selectedEmployee.split(" ")[0];
         int empIdInt = Integer.parseInt(beforeFirstSpace);

         
          if(selectedItem.equals("1A"))
          {
            wardId = 2;
         }
         else if(selectedItem.equals("1B"))
         {
            wardId = 3;
         }
         else if (selectedItem.equals("2A"))
            {
               wardId = 4;
            }
         else if (selectedItem.equals("2B"))
         {
            wardId = 5;
          } 
          else if (selectedItem.equals("Theatre"))
         {
            wardId = 1;
          } 
                     
         try{
            dbConnection.updateEmployeeWard( wardId, empIdInt);
             AssignToWard rq = new AssignToWard();   //refresh page after req is chosen and moved
   		 rq.setVisible(true);
   		 rq.pack();
   		 rq.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   		 rq.setLocationRelativeTo(null);
   		 this.dispose();

         }catch(Exception e1){
            e1.printStackTrace();
         }
         JOptionPane.showMessageDialog(null,  "Employee has been assigned to a ward", "",   JOptionPane.INFORMATION_MESSAGE);
      }
         else 
            JOptionPane.showMessageDialog(null,  "Please select an item from the list", "",   JOptionPane.INFORMATION_MESSAGE);

        }
        
   
   }
   
   
   
}



