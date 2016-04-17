package core;

import database.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Garth Toland on 16/04/2016.
 * Description: A page where the admin can
 * edit shifts.
 */
public class ShiftMainPage extends JFrame implements ActionListener {

    private JList<Shift> list;
    private DBConnection dbConnection = new DBConnection();
    private DefaultListModel<Shift> defaultListModel;
    private Shift selectedShift;
    private ArrayList<Shift> shiftList;

    public void createShiftModel() {
        shiftList = dbConnection.getShifts();
        defaultListModel = new DefaultListModel<>();
        for (Shift shift : shiftList)
            defaultListModel.addElement(shift); // Add shifts to Default List Model
    }

    public static void getShiftMainPage() {
        ShiftMainPage shiftMainPage = new ShiftMainPage();
        shiftMainPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        shiftMainPage.pack();
        shiftMainPage.setResizable(false);
        shiftMainPage.setLocationRelativeTo(null);
        shiftMainPage.setVisible(true);
    }

    public ShiftMainPage() {

        // Creating variables
        JButton btnAddShift, btnRemoveShift,btnUpdateShift, btnSearchShift, btnViewShift, btnGoBack;
        JPanel p1, p2, panelLeft, panelLeftTop;

        setLayout(new BorderLayout());
        setTitle("Manage Shifts");

        createShiftModel();

        // Panel 1 //
        p1 = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        list = new JList(defaultListModel); //data has type Object[]
        list.setCellRenderer(new shiftCellRenderer());
        scrollPane.getViewport().setView(list);
        scrollPane.setPreferredSize(new Dimension(400, 360));
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedShift = list.getSelectedValue();
            }
        });
        p1.add(scrollPane, BorderLayout.SOUTH);
        p1.setBorder(BorderFactory.createTitledBorder("Shifts"));

        // Panel Left //
        panelLeft = new JPanel();
        panelLeft.add(p1, BorderLayout.CENTER);
        add(panelLeft, BorderLayout.CENTER);

        // Panel 2 //
        p2 = new JPanel();
        p2.setLayout(new GridLayout(6, 1, 10, 10));
        p2.add(btnAddShift = new JButton("Add Shift"));
        p2.add(btnRemoveShift = new JButton("Remove Shift"));
        p2.add(btnUpdateShift = new JButton("Update Shift"));
        p2.add(btnSearchShift = new JButton("Search Shift"));
        p2.add(btnViewShift = new JButton("View Shift"));
        p2.add(btnGoBack = new JButton("Go back"));
        p2.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(p2, BorderLayout.EAST);

        // Add action listeners on each button
        btnAddShift.addActionListener(this);
        btnRemoveShift.addActionListener(this);
        btnUpdateShift.addActionListener(this);
        btnSearchShift.addActionListener(this);
        btnViewShift.addActionListener(this);
        btnGoBack.addActionListener(this);
    }

    // editing list items
    class shiftCellRenderer extends JLabel implements ListCellRenderer {
        private final Color SELECTED_BACKGROUND_COLOR = new Color(0, 128, 128);

        public shiftCellRenderer() {
            setOpaque(true);
            setIconTextGap(12);
        }

        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            Shift shift = (Shift) value;
            setText("Shift " +shift.getShift_ID() + " is from: " +shift.getStartTime() + " to " +shift.getEndTime());
            this.setFont(new Font("Sans Serif", Font.PLAIN, 20));

            if (isSelected) {
                setBackground(SELECTED_BACKGROUND_COLOR);
                setForeground(Color.white);
            } else {
                setBackground(Color.white);
                setForeground(Color.black);
            }
            return this;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add Shift":
                this.dispose();
                AddShift.getAddShift();
                break;
            case "Remove Shift":
                if (selectedShift != null) { // if an shift is selected
                    int chosenOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove shift "
                                    + selectedShift.getShift_ID() + "?",
                            "Remove shift", JOptionPane.YES_NO_OPTION);
                    if (chosenOption == 0) {
//                        dbConnection.removeShift(selectedShift);
                        defaultListModel.removeElement(selectedShift);
                    }
                } else // if no shift selected
                    JOptionPane.showMessageDialog(null, "You must select an shift!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Update Shift":
                if (selectedShift != null) { // if a shift is selected
                    this.dispose();
                    UpdateShift.getUpdateShift(selectedShift);
                } else // if no shift selected
                    JOptionPane.showMessageDialog(null, "You must select an shift!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Search Shift":
                break;
            case "View Shift":
                if (selectedShift != null) { // if a shift is selected
                    this.dispose();
                    ViewShift.getViewShift(selectedShift);
                } else // if no shift selected
                    JOptionPane.showMessageDialog(null, "You must select an shift!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Go back":
                this.dispose();
                AdminPage.getAdminPage(-1);
                break;
        }
    }
}
