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
public class CreateEmployee extends JFrame implements ActionListener {

    private JLabel lblFname, lblSname, lblDOB, lblContactNum, lblEmail, lblNumHolidays, lblContractHours, lblSalary, lblWard_ID,
            lblPassword, lblPrivilege;
    private JTextField txtFName, txtSName, txtDOB, txtContactNum, txtEmail, txtNumHoldiays, txtContractHours, txtSalary, txtWard_ID,
            txtPassword, txtPrivilege;
    private JButton btnCreate, btnCancel;
    private JComboBox<String> jcbType;
    private String selectedPrivilege;
    private EmailValidator emailValidator = new EmailValidator();

    public CreateEmployee() {

        setTitle("Create");
        setLayout(new BorderLayout());

        // Panel 1 //
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(11, 2));
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
        p1.add(lblPrivilege);

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
        btnCreate = new JButton("Create");
        p2.add(btnCancel);
        p2.add(btnCreate);
        add(p2, BorderLayout.CENTER);

        // Add button action listeners
        btnCancel.addActionListener(this);
        btnCreate.addActionListener(this);

        // Presses create button on enter key press
        this.getRootPane().setDefaultButton(btnCreate);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Create":

                try {
                    try {
                        DateValidator dateValidator = new DateValidator();
                        dateValidator.setYear(txtDOB.getText());
                        dateValidator.setMonth(txtDOB.getText());
                        dateValidator.setDay(txtDOB.getText());
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Date error.  \n\nExample format: yyyy-mm-dd\n", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (txtFName.getText().length() <= 1 || txtSName.getText().length() <= 1 || txtFName.getText().length() < 1) {
                        JOptionPane.showMessageDialog(null, "Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (txtEmail.getText().length() <= 3 || txtEmail.getText().length() > 100 || !txtEmail.getText().contains("@")
                            || !txtEmail.getText().contains(".") || !emailValidator.validateEmail(txtEmail.getText())) {
                        JOptionPane.showMessageDialog(null, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (!txtDOB.getText().contains("-") || txtDOB.getText().length() < 10 || txtDOB.getText().length() >= 11) {
                        JOptionPane.showMessageDialog(null, "Date must contain '-' \n\n Example format: yyyy-mm-dd\n", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (txtContactNum.getText().length() < 7) {
                        JOptionPane.showMessageDialog(null, "Invalid contact number.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (Double.valueOf(txtNumHoldiays.getText()) > 40) {
                        JOptionPane.showMessageDialog(null, "Invalid contact number.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (Integer.valueOf(txtWard_ID.getText()) > 10) {
                        JOptionPane.showMessageDialog(null, "Invalid ward!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (selectedPrivilege == null) {
                        selectedPrivilege = "employee";
                    } else if(txtPassword.getText().length() < 8 || txtPassword.getText().length() > 30) {
                        JOptionPane.showMessageDialog(null, "Invalid password.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
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
                                txtPassword.getText(), selectedPrivilege);
                        this.dispose();
                        AdminPage adminPage = new AdminPage();
                        adminPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        adminPage.setSize(600, 500);
                        adminPage.setLocationRelativeTo(null);
                        adminPage.setVisible(true);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Cannot have a letter where a number is expected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Cancel":
                this.dispose(); // disposes the create employee frame
                AdminPage adminPage = new AdminPage();
                adminPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                adminPage.setSize(600, 500);
                adminPage.setLocationRelativeTo(null);
                adminPage.setVisible(true);
                break;
        }
    }

//    private Calendar stringToCalendarConverter(String dobIn) {
//        try {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = simpleDateFormat.parse(dobIn);
//            Calendar calendar = new GregorianCalendar();
//            calendar.setTime(date);
//            return calendar;
//        } catch (ParseException e) {
//            return null;
//        }
//    }
}