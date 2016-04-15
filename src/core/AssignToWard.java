package core;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AssignToWard extends JFrame implements ActionListener{
	
	private JPanel employeePanel, panelBtn, panelA, listPanel;
	private JList<Employee> empList;
   private JList<Employee> assignedList;
	private JButton btnSchedule, btnShowAll, btnBack;
	private ArrayList<Employee> data ;//= new ArrayList<>();
   private ArrayList<Employee> assignedArray = new ArrayList<Employee>();
	private String[] wards={"1A", "1B", "2A", "2B", "Theatre"};
	private JComboBox wardList=new JComboBox(wards);
	private JScrollPane scrollPane, aScrollPane;
	private JLabel lbl1, lbl2;
	private Shift shift1;
	
	public AssignToWard(){
		
		setTitle("Assign to Ward");		
		setLayout(new BorderLayout());
		
      //Unassigned Employees Panel
		employeePanel=new JPanel();		
		empList= new JList();
      data = new ArrayList();
      scrollPane = new JScrollPane();
      scrollPane.getViewport().setView(empList);
      scrollPane.setPreferredSize(new Dimension(250, 520));
		empList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		empList.setLayoutOrientation(JList.VERTICAL);
      employeePanel.add(scrollPane, BorderLayout.SOUTH);
		employeePanel.setBorder(BorderFactory.createTitledBorder("Unassigned Employees"));
		
      
      //Assigned Employees Panel
      panelA = new JPanel();
      assignedList = new JList(); //data has type Object[]
      assignedArray = new ArrayList();
      aScrollPane = new JScrollPane();
      aScrollPane.getViewport().setView(assignedList);
      aScrollPane.setPreferredSize(new Dimension(250, 520));
      assignedList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
      assignedList.setLayoutOrientation(JList.VERTICAL);
      panelA.add(aScrollPane, BorderLayout.SOUTH);
      panelA.setBorder(BorderFactory.createTitledBorder("Assigned Employees"));
      
      
      
      
		//scrollPane = new JScrollPane(empList);
		//employeePanel.add(empList);
              
		//Right panel with buttons
		panelBtn=new JPanel();
		panelBtn.setLayout(new GridLayout(6, 1));		
		btnSchedule=new JButton("Assign to Ward");
      btnBack=new JButton("Back");
      btnBack.addActionListener(this);		
		panelBtn.add(wardList);
		panelBtn.add(btnSchedule);
		//panelBtn.add(btnShowAll);
		panelBtn.add(lbl1=new JLabel());
		panelBtn.add(lbl2=new JLabel());
		panelBtn.add(btnBack);		
		add(employeePanel, BorderLayout.CENTER);
		add(panelBtn, BorderLayout.EAST);
      
      listPanel = new JPanel();
      listPanel.setLayout(new GridLayout(1, 2));
      listPanel.add(employeePanel);
      listPanel.add(panelA);
      add(listPanel, BorderLayout.CENTER);
      
		
		
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
         //System.out.print("button pressed");
         mp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         mp.setLocationRelativeTo(null);
         this.dispose();
        }
        else if (e.getSource() == btnSchedule) 
        {
         JOptionPane.showMessageDialog(null,  "Employee has been assigned", "",   JOptionPane.INFORMATION_MESSAGE);
        }
   
   }
   
   
   
}



