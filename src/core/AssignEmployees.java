package core;

import javax.swing.*;

/**
 * Created by garthtee on 3/1/16.
 */
public class AssignEmployees extends JFrame {

    private JPanel p1, p2;
    private JLabel lblTitle;
    private JButton btnOk;
    private JComboBox<Shift> jcbShift;

    public AssignEmployees() {

    }

    // Main Method
    public static void main(String[] args) {
        AssignEmployees gui = new AssignEmployees();
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(450, 450);
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }
}
