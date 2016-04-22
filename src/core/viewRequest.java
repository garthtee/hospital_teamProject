package core;

import database.DBConnection;
import database.DBConnection_Holidays;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by home on 14/04/2016.
 *
 */



public class viewRequest extends JFrame implements ActionListener {


    private DBConnection dbConnection = new DBConnection();
    private DBConnection_Holidays dbConnection_hol = new DBConnection_Holidays();
    private JPanel listOfReq;
    private JList<Request> reqList;
    private JScrollPane scrollPaneReq = new JScrollPane();
    private ArrayList<Request> listOfRequests = new ArrayList<>();
    private DefaultListModel<Request> requestDefaultListModelModel;

    private JButton ok, cancelSelected;
    public JPanel mainPanel, btnPanel;

    private Request selectedReq;
    private int passed_in_id;

    public void createRequestModel(){
        listOfRequests = dbConnection.getRequests(passed_in_id); //getting requests and adding them
        requestDefaultListModelModel = new DefaultListModel<>();

        for (Request request:listOfRequests)
            requestDefaultListModelModel.addElement(request);

    }

    public static void getviewRequest(int emp_id) {
        viewRequest page = new viewRequest(emp_id);
        page.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        page.pack();
        page.setLocationRelativeTo(null);
        page.setVisible(true);
    }


    public viewRequest(int id_in){
        setLayout(new BorderLayout());
        setTitle("Current Requests");


        passed_in_id=id_in;

        createRequestModel();

        mainPanel=new JPanel();
        mainPanel.setLayout(new GridLayout(2,1));
        add(mainPanel, BorderLayout.CENTER);



        listOfReq = new JPanel();
        reqList = new JList(listOfRequests.toArray()); //data has type Object[]
        scrollPaneReq.getViewport().setView(reqList);
        scrollPaneReq.setPreferredSize(new Dimension(400, 100));
        reqList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        reqList.setLayoutOrientation(JList.VERTICAL);
        reqList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedReq = reqList.getSelectedValue();
            }
        });


        listOfReq.add(scrollPaneReq, BorderLayout.SOUTH);
        listOfReq.setBorder(BorderFactory.createTitledBorder("Current requests"));

        mainPanel.add(listOfReq);



        btnPanel=new JPanel();
        ok = new JButton("Ok");
        cancelSelected = new JButton("Remove");
        btnPanel.add(ok);
        btnPanel.add(cancelSelected);
        mainPanel.add(btnPanel);

        ok.addActionListener(this);
        cancelSelected.addActionListener(this);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ok":
                this.dispose();
                break;
            case "Remove":
                if (selectedReq != null) {
                    int chosenOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this request?",
                            "Remove Request", JOptionPane.YES_NO_OPTION);
                    if (chosenOption == 0) {
                        dbConnection.removeRequest(selectedReq);
                        requestDefaultListModelModel.removeElement(selectedReq);

                        this.dispose();
                        getviewRequest(passed_in_id);
                        break;
                    }
                } else // if no employee selected
                    JOptionPane.showMessageDialog(null, "You must select a holiday request", "Error", JOptionPane.ERROR_MESSAGE);


                break;
        }
    }
}
