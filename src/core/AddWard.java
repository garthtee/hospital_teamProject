package core;

import database.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Garth Toland on 13/04/2016.
 * Description: Adds a ward to the database.
 */
public class AddWard extends JFrame implements ActionListener {

    private JLabel lblWardType, lblReqNurses, lblReqDoctors, lblScheduled;
    private JTextField txtWardType, txtReqNurses, txtReqDoctors, txtScheduled;
    private JButton btnAdd, btnCancel;

    public static void getAddWard() {
        AddWard addWard = new AddWard();
        addWard.setVisible(true);
        addWard.pack();
        addWard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWard.setResizable(false);
        addWard.setLocationRelativeTo(null);
    }

    public AddWard() {

        setTitle("Create");
        setLayout(new BorderLayout());

        // Panel 1 //
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 2));
        lblWardType = new JLabel("Ward Type: ");
        txtWardType = new JTextField();
        lblReqNurses = new JLabel("Required Nurses: ");
        txtReqNurses = new JTextField();
        lblReqDoctors = new JLabel("Required Doctors: ");
        txtReqDoctors = new JTextField();
        lblScheduled = new JLabel("Scheduled: ");
        txtScheduled = new JTextField();

        // Add components to panel 1 //
        p1.add(lblWardType);
        p1.add(txtWardType);
        p1.add(lblReqNurses);
        p1.add(txtReqNurses);
        p1.add(lblReqDoctors);
        p1.add(txtReqDoctors);
        p1.add(lblScheduled);
        p1.add(txtScheduled);

        p1.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(p1, BorderLayout.NORTH);

        // Panel 2 //
        JPanel p2 = new JPanel();
        btnCancel = new JButton("Cancel");
        btnAdd = new JButton("Add");
        p2.add(btnCancel);
        p2.add(btnAdd);
        add(p2, BorderLayout.CENTER);

        // Add button action listeners
        btnAdd.addActionListener(this);
        btnCancel.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Add":
                // Checking if text has been entered to all TextFields
                if(txtWardType.getText().equals("") || txtReqNurses.getText().equals("") || txtReqDoctors.getText().equals("") ||
                        txtScheduled.getText().equals("")) {
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
                    } catch (NumberFormatException e) { // not an int if throws exception
                        isString = true;
                    }
                    if(!isString) { // if wardtype.getText() is a number
                        JOptionPane.showMessageDialog(null, "Ward type should not contain a number.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Do not continue
                    }
                    DBConnection dbConnection = new DBConnection();
                    Ward ward = new Ward();
                    ward.setWardType(txtWardType.getText());
                    ward.setReqNurses(Integer.valueOf(txtReqNurses.getText()));
                    ward.setReqDoctors(Integer.valueOf(txtReqDoctors.getText()));
                    ward.setScheduled(txtScheduled.getText());
                    dbConnection.addWard(ward);

                    this.dispose();
                    WardMainPage.getWardMainPage();
                }
                break;
            case "Cancel":
                this.dispose(); // disposes the add ward frame
                WardMainPage wardMainPage = new WardMainPage();
                wardMainPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                wardMainPage.setSize(600, 500);
                wardMainPage.setLocationRelativeTo(null);
                wardMainPage.setVisible(true);
                break;
        }
    }

}
