package core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by garth on 17/04/2016.
 */
public class SearchShift extends JFrame implements ActionListener {

    private ArrayList<Shift> shiftArrayList;
    private JTextField txtSearch;

    static void getSearchShift(ArrayList<Shift> shiftArrayList) {
        SearchShift searchShift = new SearchShift(shiftArrayList);
        searchShift.setVisible(true);
        searchShift.setSize(270, 130);
        searchShift.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchShift.setResizable(false);
        searchShift.setLocationRelativeTo(null);
    }

    public SearchShift(ArrayList<Shift> shiftArrayList) {
        // Creating variables
        JButton btnSearch, btnCancel;
        JPanel panelTop, panelBottom;
        JLabel lblShiftID;

        this.shiftArrayList = shiftArrayList;

        setTitle("Search Employees");
        setLayout(new BorderLayout());

        // Assign variables
        lblShiftID = new JLabel("Shift ID: ");
        txtSearch = new JTextField(10);
        btnCancel = new JButton("Cancel");
        btnSearch = new JButton("Search");

        // panelTop
        panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());

        panelTop.add(lblShiftID, BorderLayout.CENTER);
        panelTop.add(txtSearch, BorderLayout.EAST);
        add(panelTop, BorderLayout.CENTER);

        // panelBottom
        panelBottom = new JPanel();
        panelBottom.setLayout(new FlowLayout());
        panelBottom.add(btnCancel);
        panelBottom.add(btnSearch);
        add(panelBottom, BorderLayout.SOUTH);

        // adding style // top / left / bottom / right
        panelTop.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelBottom.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add actionListeners
        btnCancel.addActionListener(this);
        btnSearch.addActionListener(this);

        // Presses search button on enter key press
        this.getRootPane().setDefaultButton(btnSearch);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Cancel":
                this.dispose();
                break;
            case "Search":
                try {

                    Shift shiftFound = new Shift();
                    for (Shift shift : shiftArrayList) {
                        if (shift.getShift_ID() == Integer.valueOf(txtSearch.getText())) {
                            shiftFound = shift;
                        }
                    }
                    this.dispose();
                    ViewShift.getViewShift(shiftFound);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "You must enter a shift ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
        }
    }
}
