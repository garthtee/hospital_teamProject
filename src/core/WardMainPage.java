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
 * Created by Garth Toland on 13/04/2016.
 * Description: A page where the admin can edit
 * wards in the database from the application.
 */
public class WardMainPage extends JFrame implements ActionListener {

    private JButton btnAddWard, btnRemoveWard,btnUpdateEmp, btnSearchWard, btnViewWard, btnGoBack;
    private JPanel p1, p2, panelLeft, panelLeftTop;
    private JList<Ward> list;
    private JScrollPane scrollPane = new JScrollPane();
    private DBConnection dbConnection = new DBConnection();
    ArrayList<Ward> wardList = new ArrayList<>();
    private Ward selectedWard;
    private DefaultListModel<Ward> defaultListModel;

    public void createWardModel() {
        wardList = dbConnection.getWards();
        defaultListModel = new DefaultListModel<>();
        for (Ward ward : wardList)
            defaultListModel.addElement(ward); // Add employees to Default List Model
    }

    public WardMainPage() {

        setLayout(new BorderLayout());
        setTitle("Manage Employees");

        createWardModel();

        // Panel 1 //
        p1 = new JPanel();
        list = new JList(defaultListModel); //data has type Object[]
        list.setCellRenderer(new wardCellRenderer());
        scrollPane.getViewport().setView(list);
        scrollPane.setPreferredSize(new Dimension(400, 360));
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedWard = list.getSelectedValue();
            }
        });
        p1.add(scrollPane, BorderLayout.SOUTH);
        p1.setBorder(BorderFactory.createTitledBorder("Wards"));

        // Panel Left //
        panelLeft = new JPanel();
        panelLeft.add(p1, BorderLayout.CENTER);
        add(panelLeft, BorderLayout.CENTER);

        // Panel 2 //
        p2 = new JPanel();
        p2.setLayout(new GridLayout(6, 1, 10, 10));
        p2.add(btnAddWard = new JButton("Add Ward"));
        p2.add(btnRemoveWard = new JButton("Remove Ward"));
        p2.add(btnUpdateEmp = new JButton("Update Ward"));
        p2.add(btnSearchWard = new JButton("Search Ward"));
        p2.add(btnViewWard = new JButton("View Ward"));
        p2.add(btnGoBack = new JButton("Go back"));
        p2.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(p2, BorderLayout.EAST);

        // Add action listeners on each button
        btnAddWard.addActionListener(this);
        btnRemoveWard.addActionListener(this);
        btnUpdateEmp.addActionListener(this);
        btnSearchWard.addActionListener(this);
        btnViewWard.addActionListener(this);
        btnGoBack.addActionListener(this);
    }

    class wardCellRenderer extends JLabel implements ListCellRenderer {
        private final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

        public wardCellRenderer() {
            setOpaque(true);
            setIconTextGap(12);
        }

        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            Ward ward = (Ward) value;
            setText("Ward " +ward.getWard_ID() + ": " + ward.getWardType());
            if (isSelected) {
                setBackground(HIGHLIGHT_COLOR);
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
            case "Add Ward":
                this.dispose();
                AddWard addWard = new AddWard();
                addWard.setVisible(true);
                addWard.pack();
                addWard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addWard.setResizable(false);
                addWard.setLocationRelativeTo(null);
                break;
            case "Remove Ward":
                if (selectedWard != null) { // if an ward is selected
                    int chosenOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the "
                                    + selectedWard.getWardType() + " ward?",
                            "Remove ward", JOptionPane.YES_NO_OPTION);
                    if (chosenOption == 0) {
                        dbConnection.removeWard(selectedWard);
                        defaultListModel.removeElement(selectedWard);
                        break;
                    }
                } else // if no employee selected
                    JOptionPane.showMessageDialog(null, "You must select a ward!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Update Ward":
                if (selectedWard != null) {
                    this.dispose();
                    UpdateWard updateWard = new UpdateWard(selectedWard);
                    updateWard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    updateWard.pack();
                    updateWard.setResizable(false);
                    updateWard.setLocationRelativeTo(null);
                    updateWard.setVisible(true);
                } else
                    JOptionPane.showMessageDialog(null, "You must select an employee!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Search Ward":

                break;
            case "View Ward":

                break;
            case "Go back":
                this.dispose();
                AdminPage adminPage = new AdminPage(-1);
                adminPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                adminPage.setSize(600, 500);
                adminPage.setResizable(false);
                adminPage.setLocationRelativeTo(null);
                adminPage.setVisible(true);
                break;
        }
    }
}
