package core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchEmployeePage extends JFrame implements ActionListener {

    private JTextField txtSearch;
    private String selectedItem;
    private ArrayList<Employee> employeeList = new ArrayList<>();
    private JComboBox<String> jcbSearch;
    ArrayList<Employee> tempList = new ArrayList<>();

    public static void getSearchEmployeePage(ArrayList<Employee> employeeListIn) {
        SearchEmployeePage searchEmployeePage = new SearchEmployeePage(employeeListIn);
        searchEmployeePage.setVisible(true);
        searchEmployeePage.pack();
        searchEmployeePage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchEmployeePage.setResizable(false);
        searchEmployeePage.setLocationRelativeTo(null);
    }

    public SearchEmployeePage(ArrayList<Employee> employeeList) {

        // Creating variables
        JButton btnSearch, btnCancel;
        JPanel panelTop, panelBottom;
        JLabel lblEmpName;

        this.employeeList = employeeList;

        setTitle("Search Employees");
        setLayout(new BorderLayout());

        // Assign variables
        lblEmpName = new JLabel("Search criteria: ");
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

        // adding style // top / left / bottom / right
        panelTop.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelBottom.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add actionListeners
        btnCancel.addActionListener(this);
        btnSearch.addActionListener(this);

        // Presses search button on enter key press
        this.getRootPane().setDefaultButton(btnSearch);
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
                                ViewEmployee viewEmployee = new ViewEmployee(employee);
                                viewEmployee.setVisible(true);
                                viewEmployee.pack();
                                viewEmployee.setResizable(false);
                                viewEmployee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                viewEmployee.setLocationRelativeTo(null);
                                jcbSearch.setSelectedIndex(0);
                                this.dispose();
                                return;
                            }
                        } catch (NumberFormatException exception) {
                            JOptionPane.showMessageDialog(null, "You must enter a number", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    if(!employeeFound) { // if employee not found..
                        employeeFound=false;
                        JOptionPane.showMessageDialog(null, "No employee found! \n\n"
                                + "Search criteria being:\n"
                                + txtSearch.getText(), "Error", JOptionPane.ERROR_MESSAGE);
                        jcbSearch.setSelectedIndex(0);
                    }
                }
                if (selectedItem.equals("First Name")) {
                    tempList.clear();
                    boolean employeeFound = false;
                    for (int i =0; i <= employeeList.size()-1; i++) {
                        if (employeeList.get(i).getfName().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                            employeeFound=true;
                            tempList.add(employeeList.get(i));
                        }
                    }
                    if (tempList.size() > 0) {
                        ViewEmployees viewEmployees = new ViewEmployees(tempList, txtSearch.getText());
                        viewEmployees.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        viewEmployees.pack();
                        viewEmployees.setLocationRelativeTo(null);
                        viewEmployees.setVisible(true);
                        viewEmployees.setResizable(false);
                        jcbSearch.setSelectedIndex(0);
                        this.dispose();
                    }
                    if(!employeeFound) { // if employee not found..
                        JOptionPane.showMessageDialog(null, "No employee found! \n\n"
                                + "Search criteria being:\n"
                                + txtSearch.getText(), "Error", JOptionPane.ERROR_MESSAGE);
                        jcbSearch.setSelectedIndex(0);
                    }
                }
                if (selectedItem.equals("Surname")) {
                    tempList.clear();
                    boolean employeeFound = false;
                    for (int i =0; i <= employeeList.size()-1; i++) {
                        if (employeeList.get(i).getsName().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                            employeeFound=true;
                            tempList.add(employeeList.get(i));
                        }
                    }
                    if (tempList.size() > 0) {
                        ViewEmployees viewEmployees = new ViewEmployees(tempList, txtSearch.getText());
                        viewEmployees.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        viewEmployees.pack();
                        viewEmployees.setResizable(false);
                        viewEmployees.setLocationRelativeTo(null);
                        viewEmployees.setVisible(true);
                        jcbSearch.setSelectedIndex(0);
                        this.dispose();
                    }
                    if(!employeeFound) { // if employee not found..
                        JOptionPane.showMessageDialog(null, "No employee found! \n\n"
                                + "Search criteria being:\n"
                                + txtSearch.getText(), "Error", JOptionPane.ERROR_MESSAGE);
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