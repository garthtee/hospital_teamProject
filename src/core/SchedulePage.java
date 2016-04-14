package core;

import database.DBConnection_Scheduler;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Kevin on 4/14/2016.
 */
public class SchedulePage extends JFrame implements ActionListener {

    private JLabel lblWard;
    private JButton btnSchedule, btnCancel;
    private JPanel panelTop, panelBottom;
    private JComboBox<String> jcbSchedule;
    private int selectedItem;

    public SchedulePage() {

        setTitle("Schedule Employees");
        setLayout(new BorderLayout());

        // Assign variables
        btnCancel = new JButton("Cancel");
        btnSchedule = new JButton("Schedule");

        // panelTop
        panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());

        ArrayList<Ward> wards=new ArrayList<>();
        jcbSchedule = new JComboBox<>(searches);
        jcbSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedItem = Integer.valueOf(jcbSchedule.getSelectedItem());
            }
        });
        panelTop.add(jcbSchedule, BorderLayout.NORTH);
        panelTop.add(lblEmpName, BorderLayout.CENTER);
        panelTop.add(txtSearch, BorderLayout.EAST);
        add(panelTop, BorderLayout.CENTER);

        // panelBottom
        panelBottom = new JPanel();
        panelBottom.setLayout(new FlowLayout());
        panelBottom.add(btnCancel);
        panelBottom.add(btnSchedule);
        add(panelBottom, BorderLayout.SOUTH);

        // adding style // top / left / bottom / rights
        panelTop.setBorder(new EmptyBorder(20, 10, 5, 10));
        panelBottom.setBorder(new EmptyBorder(5, 10, 10, 10));

        // Add actionListeners
        btnCancel.addActionListener(this);
        btnSchedule.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "Cancel":
                this.dispose();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Something went wrong!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }

    }
}