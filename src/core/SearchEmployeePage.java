package core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchEmployeePage extends JFrame implements ActionListener {

    private JLabel lblEmpName, lblSpace;
    private JTextField txtSearch;
    private JButton btnSearch, btnCancel;
    private JPanel panelTop, panelBottom;
    private String selectedItem;
    private ArrayList<Employee> employeeList = new ArrayList<>();
    private ArrayList<Employee> tempList = new ArrayList<>();
    private JComboBox<String> jcbSearch;

    public SearchEmployeePage(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;

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

        String[] searches = {"Search by:", "ID", "First Name", "Surname"};
        jcbSearch = new JComboBox<>(searches);
        jcbSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedItem = String.valueOf(jcbSearch.getSelectedItem());
            }
        });
        panelTop.add(jcbSearch, BorderLayout.NORTH);
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
            case "Search":
                if (selectedItem.equals("Search by:")) {
                    JOptionPane.showMessageDialog(null, "You must select: \n" +
                            "ID \nFirst Name \nSurname", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if (selectedItem.equals("ID")) {
                    boolean employeeFound = false;
                    for (Employee employee : employeeList) {
                        try {
                            if (employee.getEmp_ID() == Integer.valueOf(txtSearch.getText())) {
                                employeeFound=true;
                                ViewEmployee sp = new ViewEmployee(employee);
                                sp.setVisible(true);
                                sp.pack();
                                sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                sp.setLocationRelativeTo(null);
                                jcbSearch.setSelectedIndex(0);
                                return;
                            }
                        } catch (NumberFormatException exception) {
                            JOptionPane.showMessageDialog(null, "You must enter a number", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    if(!employeeFound) { // if employee not found..
                        employeeFound=false;
                        JOptionPane.showMessageDialog(null, "No employee found!", "Error", JOptionPane.ERROR_MESSAGE);
                        jcbSearch.setSelectedIndex(0);
                    }
                }
                if (selectedItem.equals("First Name")) {
                    boolean employeeFound = false;
                    for (Employee employee : employeeList) {
                        if (employee.getfName().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                            employeeFound=true;

                            ViewEmployee sp = new ViewEmployee(employee);
                            sp.setVisible(true);
                            sp.pack();
                            sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            sp.setLocationRelativeTo(null);
                            jcbSearch.setSelectedIndex(0);
                        }
                    }
                    if(!employeeFound) { // if employee not found..
                        JOptionPane.showMessageDialog(null, "No employee found!", "Error", JOptionPane.ERROR_MESSAGE);
                        jcbSearch.setSelectedIndex(0);
                    }
                }
                if (selectedItem.equals("Surname")) {
                    boolean employeeFound = false;
                    for (Employee employee : employeeList) {
                        if (employee.getsName().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                            employeeFound=true;
                            ViewEmployee sp = new ViewEmployee(employee);
                            sp.setVisible(true);
                            sp.pack();
                            sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            sp.setLocationRelativeTo(null);
                            jcbSearch.setSelectedIndex(0);
                        }
                    }
                    if(!employeeFound) { // if employee not found..
                        JOptionPane.showMessageDialog(null, "No employee found!", "Error", JOptionPane.ERROR_MESSAGE);
                        jcbSearch.setSelectedIndex(0);
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Something went wrong!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }

    }
}