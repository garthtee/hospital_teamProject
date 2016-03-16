package core;

import javax.swing.*;

/**
 * Created by garthtee on 3/1/16.
 */
public class AssignEmployeeToWard extends JFrame {

    private JList<Employee> employeeList;
    private JList<Ward> wardList;
    private JButton btnAssign, btnViewAll;
    private JComboBox wards;
    private JLabel lblTitle;
    private JPanel pnlTitle, pnlList, pnlButton;

    public AssignEmployeeToWard() {




    }

    // Main Method
    public static void main(String[] args) {
        AssignEmployeeToWard gui = new AssignEmployeeToWard();
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(450, 450);
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }
}
