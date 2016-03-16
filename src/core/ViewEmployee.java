package core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by garth on 3/3/2016.
 */
public class ViewEmployee extends JFrame implements ActionListener {

    private JLabel lblFname, lblSname, lblDOB, lblContactNum;
    private JTextField txtFName, txtSName, txtDOB, txtContactNum;
    private JButton btnOk;

    public ViewEmployee(String employeeName) {
    	
    	/*
    	 * (String fName, String sName, Date dOB, String contactNum, String email, int numHolidays,
			int contractHours, double salary)
    	 */

        setTitle("View");
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
        btnOk = new JButton("Ok");
        p2.add(btnOk);
        add(p2, BorderLayout.CENTER);

        // Add button action listeners
        btnOk.addActionListener(this);

        // Making text boxes uneditable
        txtFName.setEditable(false);
        txtSName.setEditable(false);
        txtDOB.setEditable(false);
        txtContactNum.setEditable(false);
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
