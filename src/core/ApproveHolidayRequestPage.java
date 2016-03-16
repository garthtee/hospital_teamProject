package core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by garth on 04/03/2016.
 */
public class ApproveHolidayRequestPage extends JFrame {

    private JPanel panelTop, panelCenter, panelBottom;
    private JLabel lblTitle;
    private JList<Employee> pendingList = new JList<>();
    private JList<Employee> approvedList = new JList<>();
    private ArrayList<Employee> pendingArray = new ArrayList<>();
    private ArrayList<Employee> approvedArray = new ArrayList<>();
    private JScrollPane pendingScroll = new JScrollPane();
    private JScrollPane approvedScroll = new JScrollPane();
    private JButton btnCancel, btnApprove, btnDecline;

    public ApproveHolidayRequestPage() {

        setTitle("Holiday Requests");
        setLayout(new BorderLayout());

        // Assigning new panel objects
        panelTop = new JPanel();
        panelCenter = new JPanel();
        panelBottom = new JPanel();

        // TopPanel
        panelTop.add(lblTitle = new JLabel("Holiday Requests"));
        add(panelTop);

        // Center Panel with lists

        // Pending
        pendingList = new JList(pendingArray.toArray()); //data has type Object[]
        pendingScroll.getViewport().setView(pendingList);
        pendingScroll.setPreferredSize(new Dimension(400, 360));
        pendingList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        pendingList.setLayoutOrientation(JList.VERTICAL);
        panelCenter.add(pendingScroll, BorderLayout.CENTER);
        panelCenter.setBorder(BorderFactory.createTitledBorder("Pending"));
        add(panelCenter);

        // Approved
        approvedList = new JList(approvedArray.toArray()); //data has type Object[]
        approvedScroll.getViewport().setView(pendingList);
        approvedScroll.setPreferredSize(new Dimension(400, 360));
        approvedList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        approvedList.setLayoutOrientation(JList.VERTICAL);
        approvedList.add(pendingScroll, BorderLayout.EAST);
        panelCenter.setBorder(BorderFactory.createTitledBorder("Pending"));
        add(panelCenter);
    }

    public static void main(String[] args) {
        ApproveHolidayRequestPage approveHolidayRequestPage = new ApproveHolidayRequestPage();
        approveHolidayRequestPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        approveHolidayRequestPage.pack();
        approveHolidayRequestPage.setLocationRelativeTo(null);
        approveHolidayRequestPage.setVisible(true);
    }
}
