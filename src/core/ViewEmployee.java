package core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/**
 * Created by Group 5 on 3/3/2016.
 */
public class ViewEmployee extends JFrame implements ActionListener {

    private JPasswordField txtPassword;

    public static void getViewEmployeePage(Employee selectedEmployee) {
        ViewEmployee viewEmployee = new ViewEmployee(selectedEmployee);
        viewEmployee.setVisible(true);
        viewEmployee.pack();
        viewEmployee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewEmployee.setResizable(false);
        viewEmployee.setLocationRelativeTo(null);
    }

    public ViewEmployee(Employee employee) {

        // Creating variables
        JLabel lblID, lblFname, lblSname, lblDOB, lblContactNum, lblEmail, lblNumHolidays,
                lblContractHours, lblSalary,lblOnHoliday, lblOffSick, lblWard_ID, lblPassword, lblPrivilege, lblEmployeeType;
        JTextField txtID, txtFName, txtSName, txtDOB, txtContactNum, txtEmail, txtNumHolidays,
                txtContractHours, txtSalary, txtOnHoliday, txtOffSick, txtWard_ID, txtPrivilege, txtEmployeeType;
        JButton btnOk;

        setTitle("View");
        setLayout(new BorderLayout());

        // Panel 1 //
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(15, 2));
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
        txtNumHolidays = new JTextField();
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
        txtPassword = new JPasswordField();
        lblEmployeeType = new JLabel("Employee Type: ");
        txtEmployeeType = new JTextField();
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
        p1.add(txtNumHolidays);
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
        p1.add(lblEmployeeType);
        p1.add(txtEmployeeType);
        p1.add(lblPrivilege);
        p1.add(txtPrivilege);
        p1.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(p1, BorderLayout.NORTH);

        // Panel 2 //
        JPanel p2 = new JPanel();
        btnOk = new JButton("Ok");
        p2.add(btnOk);
        add(p2, BorderLayout.CENTER);

        // Add button action listeners
        btnOk.addActionListener(this);

        // Setting text to employee details
        txtID.setText(String.valueOf(employee.getEmp_ID()));
        txtFName.setText(employee.getfName());
        txtSName.setText(employee.getsName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        txtDOB.setText(sdf.format(employee.getDOB().getTime()));
        txtContactNum.setText(employee.getContactNum());
        txtEmail.setText(employee.getEmail());
        txtNumHolidays.setText(String.valueOf(employee.getNumHolidays()));
        txtContractHours.setText(String.valueOf(employee.getContractHours()));
        txtSalary.setText(String.valueOf(employee.getSalary()));
        txtOnHoliday.setText(String.valueOf(employee.isOnHoliday()));
        txtOffSick.setText(String.valueOf(employee.isOffSick()));
        txtWard_ID.setText(String.valueOf(employee.getWard_ID()));
        txtPassword.setText(employee.getPassword());
        txtEmployeeType.setText(employee.getEmployee_type());
        txtPrivilege.setText(employee.getPrivilege());

        // Making text boxes uneditable
        txtID.setEditable(false);
        txtFName.setEditable(false);
        txtSName.setEditable(false);
        txtDOB.setEditable(false);
        txtContactNum.setEditable(false);
        txtEmail.setEditable(false);
        txtNumHolidays.setEditable(false);
        txtContractHours.setEditable(false);
        txtSalary.setEditable(false);
        txtOnHoliday.setEditable(false);
        txtOffSick.setEditable(false);
        txtWard_ID.setEditable(false);
        txtPassword.setEditable(false);
        txtEmployeeType.setEditable(false);
        txtPrivilege.setEditable(false);

        // Presses ok button on enter key press
        this.getRootPane().setDefaultButton(btnOk);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ok":
                this.dispose();
                break;
        }
    }
}
