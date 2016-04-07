package core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class E_ManagerPage extends JFrame implements ActionListener {


    private JPanel center, title;
    private JPanel listOfEmployees, btnPanel;
    private JList<Shift> list;
    private JScrollPane scrollPane = new JScrollPane();
    private JButton btnAssignWard, btnApproveHols, btnViewHours, btnClockIn, btnLogout, btnSHours;
    private JLabel lblSpace;
    //private ArrayList<E_Manager> listOfEmployees = new ArrayList<>();



    public E_ManagerPage(){

        setLayout(new BorderLayout());
        setTitle("Manager Home Page");

        title = new JPanel();
        JLabel lblTitle = new JLabel("Manager Home Page");
        lblTitle.setFont(new Font("Sans Serif", Font.ITALIC, 24));
        title.add(lblTitle, BorderLayout.CENTER);


        listOfEmployees = new JPanel();
        list = new JList(); //data has type Object[]
        scrollPane.getViewport().setView(list);
        scrollPane.setPreferredSize(new Dimension(400, 360));
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);

        listOfEmployees.add(scrollPane, BorderLayout.SOUTH);
        listOfEmployees.setBorder(BorderFactory.createTitledBorder("List of Employees"));


        // Panel Left //
        center = new JPanel();
        center.add(title, BorderLayout.NORTH);
        center.add(listOfEmployees, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);


        // Panel 2 //
        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(5, 1, 10, 10));
        //  btnPanel.add(btnClockIn = new JButton("Clock In/ Clock Out"));
        btnPanel.add(btnAssignWard = new JButton("Assign Employee To Ward"));

        btnPanel.add(btnApproveHols = new JButton("Approve Holiday Requests"));
        btnPanel.add(btnViewHours = new JButton("View Recorded Hours"));
        btnPanel.add(btnSHours = new JButton("Schedule Weekly Hours"));
        // btnPanel.add(lblSpace = new JLabel(""));
        btnPanel.add(btnLogout = new JButton("Log Out"));
        btnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(btnPanel, BorderLayout.EAST);

        // Add action listeners on each button
        //btnClockIn.addActionListener(this);
        //btnAssignWard.addActionListener(this);
        //btnViewHours.addActionListener(this);
        //btnApproveHols.addActionListener(this);


        btnLogout.addActionListener(this);


    }


    public static void main(String[] args) {
        E_ManagerPage gui = new E_ManagerPage();
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(650, 500);
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        switch (e.getActionCommand()) {
        /*case "Clock In":
        	int counter=1;
        	counter++;
        	if(counter%2==0){
        		StopWatch.start();
        	}
        	else{
        		StopWatch.stop();

        		StopWatch.getElapsedTime();
        	}


        	break;*/
            case "Request Holiday":
                bookHolidays sp = new bookHolidays();
                sp.setVisible(true);
                sp.pack();
                sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                sp.setLocationRelativeTo(null);
                break;
            case "Log Out":
                LogInForm form = new LogInForm();
                form.setVisible(true);
                form.setSize(300, 250);
                form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                form.setLocationRelativeTo(null);
                this.dispose();

                break;
        }

    }
}
