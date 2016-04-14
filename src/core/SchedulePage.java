package core;

import database.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

        lblWard=new JLabel("Wards to schedule");

        // panelTop
        panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());

        ArrayList<Ward> wards=new ArrayList<>();
        DBConnection dbConnection=new DBConnection();
        wards=dbConnection.getWards();
        //int[] wardIds = new int[10];
        jcbSchedule = new JComboBox<>();

        for(int i=0; i<wards.size(); i++) {
            jcbSchedule.addItem(String.valueOf(wards.get(i).getWard_ID()));
        }

        jcbSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedItem = (int) jcbSchedule.getSelectedItem();
            }
        });
        panelTop.add(lblWard, BorderLayout.NORTH);
        panelTop.add(jcbSchedule, BorderLayout.CENTER);
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

    public static void main(String[] args){
        SchedulePage frame=new SchedulePage();
        frame.setVisible(true);
        frame.setSize(200, 200);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}