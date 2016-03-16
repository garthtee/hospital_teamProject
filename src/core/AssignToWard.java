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

public class AssignToWard extends JFrame{
	
	private JPanel jPan1, jPan2;
	private JList<Employee> empList;
	private JButton btnSchedule, btnShowAll, btnBack;
	private ArrayList<Employee> data = new ArrayList<>();
	private String[] wards={"1A", "1B", "2A", "2B", "Theatre"};
	private JComboBox wardList=new JComboBox(wards);
	private JScrollPane scrollPane;
	private JLabel lbl1, lbl2;
	private Shift shift1;
	
	public AssignToWard(){
		
		setTitle("Assign to Ward");
		
		setLayout(new BorderLayout());
		
		jPan1=new JPanel();
		
		empList= new JList(data.toArray());
		empList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		empList.setLayoutOrientation(JList.VERTICAL);
		jPan1.setBorder(BorderFactory.createTitledBorder("Employees"));
		add(jPan1, BorderLayout.CENTER);
		scrollPane = new JScrollPane(empList);
		jPan1.add(empList);
        
		
		jPan2=new JPanel();
		jPan2.setLayout(new GridLayout(6, 1));
		
		btnSchedule=new JButton("Assign to Ward");
		btnShowAll=new JButton("Show All Employees");
		btnBack=new JButton("Back");
		
		jPan2.add(wardList);
		jPan2.add(btnSchedule);
		jPan2.add(btnShowAll);
		jPan2.add(lbl1=new JLabel());
		jPan2.add(lbl2=new JLabel());
		jPan2.add(btnBack);
		
		add(jPan1, BorderLayout.CENTER);
		add(jPan2, BorderLayout.EAST);
		
		
	}
	
	public static void main(String []args){
		AssignToWard frame=new AssignToWard();
		frame.setSize(700, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
