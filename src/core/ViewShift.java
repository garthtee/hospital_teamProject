package core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Garth Toland on 17/04/2016.
 * Description:
 */
public class ViewShift extends JFrame implements ActionListener {

    private JButton btnOk;

    static void getViewShift(Shift shift) {
        ViewShift viewShift = new ViewShift(shift);
        viewShift.setVisible(true);
        viewShift.pack();
        viewShift.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewShift.setResizable(false);
        viewShift.setLocationRelativeTo(null);
    }

    public ViewShift(Shift shift) {

        JTextField txtID, txtShiftStart, txtShiftEnd, txtShiftType, txtWardID;
        JLabel lblID, lblShiftStart, lblShiftEnd, lblShiftType, lblWardID;

        setTitle("View");
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
        txtID.setText(String.valueOf(shift.getShift_ID()));
        txtShiftStart.setText(shift.getStartTime());
        txtShiftEnd.setText(shift.getEndTime());
        txtShiftType.setText(shift.getShiftType());
        txtWardID.setText(String.valueOf(shift.getWard_ID()));


        // Making text boxes uneditable
        txtID.setEditable(false);
        txtShiftStart.setEditable(false);
        txtShiftEnd.setEditable(false);
        txtShiftType.setEditable(false);
        txtWardID.setEditable(false);


        // Presses ok button on enter key press
        this.getRootPane().setDefaultButton(btnOk);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ok":
                this.dispose();
                ShiftMainPage.getShiftMainPage();
                break;
        }
    }
}
