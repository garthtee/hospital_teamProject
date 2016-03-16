package core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by garth on 03/03/2016.
 */
public class UpdateEmployee extends JFrame implements ActionListener {

    private JLabel lblFname, lblSname, lblDOB, lblContactNum;
    private JTextField txtFName, txtSName, txtDOB, txtContactNum;
    private JButton btnUpdate, btnCancel;

    public UpdateEmployee(Employee employee) {

        setTitle("Update");
        setLayout(new BorderLayout());

        // Panel 1 //
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 2));
        lblFname = new JLabel("First Name: ");
        txtFName = new JTextField();
        lblSname = new JLabel("Surname: ");
        txtSName = new JTextField();
        lblDOB = new JLabel("DOB: ");
        txtDOB = new JTextField();
        lblContactNum = new JLabel("Contact Number: ");
        txtContactNum = new JTextField();

        // Add components to panel 1 //
        p1.add(lblFname);
        p1.add(txtFName);
        p1.add(lblSname);
        p1.add(txtSName);
        p1.add(lblDOB);
        p1.add(txtDOB);
        p1.add(lblContactNum);
        p1.add(txtContactNum);
        p1.setBorder(new EmptyBorder(10, 10, 10, 10));
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
        txtFName.setText(employee.getfName());
        txtSName.setText(employee.getsName());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Cancel":
                this.dispose();
                break;
            case "Update":
                System.out.print("Update button clicked!");
                break;
        }
    }
}
