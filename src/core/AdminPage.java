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
 * Description: Administration page for Employees.
 */
public class AdminPage extends JFrame implements ActionListener {

    private JList<Employee> list;
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

    public static void getAdminPage(int id) {
        AdminPage adminPage = new AdminPage(id);
        adminPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminPage.setSize(600, 500);
        adminPage.setResizable(false);
        adminPage.setLocationRelativeTo(null);
        adminPage.setVisible(true);
    }

    public AdminPage(int emp_ID_In) {

        // Creating variables
        JButton btnAddEmp, btnRemoveEmp, btnUpdateEmp, btnSearchEmp, btnViewEmp, btnWards, btnLogout;
        JPanel p1, p2, panelLeft, panelLeftTop;
        JScrollPane scrollPane = new JScrollPane();

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
        list.setCellRenderer(new employeeCellRenderer());
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
        p2.setLayout(new GridLayout(7, 1, 10, 10));
        p2.add(btnAddEmp = new JButton("Add Employee"));
        p2.add(btnRemoveEmp = new JButton("Remove Employee"));
        p2.add(btnUpdateEmp = new JButton("Update Employee"));
        p2.add(btnSearchEmp = new JButton("Search Employees"));
        p2.add(btnViewEmp = new JButton("View Employee"));
        p2.add(btnWards = new JButton("Wards page"));
        p2.add(btnLogout = new JButton("Log Out"));
        p2.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(p2, BorderLayout.EAST);

        // Add action listeners on each button
        btnAddEmp.addActionListener(this);
        btnRemoveEmp.addActionListener(this);
        btnUpdateEmp.addActionListener(this);
        btnSearchEmp.addActionListener(this);
        btnViewEmp.addActionListener(this);
        btnWards.addActionListener(this);
        btnLogout.addActionListener(this);
    }

    // editing list items
    class employeeCellRenderer extends JLabel implements ListCellRenderer {
        private final Color SELECTED_BACKGROUND_COLOR = new Color(0, 128, 128);

        public employeeCellRenderer() {
            setOpaque(true);
            setIconTextGap(12);
        }

        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            Employee employee = (Employee) value;
            setText("Employee " +employee.getEmp_ID() + ": " + employee.getfName()
                + " " + employee.getsName());
            this.setFont(new Font("Sans Serif", Font.PLAIN, 20));

            if (isSelected) {
                setBackground(SELECTED_BACKGROUND_COLOR);
                setForeground(Color.white);
            } else {
                setBackground(Color.white);
                setForeground(Color.black);
            }
            return this;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add Employee":
                AddWard.getAddWard();
                this.dispose();
                break;
            case "Remove Employee":
                if (selectedEmployee != null) { // if an employee is selected
                    int chosenOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove "
                                    + selectedEmployee.getfName() + "?",
                            "Remove employee", JOptionPane.YES_NO_OPTION);
                    if (chosenOption == 0) {
                        boolean employeeRemoved = false;
                        employeeRemoved = dbConnection.removeEmployee(selectedEmployee);
                        if(employeeRemoved)
                            defaultListModel.removeElement(selectedEmployee);
                        else
                            JOptionPane.showMessageDialog(null, "Employee has shifts assigned.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                } else // if no employee selected
                    JOptionPane.showMessageDialog(null, "You must select an employee!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Update Employee":
                if (selectedEmployee != null) {
                    UpdateEmployee.getUpdateEmployeePage(selectedEmployee);
                    this.dispose();
                } else
                    JOptionPane.showMessageDialog(null, "You must select an employee!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "View Employee":
                if (selectedEmployee != null) {
                    ViewEmployee.getViewEmployeePage(selectedEmployee);
                } else
                    JOptionPane.showMessageDialog(null, "You must select an employee!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Search Employees":
                SearchEmployeePage.getSearchEmployeePage(employeeList);
                break;
            case "Wards page":
                this.dispose();
                WardMainPage.getWardMainPage();
                break;
            case "Log Out":
                this.dispose();
                LogInForm.getLoginPage();
                break;
        }
    }
}