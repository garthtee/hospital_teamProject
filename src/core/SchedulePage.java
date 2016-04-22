package core;

import database.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Kevin on 4/14/2016.
 */
public class SchedulePage extends JFrame implements ActionListener {

    private JLabel lblWard;
    private JTextField dateEntered = new JTextField(10);
    private JButton btnSchedule, btnCancel;
    private JPanel panelTop, panelBottom;
    private JComboBox<String> jcbSchedule;
    private String selectedItem;
    private String endDate;
    ArrayList<Ward> wards;

    public SchedulePage() {

        setTitle("Schedule Employees");
        setLayout(new BorderLayout());

        // Assign variables
        btnCancel = new JButton("Cancel");
        btnSchedule = new JButton("Schedule");

        lblWard = new JLabel("Wards to schedule");

        // panelTop
        panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(4, 1, 5, 5));

        wards = new ArrayList<>();
        DBConnection dbConnection = new DBConnection();
        wards = dbConnection.getWards();
        //int[] wardIds = new int[10];
        jcbSchedule = new JComboBox<>();

        for (int i = 0; i < wards.size(); i++) {
            if (wards.get(i).getWard_ID() != 0) {
                jcbSchedule.addItem(wards.get(i).getWardType());
            }

        }

        jcbSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedItem = jcbSchedule.getSelectedItem().toString();
            }
        });
        panelTop.add(lblWard);
        panelTop.add(jcbSchedule);
        panelTop.add(new JLabel("Week Start: "));
        panelTop.add(dateEntered);
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
                E_ManagerPage.getManagerPage();
                break;
            case "Schedule":
                int wardIDPassIn=0;
                for(Ward ward: wards){
                    if(ward.getWardType().equals(selectedItem)){
                        wardIDPassIn=ward.getWard_ID();
                    }
                }
                Scheduler s = new Scheduler(wardIDPassIn);
                Calendar calendar = Calendar.getInstance();
                try { // try parsing the string to a Calendar object
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    calendar.setTime(dateFormat.parse(dateEntered.getText()));
                } catch (ParseException exception) {
                    JOptionPane.showMessageDialog(null, "Date must contain '-' \n\n Example format: yyyy-mm-dd", "Error", JOptionPane.ERROR_MESSAGE);
                }

                s.schedule(calendar);

        }
    }

    public static void getSchedulePage() {
        SchedulePage frame = new SchedulePage();
        frame.setVisible(true);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SchedulePage frame = new SchedulePage();
        frame.setVisible(true);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}