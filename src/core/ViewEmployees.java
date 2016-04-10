package core;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Garth Toland on 07/04/2016.
 * Description:
 */
public class ViewEmployees extends JFrame implements ActionListener {

    private JPanel p1, p2;
    private JList<Employee> list;
    private JScrollPane scrollPane = new JScrollPane();
//    ArrayList<Employee> employeeList = new ArrayList<>();
    private Employee selectedEmployee;
    private DefaultListModel<Employee> defaultListModel;
    private JButton btnViewDetails, btnClose;

    public void createEmployeeModel(ArrayList<Employee> employeeArrayList) {
        defaultListModel = new DefaultListModel<>();
        for (Employee employee : employeeArrayList)
            defaultListModel.addElement(employee); // Add employees to Default List Model
    }

    public ViewEmployees(ArrayList<Employee> employeeListIn, String searchText) {

        createEmployeeModel(employeeListIn);

        setLayout(new BorderLayout());
        setTitle("Search results for: " + searchText);

        // Panel 1 //
        p1 = new JPanel();
        list = new JList(defaultListModel); //data has type Object[]
        scrollPane.getViewport().setView(list);
        scrollPane.setPreferredSize(new Dimension(400, 360));
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedEmployee = list.getSelectedValue();
            }
        });
        p1.add(scrollPane);
        p1.setBorder(BorderFactory.createTitledBorder("Employees"));


        //panel 2
        p2 = new JPanel();
        p2.add(btnViewDetails = new JButton("View Details"));
        p2.add(btnClose = new JButton("Close"));

        add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

        btnViewDetails.addActionListener(this);
        btnClose.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "View Details":
                if (selectedEmployee != null) {
                    ViewEmployee viewEmployee = new ViewEmployee(selectedEmployee);
                    viewEmployee.setVisible(true);
                    viewEmployee.pack();
                    viewEmployee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    viewEmployee.setLocationRelativeTo(null);
                } else
                    JOptionPane.showMessageDialog(null, "You must select an employee!", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Close":
                this.dispose();
                break;
        }
    }

//    /**
//     * Launch the application.
//     */
//    public static void main(String[] args) {
//        Employee e = new Employee();
//        e.setfName("Garth");
//        ArrayList<Employee> employeeArrayList = new ArrayList<>();
//        employeeArrayList.add(e);
//        ViewEmployees gui = new ViewEmployees(employeeArrayList);
//        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        gui.pack();
//        gui.setLocationRelativeTo(null);
//        gui.setVisible(true);
//    }
}
