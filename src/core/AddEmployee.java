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
 * Created by Group 5 on 3/2/2016.
 */
public class AddEmployee extends JFrame implements ActionListener {

    private JLabel lblFname, lblSname, lblDOB, lblContactNum, lblEmail, lblNumHolidays, lblContractHours, lblSalary, lblWard_ID,
            lblPassword, lblPrivilege, lblEmployeeType;
    private JTextField txtFName, txtSName, txtDOB, txtContactNum, txtEmail, txtNumHoldiays, txtContractHours, txtSalary, txtWard_ID,
            txtPassword, txtPrivilege, txtEmployeeType;
    private JButton btnCreate, btnCancel;
    private JComboBox<String> jcbType;
    private String selectedPrivilege;

    public AddEmployee() {

        setTitle("Create");
        setLayout(new BorderLayout());

        // Panel 1 //
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(12, 2));
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
        lblWard_ID = new JLabel("Ward ID: ");
        txtWard_ID = new JTextField();
        lblPassword = new JLabel("Password: ");
        txtPassword = new JTextField();
        lblEmployeeType = new JLabel("Employee Type: ");
        txtEmployeeType = new JTextField();
        lblPrivilege = new JLabel("Privilege: ");
        txtPrivilege = new JTextField();


        // Add components to panel 1 //
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
        p1.add(lblWard_ID);
        p1.add(txtWard_ID);
        p1.add(lblPassword);
        p1.add(txtPassword);
        p1.add(lblEmployeeType);
        p1.add(txtEmployeeType);
        p1.add(lblPrivilege);

        // Adding privilege as JComboBox
        String[] types = {"Employee", "Admin", "Manager"};
        jcbType = new JComboBox<>(types);
        jcbType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPrivilege = String.valueOf(jcbType.getSelectedItem()).toLowerCase();
            }
        });
        p1.add(jcbType);

        p1.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(p1, BorderLayout.NORTH);

        // Panel 2 //
        JPanel p2 = new JPanel();
        btnCancel = new JButton("Cancel");
        btnCreate = new JButton("Create");
        p2.add(btnCancel);
        p2.add(btnCreate);
        add(p2, BorderLayout.CENTER);

        // Add button action listeners
        btnCancel.addActionListener(this);
        btnCreate.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Create":
                if(txtEmail.getText().length() <= 3 || !txtEmail.getText().contains("@")) {
                    JOptionPane.showMessageDialog(null, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if(txtFName.getText().length() <= 1 || txtSName.getText().length() <= 1 || txtFName.getText().length() < 1) {
                    JOptionPane.showMessageDialog(null, "Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if(txtDOB.getText().length() < 10) {
                    JOptionPane.showMessageDialog(null, "Please enter valid date.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if(!txtDOB.getText().contains("-")) {
                    JOptionPane.showMessageDialog(null, "Date must contain '-' \n\n Example format: yyyy-mm-dd", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if(txtContactNum.getText().length() < 7) {
                    JOptionPane.showMessageDialog(null, "Invalid contact number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }  else {
                    Calendar calendar = Calendar.getInstance();
                    DBConnection dbConnection = new DBConnection();
                    try { // try parsing the string to a Calendar object
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.setTime(dateFormat.parse(txtDOB.getText()));
                    } catch (ParseException exception) {
                        exception.printStackTrace();
                    }
                    dbConnection.createEmployee(txtFName.getText(), txtSName.getText(), calendar, txtContactNum.getText(),
                            txtEmail.getText(), Double.valueOf(txtNumHoldiays.getText()), Double.valueOf(txtContractHours.getText()),
                            Double.valueOf(txtSalary.getText()), Integer.valueOf(txtWard_ID.getText()),
                            txtPassword.getText(), selectedPrivilege, txtEmployeeType.getText());
                    this.dispose();
                    AdminPage adminPage = new AdminPage(-1);
                    adminPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    adminPage.setSize(600, 500);
                    adminPage.setLocationRelativeTo(null);
                    adminPage.setVisible(true);
                }
                break;
            case "Cancel":
                this.dispose(); // disposes the create employee frame
                AdminPage adminPage = new AdminPage(-1);
                adminPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                adminPage.setSize(600, 500);
                adminPage.setLocationRelativeTo(null);
                adminPage.setVisible(true);
                break;
        }
    }
}