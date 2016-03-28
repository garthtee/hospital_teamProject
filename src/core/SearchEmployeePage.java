package core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchEmployeePage extends JFrame implements ActionListener {

	private JLabel lblEmpName;
	private JTextField txtSearch;
	private JButton btnSearch, btnCancel;
	private JPanel panelTop, panelBottom;
	private ArrayList<Employee> employeeArrayList = new ArrayList<>();
	
	public SearchEmployeePage(ArrayList<Employee> employeeList) {
		employeeArrayList = employeeList;

		setTitle("Search Employees");
		setLayout(new BorderLayout());
		
		// Assign variables
		lblEmpName = new JLabel("Employee Name: ");
		txtSearch = new JTextField(12);
		btnCancel = new JButton("Cancel");
		btnSearch = new JButton("Search");
		
		// panelTop
		panelTop = new JPanel();
		panelTop.setLayout(new BorderLayout());
		panelTop.add(lblEmpName, BorderLayout.CENTER);
		panelTop.add(txtSearch, BorderLayout.EAST);
		add(panelTop, BorderLayout.CENTER);
		
		// panelBottom
		panelBottom = new JPanel();
		panelBottom.setLayout(new FlowLayout());
		panelBottom.add(btnCancel);
		panelBottom.add(btnSearch);
		add(panelBottom, BorderLayout.SOUTH);
		
		// adding style // top / left / bottom / rights
		panelTop.setBorder(new EmptyBorder(20, 10, 5, 10));
		panelBottom.setBorder(new EmptyBorder(5, 10, 10, 10));
		
		// Add actionListeners
		btnCancel.addActionListener(this);
		btnSearch.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Cancel":
			this.dispose();
			break;
		case "Search": // only searches by first name
            for(Employee employee : employeeArrayList) {
                if(employee.getfName().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                    ViewEmployee sp = new ViewEmployee(txtSearch.getText(), employee);
                    sp.setVisible(true);
                    sp.pack();
                    sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    sp.setLocationRelativeTo(null);
                }
            }
			break;
		}
	}
	
//	// Main Method //
//    public static void main(String[] args) {
//		SearchEmployeePage sp = new SearchEmployeePage();
//	    sp.setVisible(true);
//	    sp.pack();
//	    sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	    sp.setLocationRelativeTo(null);
//    }

}
