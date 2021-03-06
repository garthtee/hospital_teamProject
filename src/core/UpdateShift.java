package core;

import database.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Garth Toland on 16/04/2016.
 * Description: Updates the selected shift.
 */
public class UpdateShift extends JFrame implements ActionListener {

    private JTextField txtID, txtShiftStart, txtShiftEnd, txtShiftType, txtWardID;

    static void getUpdateShift(Shift shift) {
        UpdateShift updateShift = new UpdateShift(shift);
        updateShift.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateShift.pack();
        updateShift.setResizable(false);
        updateShift.setLocationRelativeTo(null);
        updateShift.setVisible(true);
    }

    public UpdateShift(Shift shift) {
        JButton btnUpdate, btnCancel;
        JLabel lblID, lblShiftStart, lblShiftEnd, lblShiftType, lblWardID;


        setTitle("Update Ward");
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

        p1.setBorder(new EmptyBorder(30, 70, 30, 70));
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
        txtID.setText(String.valueOf(shift.getShift_ID()));
        txtShiftStart.setText(shift.getStartTime());
        txtShiftEnd.setText(shift.getEndTime());
        txtShiftType.setText(shift.getShiftType());
        txtWardID.setText(String.valueOf(shift.getWard_ID()));

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
                ShiftMainPage.getShiftMainPage();
                break;
            case "Update":

                try {
                    DateValidator dateValidator = new DateValidator();
                    dateValidator.setYear(txtShiftStart.getText());
                    dateValidator.setMonth(txtShiftStart.getText());
                    dateValidator.setDay(txtShiftStart.getText());
                } catch (StringIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date.  \n\nExample format: yyyy-mm-dd\n", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Date error.\n\n" +ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    DateValidator dateValidator = new DateValidator();
                    dateValidator.setYear(txtShiftEnd.getText());
                    dateValidator.setMonth(txtShiftEnd.getText());
                    dateValidator.setDay(txtShiftEnd.getText());
                } catch (StringIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date.  \n\nExample format: yyyy-mm-dd\n", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Date error.\n\n" +ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                DBConnection dbConnection = new DBConnection();
                Shift shift = new Shift();
                if(txtShiftStart.getText().equals("") || txtShiftEnd.getText().equals("") || txtShiftType.getText().equals("") ||
                        txtWardID.getText().equals("")) {
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
                        shift.setShift_ID(Integer.valueOf(txtID.getText()));
                        shift.setStartTime(txtShiftStart.getText());
                        shift.setEndTime(txtShiftEnd.getText());
                        shift.setShiftType(txtShiftType.getText());
                        shift.setWard_ID(Integer.valueOf(txtWardID.getText()));

                        dbConnection.updateShift(shift);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Ward must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
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
