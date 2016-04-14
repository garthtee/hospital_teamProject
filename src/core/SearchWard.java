package core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Garth Toland on 14/04/2016.
 * Description:
 */
public class SearchWard extends JFrame implements ActionListener {

    private JLabel lblWardID, lblSpace;
    private JTextField txtSearch;
    private JButton btnSearch, btnCancel;
    private JPanel panelTop, panelBottom;
    private ArrayList<Ward> wardList = new ArrayList<>();

    public static void getSearchWardPage(ArrayList<Ward> wardList) {
        SearchWard searchWard = new SearchWard(wardList);
        searchWard.setVisible(true);
        searchWard.setSize(300, 165);
        searchWard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchWard.setResizable(false);
        searchWard.setLocationRelativeTo(null);
    }

    public SearchWard(ArrayList<Ward> wardList) {
        this.wardList = wardList;

        setTitle("Search Wards");
        setLayout(new BorderLayout());

        // Assign variables
        lblWardID = new JLabel("Ward ID: ");
        txtSearch = new JTextField(12);
        btnCancel = new JButton("Cancel");
        btnSearch = new JButton("Search");

        // panelTop
        panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());

        panelTop.add(lblWardID, BorderLayout.CENTER);
        panelTop.add(txtSearch, BorderLayout.EAST);
        add(panelTop, BorderLayout.CENTER);

        // panelBottom
        panelBottom = new JPanel();
        panelBottom.setLayout(new FlowLayout());
        panelBottom.add(btnCancel);
        panelBottom.add(btnSearch);
        add(panelBottom, BorderLayout.SOUTH);

        // adding style // top / left / bottom / rights
        panelTop.setBorder(new EmptyBorder(20, 10, 5, 10));
        panelBottom.setBorder(new EmptyBorder(5, 10, 10, 10));

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
                boolean wardFound = false;
                for (Ward ward : wardList) {
                    try {
                        if (ward.getWard_ID() == Integer.valueOf(txtSearch.getText())) {
                            wardFound = true;
                            ViewWard viewWard = new ViewWard(ward);
                            viewWard.setVisible(true);
                            viewWard.pack();
                            viewWard.setResizable(false);
                            viewWard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            viewWard.setLocationRelativeTo(null);
                            this.dispose();
                            return;
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "You must enter a number", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if (!wardFound) { // if employee not found..
                    wardFound = false;
                    JOptionPane.showMessageDialog(null, "No ward found! \n\n"
                            + "Search criteria being:\n"
                            + txtSearch.getText(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;
            default:
                JOptionPane.showMessageDialog(null, "Something went wrong!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }

    }
}
