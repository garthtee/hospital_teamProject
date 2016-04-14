package core;

import database.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Garth Toland on 13/04/2016.
 * Description:
 */
public class UpdateWard extends JFrame implements ActionListener {

    private JButton btnUpdate, btnCancel;
    private JLabel lblID, lblWardType, lblReqNurses, lblReqDoctors;
    private JTextField txtID, txtWardType, txtReqNurses, txtReqDoctors;
    private JComboBox<String> jcbType;
    private String selectedPrivilege;
    private EmailValidator emailValidator = new EmailValidator();

    public static void getUpdateWard(Ward ward) {
        UpdateWard updateWard = new UpdateWard(ward);
        updateWard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateWard.pack();
        updateWard.setResizable(false);
        updateWard.setLocationRelativeTo(null);
        updateWard.setVisible(true);
    }

    public UpdateWard(Ward ward) {

        setTitle("Update Ward");
        setLayout(new BorderLayout());

        // Panel 1 //
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 2));
        lblID = new JLabel("ID: ");
        txtID = new JTextField();
        lblWardType = new JLabel("Ward Type: ");
        txtWardType = new JTextField();
        lblReqNurses = new JLabel("Required Nurses: ");
        txtReqNurses = new JTextField();
        lblReqDoctors = new JLabel("Required Doctors: ");
        txtReqDoctors = new JTextField();

        // Add components to panel 1 //
        p1.add(lblID);
        p1.add(txtID);
        p1.add(lblWardType);
        p1.add(txtWardType);
        p1.add(lblReqNurses);
        p1.add(txtReqNurses);
        p1.add(lblReqDoctors);
        p1.add(txtReqDoctors);

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
        txtID.setText(String.valueOf(ward.getWard_ID()));
        txtWardType.setText(ward.getWardType());
        txtReqNurses.setText(String.valueOf(ward.getReqNurses()));
        txtReqDoctors.setText(String.valueOf(ward.getReqDoctors()));

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
                WardMainPage wardMainPage = new WardMainPage();
                wardMainPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                wardMainPage.setSize(600, 500);
                wardMainPage.setLocationRelativeTo(null);
                wardMainPage.setVisible(true);
                break;
            case "Update":
                // Checking if text has been entered to all TextFields
                if(txtWardType.getText().equals("") || txtReqNurses.getText().equals("") || txtReqDoctors.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if(txtWardType.getText().equals("") || txtReqNurses.getText().equals("") || txtReqDoctors.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if(Integer.valueOf(txtReqNurses.getText()) > 25 || Integer.valueOf(txtReqDoctors.getText()) > 25) {
                    JOptionPane.showMessageDialog(null, "Required staff for this ward is too high." +
                            "\n\nPlease enter value less than 25.\n", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    boolean isString = false;
                    try {
                        Integer.parseInt(txtWardType.getText());
                    } catch (NumberFormatException ex) { // not an int if throws exception
                        isString = true;
                    }
                    if (!isString) { // if wardtype.getText() is a number
                        JOptionPane.showMessageDialog(null, "Ward type should not contain a number.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Do not continue
                    }
                    DBConnection dbConnection = new DBConnection();
                    Ward ward = new Ward();
                    ward.setWard_ID(Integer.valueOf(txtID.getText()));
                    ward.setWardType(txtWardType.getText());
                    ward.setReqNurses(Integer.valueOf(txtReqNurses.getText()));
                    ward.setReqDoctors(Integer.valueOf(txtReqDoctors.getText()));
                    dbConnection.updateWard(ward);
                    // Close update ward page and open ward main page..
                    this.dispose();
                    WardMainPage.getWardMainPage();
                }
                break;
        }
    }
}
