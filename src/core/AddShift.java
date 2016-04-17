package core;

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

    public static void getAddShift() {
        AddShift addShift = new AddShift();
        addShift.setVisible(true);
        addShift.pack();
        addShift.setResizable(false);
        addShift.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addShift.setLocationRelativeTo(null);
    }

    public AddShift(){

        JLabel lblShiftStart, lblShiftEnd, lblShiftType, lblWardID;
        JTextField txtShiftStart, txtShiftEnd, txtShiftType, txtWardID;

        setTitle("Create");
        setLayout(new BorderLayout());

        // Panel 1 //
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 2));
        lblShiftStart = new JLabel("Shift start: ");
        txtShiftStart = new JTextField();
        lblShiftEnd = new JLabel("Shift end: ");
        txtShiftEnd = new JTextField();
        lblShiftType = new JLabel("Shift type: ");
        txtShiftType = new JTextField();
        lblWardID = new JLabel("Ward ID: ");
        txtWardID = new JTextField();

        // Add components to panel 1 //
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
                break;
            case "Cancel":
                this.dispose();
                ShiftMainPage.getShiftMainPage();
                break;
        }

    }
}
