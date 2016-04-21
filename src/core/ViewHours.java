package core;

import database.DBConnection_Scheduler;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Garth Toland on 19/04/2016.
 * Description:
 */
public class ViewHours extends JFrame implements ActionListener {

    ArrayList<Shift_Employee> shift_employees = new ArrayList<>();
    private Shift_Employee selectedShift_Employee;
    private DefaultListModel<Shift_Employee> defaultListModel;
    private DBConnection_Scheduler dbConnection_scheduler = new DBConnection_Scheduler();
    private JList<Shift_Employee> list;

    public void createShift_EmployeeModel() {
        shift_employees = dbConnection_scheduler.getShift_Employees(); // adding database records to employee list
        defaultListModel = new DefaultListModel<>();
        for (Shift_Employee se : shift_employees)
            defaultListModel.addElement(se); // Add employees to Default List Model
    }

    static void getViewHours() {
        ViewHours viewHours = new ViewHours();
        viewHours.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewHours.pack();
        viewHours.setLocationRelativeTo(null);
        viewHours.setVisible(true);
    }

    public ViewHours() {
        // Creating variables
        JButton btnOk;
        JPanel p1, p2;
        JScrollPane scrollPane = new JScrollPane();

        setLayout(new BorderLayout());
        setTitle("Schedule");

        createShift_EmployeeModel();

        // Panel 1 //
        p1 = new JPanel();
        list = new JList(defaultListModel); //data has type Object[]
        list.setCellRenderer(new shift_employeeCellRenderer());
        scrollPane.getViewport().setView(list);
        scrollPane.setPreferredSize(new Dimension(400, 360));
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedShift_Employee = list.getSelectedValue();
            }
        });
        p1.add(scrollPane, BorderLayout.SOUTH);
        p1.setBorder(BorderFactory.createTitledBorder("Schedule"));

        // Panel 2 //
        p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        p2.add(btnOk = new JButton("Ok"));

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);

        // Add action listeners on each button
        btnOk.addActionListener(this);
    }

    // editing list items
    class shift_employeeCellRenderer extends JLabel implements ListCellRenderer {
        private final Color SELECTED_BACKGROUND_COLOR = new Color(0, 128, 128);

        public shift_employeeCellRenderer() {
            setOpaque(true);
            setIconTextGap(12);
        }

        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            Shift_Employee se = (Shift_Employee) value;
            setText("Assigned shift " +se.getShift_employee_ID() + ": Shift ID" + se.getShift_ID() + " " + se.getEmployee_ID()
                    + " " + se.getDate());
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
            case "Ok":
                this.dispose();
                E_ManagerPage.getManagerPage();
                break;
        }
    }
}
