package core;

import database.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Garth Toland on 16/04/2016.
 * Description: Adds a shift to Database.
 */
public class AddShift extends JFrame implements ActionListener {

    private JButton btnAdd, btnCancel;
    private JTextField txtID, txtShiftStart, txtShiftEnd, txtShiftType, txtWardID;

    public static void getAddShift() {
        AddShift addShift = new AddShift();
        addShift.setVisible(true);
        addShift.pack();
        addShift.setResizable(false);
        addShift.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addShift.setLocationRelativeTo(null);
    }

    public AddShift(){

        JLabel lblID, lblShiftStart, lblShiftEnd, lblShiftType, lblWardID;

        setTitle("Create");
        setLayout(new BorderLayout());

        // Panel 1 //
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(5, 2));
        lblID = new JLabel("ID: ");
        txtID = new JTextField();
        lblShiftStart = new JLabel("Shift start: ");
        txtShiftStart = new JTextField();
        lblShiftEnd = new JLabel("Shift end: ");
        txtShiftEnd = new JTextField();
        lblShiftType = new JLabel("Shift type: ");
        txtShiftType = new JTextField();
        lblWardID = new JLabel("Ward ID: ");
        txtWardID = new JTextField();

        // Add components to panel 1 //
        p1.add(lblID);
        p1.add(txtID);
        p1.add(lblShiftStart);
        p1.add(txtShiftStart);
        p1.add(lblShiftEnd);
        p1.add(txtShiftEnd);
        p1.add(lblShiftType);
        p1.add(txtShiftType);
        p1.add(lblWardID);
        p1.add(txtWardID);

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
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add":
                if(txtShiftStart.getText().equals("") || txtShiftEnd.getText().equals("") ||
                        txtShiftType.getText().equals("") || txtWardID.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if(!TimeValidator.validateTime(txtShiftStart.getText())) {
                    JOptionPane.showMessageDialog(null, "Incorrect start time. \n\nCorrect format example, 12:00\n", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if(!TimeValidator.validateTime(txtShiftEnd.getText())) {
                    JOptionPane.showMessageDialog(null, "Incorrect end time. \n\nCorrect format example, 12:00\n", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if(isNumeric(txtShiftType.getText())) {
                    JOptionPane.showMessageDialog(null, "Shift type must not be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    try {
                        DBConnection dbConnection = new DBConnection();
                        Shift shift = new Shift();
                        shift.setShift_ID(Integer.valueOf(txtID.getText()));
                        shift.setStartTime(txtShiftStart.getText());
                        shift.setEndTime(txtShiftEnd.getText());
                        shift.setShiftType(txtShiftType.getText());
                        shift.setWard_ID(Integer.valueOf(txtWardID.getText()));
                        dbConnection.addShift(shift);
                        this.dispose();
                        ShiftMainPage.getShiftMainPage();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Ward must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "Cancel":
                this.dispose();
                ShiftMainPage.getShiftMainPage();
                break;
        }

    }

    public boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
