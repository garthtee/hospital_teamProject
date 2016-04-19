package core;

import database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AssignToWard extends JFrame implements ActionListener {

    private JPanel employeePanel, panelBtn, panelA, listPanel;
    private JList<Employee> empList;
    private JList<Employee> assignedList;
    private JButton btnSchedule, btnShowAll, btnBack;
    private ArrayList<Employee> data;//= new ArrayList<>();
    private ArrayList<Employee> assignedArray = new ArrayList<Employee>();
    private ArrayList<Ward> wards;
    private JComboBox wardList;
    private JScrollPane scrollPane, aScrollPane;
    private JLabel lbl1, lbl2;
    private Shift shift1;

    public AssignToWard() {

        setTitle("Assign to Ward");
        setLayout(new BorderLayout());

        //Unassigned Employees Panel
        employeePanel = new JPanel();
        empList = new JList();
        data = new ArrayList();
        scrollPane = new JScrollPane();
        scrollPane.getViewport().setView(empList);
        scrollPane.setPreferredSize(new Dimension(250, 300));
        empList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        empList.setLayoutOrientation(JList.VERTICAL);
        employeePanel.add(scrollPane, BorderLayout.SOUTH);
        employeePanel.setBorder(BorderFactory.createTitledBorder("Unassigned Employees"));


        //Assigned Employees Panel
        panelA = new JPanel();
        assignedList = new JList(); //data has type Object[]
        assignedArray = new ArrayList();
        aScrollPane = new JScrollPane();
        aScrollPane.getViewport().setView(assignedList);
        aScrollPane.setPreferredSize(new Dimension(250, 300));
        assignedList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        assignedList.setLayoutOrientation(JList.VERTICAL);
        panelA.add(aScrollPane, BorderLayout.SOUTH);
        panelA.setBorder(BorderFactory.createTitledBorder("Assigned Employees"));


        //scrollPane = new JScrollPane(empList);
        //employeePanel.add(empList);

        DBConnection dbConnection=new DBConnection();
        wards=dbConnection.getWards();
        //int[] wardIds = new int[10];
        wardList = new JComboBox<>();

        for(int i=0; i<wards.size(); i++) {
            wardList.addItem(String.valueOf(wards.get(i).getWard_ID()));
        }


        //Right panel with buttons
        panelBtn = new JPanel();
        panelBtn.setLayout(new GridLayout(4, 1));
        btnSchedule = new JButton("Assign to Ward");
        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
        panelBtn.add(wardList);
        panelBtn.add(btnSchedule);
        //panelBtn.add(btnShowAll);
        panelBtn.add(lbl1 = new JLabel());
        //panelBtn.add(lbl2 = new JLabel());
        panelBtn.add(btnBack);
        add(employeePanel, BorderLayout.CENTER);
        add(panelBtn, BorderLayout.EAST);

        listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(1, 2, 10, 10));
        listPanel.add(employeePanel);
        listPanel.add(panelA);
        add(listPanel, BorderLayout.CENTER);



    }

    public static void main(String[] args) {
        AssignToWard frame = new AssignToWard();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            E_ManagerPage mp = new E_ManagerPage();
            mp.setVisible(true);
            mp.pack();
            //System.out.print("button pressed");
            mp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            mp.setLocationRelativeTo(null);
            this.dispose();
        } else if (e.getSource() == btnSchedule) {
            JOptionPane.showMessageDialog(null, "Employee has been assigned", "", JOptionPane.INFORMATION_MESSAGE);
        }

    }


}



