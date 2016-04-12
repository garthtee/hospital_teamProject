package core;

import database.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Group 5 on 3/1/16.
 * Description:
 */
public class AdminPage extends JFrame implements ActionListener {

    private JButton btnAddEmp, btnRemoveEmp, btnUpdateEmp, btnSearchEmp, btnViewEmp, btnLogout;
    private JPanel p1, p2, panelLeft, panelLeftTop;
    private JList<Employee> list;
    private JScrollPane scrollPane = new JScrollPane();
    private DBConnection dbConnection = new DBConnection();
    ArrayList<Employee> employeeList = new ArrayList<>();
    private Employee selectedEmployee;
    private DefaultListModel<Employee> defaultListModel;

    public void createEmployeeModel() {
        employeeList = dbConnection.getEmployees(); // adding database records to employee list
        defaultListModel = new DefaultListModel<>();
        for (Employee employee : employeeList)
            defaultListModel.addElement(employee); // Add employees to Default List Model
    }

    public AdminPage() {

        setLayout(new BorderLayout());
        setTitle("Manage Employees");

        createEmployeeModel();

        // Panel Left Top //
        panelLeftTop = new JPanel();
        JLabel lblTitle = new JLabel("Hospital Admin System");
        lblTitle.setFont(new Font("Sans Serif", Font.ITALIC, 24));
        panelLeftTop.add(lblTitle, BorderLayout.CENTER);

        // Panel 1 //
        p1 = new JPanel();
        list = new JList(defaultListModel); //data has type Object[]
        scrollPane.getViewport().setView(list);
        scrollPane.setPreferredSize(new Dimension(400, 360));
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedEmployee = list.getSelectedValue();
            }
        });
        p1.add(scrollPane, BorderLayout.SOUTH);
        p1.setBorder(BorderFactory.createTitledBorder("Employees"));

        // Panel Left //
        panelLeft = new JPanel();
        panelLeft.add(panelLeftTop, BorderLayout.NORTH);
        panelLeft.add(p1, BorderLayout.CENTER);
        add(panelLeft, BorderLayout.CENTER);

        // Panel 2 //
        p2 = new JPanel();
        p2.setLayout(new GridLayout(6, 1, 10, 10));
        p2.add(btnAddEmp = new JButton("Add Employee"));
        p2.add(btnRemoveEmp = new JButton("Remove Employee"));
        p2.add(btnUpdateEmp = new JButton("Update Employee"));
        p2.add(btnSearchEmp = new JButton("Search Employees"));
        p2.add(btnViewEmp = new JButton("View Employee"));
        p2.add(btnLogout = new JButton("Log Out"));
        p2.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(p2, BorderLayout.EAST);

        // Add action listeners on each button
        btnAddEmp.addActionListener(this);
        btnRemoveEmp.addActionListener(this);
        btnUpdateEmp.addActionListener(this);
        btnSearchEmp.addActionListener(this);
        btnViewEmp.addActionListener(this);
        btnLogout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add Employee":
                CreateEmployee createEmployeeFrame = new CreateEmployee();
                createEmployeeFrame.setVisible(true);
                createEmployeeFrame.pack();
                createEmployeeFrame.setResizable(false);
                createEmployeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                createEmployeeFrame.setLocationRelativeTo(null);
                this.dispose();
                break;
            case "Remove Employee":
                if (selectedEmployee != null) { // if an employee is selected
                    int chosenOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove "
                                    + selectedEmployee.getfName() + "?",
                            "Remove employee", JOptionPane.YES_NO_OPTION);
                    if (chosenOption == 0) {
                        dbConnection.removeEmployee(selectedEmployee);
                        defaultListModel.removeElement(selectedEmployee);
                        break;
                    }
                } else // if no employee selected
                    JOptionPane.showMessageDialog(null, "You must select an employee!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Update Employee":
                if (selectedEmployee != null) {
                    UpdateEmployee updateEmployeeFrame = new UpdateEmployee(selectedEmployee);
                    updateEmployeeFrame.setVisible(true);
                    this.dispose();
                    updateEmployeeFrame.pack();
                    updateEmployeeFrame.setResizable(false);
                    updateEmployeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    updateEmployeeFrame.setLocationRelativeTo(null);
                } else
                    JOptionPane.showMessageDialog(null, "You must select an employee!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "View Employee":
                if (selectedEmployee != null) {
                    ViewEmployee viewEmployee = new ViewEmployee(selectedEmployee);
                    viewEmployee.setVisible(true);
                    viewEmployee.pack();
                    viewEmployee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    viewEmployee.setResizable(false);
                    viewEmployee.setLocationRelativeTo(null);
                } else
                    JOptionPane.showMessageDialog(null, "You must select an employee!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Search Employees":
                SearchEmployeePage searchEmployeePage = new SearchEmployeePage(employeeList);
                searchEmployeePage.setVisible(true);
                searchEmployeePage.setSize(300, 165);
                searchEmployeePage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                searchEmployeePage.setResizable(false);
                searchEmployeePage.setLocationRelativeTo(null);

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