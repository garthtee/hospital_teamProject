package core;

import database.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Group 5 on 03/03/2016.
 */
public class UpdateEmployee extends JFrame implements ActionListener {


    private JButton btnUpdate, btnCancel;
    private JLabel lblID, lblFname, lblSname, lblDOB, lblContactNum, lblEmail, lblNumHolidays,
            lblContractHours, lblSalary, lblOnHoliday, lblOffSick, lblWard_ID, lblPassword, lblPrivilege;
    private JTextField txtID, txtFName, txtSName, txtDOB, txtContactNum, txtEmail, txtNumHoldiays,
            txtContractHours, txtSalary, txtOnHoliday, txtOffSick, txtWard_ID, txtPassword, txtPrivilege;
    private JComboBox<String> jcbType;
    private String selectedPrivilege;
    private EmailValidator emailValidator = new EmailValidator();

    public UpdateEmployee(Employee employee) {

        setTitle("Update");
        setLayout(new BorderLayout());

        // Panel 1 //
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(14, 2));
        lblID = new JLabel("ID: ");
        txtID = new JTextField();
        lblFname = new JLabel("First Name: ");
        txtFName = new JTextField();
        lblSname = new JLabel("Surname: ");
        txtSName = new JTextField();
        lblDOB = new JLabel("DOB: ");
        txtDOB = new JTextField();
        lblContactNum = new JLabel("Contact Number: ");
        txtContactNum = new JTextField();
        lblEmail = new JLabel("Email: ");
        txtEmail = new JTextField();
        lblNumHolidays = new JLabel("Num Holidays: ");
        txtNumHoldiays = new JTextField();
        lblContractHours = new JLabel("Contract Hours: ");
        txtContractHours = new JTextField();
        lblSalary = new JLabel("Salary: ");
        txtSalary = new JTextField();
        lblOnHoliday = new JLabel("On Holiday: ");
        txtOnHoliday = new JTextField();
        lblOffSick = new JLabel("Off Sick: ");
        txtOffSick = new JTextField();
        lblWard_ID = new JLabel("Ward ID: ");
        txtWard_ID = new JTextField();
        lblPassword = new JLabel("Password: ");
        txtPassword = new JTextField();
        lblPrivilege = new JLabel("Privilege: ");
        txtPrivilege = new JTextField();

        // Add components to panel 1 //
        p1.add(lblID);
        p1.add(txtID);
        p1.add(lblFname);
        p1.add(txtFName);
        p1.add(lblSname);
        p1.add(txtSName);
        p1.add(lblDOB);
        p1.add(txtDOB);
        p1.add(lblContactNum);
        p1.add(txtContactNum);
        p1.add(lblEmail);
        p1.add(txtEmail);
        p1.add(lblNumHolidays);
        p1.add(txtNumHoldiays);
        p1.add(lblContractHours);
        p1.add(txtContractHours);
        p1.add(lblSalary);
        p1.add(txtSalary);
        p1.add(lblOnHoliday);
        p1.add(txtOnHoliday);
        p1.add(lblOffSick);
        p1.add(txtOffSick);
        p1.add(lblWard_ID);
        p1.add(txtWard_ID);
        p1.add(lblPassword);
        p1.add(txtPassword);

        p1.add(lblPrivilege);
//        p1.add(txtPrivilege);
        String[] types = {"employee", "admin", "manager"};
        jcbType = new JComboBox<>(types);
        jcbType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPrivilege = String.valueOf(jcbType.getSelectedItem());
            }
        });
        p1.add(jcbType);

        p1.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(p1, BorderLayout.NORTH);

        // Panel 2 //
        JPanel p2 = new JPanel();
        btnCancel = new JButton("Cancel");
        btnUpdate = new JButton("Update");
        p2.add(btnCancel);
        p2.add(btnUpdate);
        add(p2, BorderLayout.CENTER);

        // Add button action listeners
        btnCancel.addActionListener(this);
        btnUpdate.addActionListener(this);

        // Setting text to employee details
        txtID.setText(String.valueOf(employee.getEmp_ID()));
        txtFName.setText(employee.getfName());
        txtSName.setText(employee.getsName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        txtDOB.setText(sdf.format(employee.getDOB().getTime()));
        txtContactNum.setText(employee.getContactNum());
        txtEmail.setText(employee.getEmail());
        txtNumHoldiays.setText(String.valueOf(employee.getNumHolidays()));
        txtContractHours.setText(String.valueOf(employee.getContractHours()));
        txtSalary.setText(String.valueOf(employee.getSalary()));
        txtOnHoliday.setText(String.valueOf(employee.isOnHoliday()));
        txtOffSick.setText(String.valueOf(employee.isOffSick()));
        txtWard_ID.setText(String.valueOf(employee.getWard_ID()));
        txtPassword.setText(employee.getPassword());
        jcbType.setSelectedItem(employee.getPrivilege());
//        txtPrivilege.setText(employee.getPrivilege());

        // Setting ID to be uneditable
        txtID.setEditable(false);

        // Presses update button on enter key press
        this.getRootPane().setDefaultButton(btnUpdate);

    }

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
                try {

                    try {
                        DateValidator dateValidator = new DateValidator();
                        dateValidator.setYear(txtDOB.getText());
                        dateValidator.setMonth(txtDOB.getText());
                        dateValidator.setDay(txtDOB.getText());
                    } catch (StringIndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid date.  \n\nExample format: yyyy-mm-dd\n", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, "Date error.  \n\nExample format: yyyy-mm-dd\n", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (txtFName.getText().length() <= 1 || txtSName.getText().length() <= 1 || txtFName.getText().length() < 1) {
                        JOptionPane.showMessageDialog(null, "Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (txtContactNum.getText().length() < 7) {
                        JOptionPane.showMessageDialog(null, "Invalid contact number.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (txtEmail.getText().length() <= 3 || txtEmail.getText().length() > 100 || !txtEmail.getText().contains("@")
                            || !txtEmail.getText().contains(".") || !emailValidator.validateEmail(txtEmail.getText())) {
                        JOptionPane.showMessageDialog(null, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (!txtDOB.getText().contains("-") || txtDOB.getText().length() < 10 || txtDOB.getText().length() >= 11) {
                        JOptionPane.showMessageDialog(null, "Date must contain '-' \n\n Example format: yyyy-mm-dd\n", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (Double.valueOf(txtNumHoldiays.getText()) > 40) {
                        JOptionPane.showMessageDialog(null, "Invalid contact number.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (Integer.valueOf(txtWard_ID.getText()) > 10) {
                        JOptionPane.showMessageDialog(null, "Invalid ward!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (selectedPrivilege == null) {
                        selectedPrivilege = "employee";
                    } else if (txtPassword.getText().length() < 8 || txtPassword.getText().length() > 30) {
                        JOptionPane.showMessageDialog(null, "Invalid password.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
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
                        employee.setPrivilege(selectedPrivilege);
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
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Cannot have a letter where a number is expected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
        }
    }
}
