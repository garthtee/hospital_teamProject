package core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Garth Toland on 14/04/2016.
 * Description: Displays ward details in an uneditable
 * frame.
 */
public class ViewWard extends JFrame implements ActionListener {


    private JButton btnOk;

    static void getViewWard(Ward selectedWard) {
        ViewWard viewWard = new ViewWard(selectedWard);
        viewWard.setVisible(true);
        viewWard.pack();
        viewWard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewWard.setResizable(false);
        viewWard.setLocationRelativeTo(null);
    }

    public ViewWard(Ward ward) {

        JLabel lblID, lblWardType, lblReqNurses, lblReqDoctors, lblScheduled;
        JTextField txtID, txtWardType, txtReqNurses, txtReqDoctors, txtScheduled;

        setTitle("View");
        setLayout(new BorderLayout());

        // Panel 1 //
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(5, 2));
        lblID = new JLabel("ID: ");
        txtID = new JTextField();
        lblWardType = new JLabel("Ward Type: ");
        txtWardType = new JTextField();
        lblReqNurses = new JLabel("Req Nurses: ");
        txtReqNurses = new JTextField();
        lblReqDoctors = new JLabel("Req Doctors: ");
        txtReqDoctors = new JTextField();
        lblScheduled = new JLabel("Scheduled: ");
        txtScheduled = new JTextField();

        // Add components to panel 1 //
        p1.add(lblID);
        p1.add(txtID);

        p1.add(lblWardType);
        p1.add(txtWardType);
        p1.add(lblReqNurses);
        p1.add(txtReqNurses);
        p1.add(lblReqDoctors);
        p1.add(txtReqDoctors);
        p1.add(lblScheduled);
        p1.add(txtScheduled);

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
        txtID.setText(String.valueOf(ward.getWard_ID()));
        txtWardType.setText(ward.getWardType());
        txtReqNurses.setText(String.valueOf(ward.getReqNurses()));
        txtReqDoctors.setText(String.valueOf(ward.getReqDoctors()));
        txtScheduled.setText(ward.getScheduled());


        // Making text boxes uneditable
        txtID.setEditable(false);
        txtWardType.setEditable(false);
        txtReqNurses.setEditable(false);
        txtReqDoctors.setEditable(false);
        txtScheduled.setEditable(false);


        // Presses ok button on enter key press
        this.getRootPane().setDefaultButton(btnOk);
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
