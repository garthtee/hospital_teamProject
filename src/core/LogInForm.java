package core;

import database.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogInForm extends JFrame implements ActionListener {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPanel panel0, panel1, panel2, panel3;
    private JLabel lblTitle, lblUsername, lblPassword;
    private JButton btnLogin, btnCancel;
    private DBConnection dbConnection = new DBConnection();

    public static void getLoginPage() {
        LogInForm logInForm = new LogInForm();
        logInForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        logInForm.setSize(300, 250);
        logInForm.setLocationRelativeTo(null);
        logInForm.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    public LogInForm() {
        setTitle("STAFF  - Welcome");
        setLayout(new GridLayout(4, 1));

        // PANEL 0
        panel0 = new JPanel();
        lblTitle = new JLabel("LOGIN");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel0.add(lblTitle);

        // PANEL 1
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 2, 5, 5));
        lblUsername = new JLabel("Username: ");
        txtUsername = new JTextField();
        panel1.add(lblUsername);
        panel1.add(txtUsername);

        // PANEL 2
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2, 5, 5));
        lblPassword = new JLabel("Password: ");
        txtPassword = new JPasswordField();
        panel2.add(lblPassword);
        panel2.add(txtPassword);

        // PANEL 3
        panel3 = new JPanel();
        btnLogin = new JButton("Login");
        btnCancel = new JButton("Cancel");
        panel3.add(btnLogin);
        panel3.add(btnCancel);

        panel0.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel2.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel3.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add Action Listeners
        btnCancel.addActionListener(this);
        btnLogin.addActionListener(this);

        // Presses login button on enter key press
        this.getRootPane().setDefaultButton(btnLogin);


        add(panel0);
        add(panel1);
        add(panel2);
        add(panel3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Login":
                if(!txtUsername.getText().equals("") && !new String(txtPassword.getPassword()).equals("")) {
                    try {
                        ArrayList<String> resultList = new ArrayList<>();
                        resultList = dbConnection.getLoginExistence(Integer.valueOf(txtUsername.getText()), new String(txtPassword.getPassword()));
                        String existence = resultList.get(0);
                        String privilege = resultList.get(1);

                        if (existence.equals("true")) {
                            switch (privilege) {
                                case "employee":
                                    EmployeePage employeePage = new EmployeePage(Integer.valueOf(txtUsername.getText()));
                                    employeePage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    employeePage.setSize(600, 500);
                                    employeePage.setResizable(false);
                                    employeePage.setLocationRelativeTo(null);
                                    employeePage.setVisible(true);
                                    this.dispose();
                                    break;
                                case "admin":
                                    AdminPage.getAdminPage(Integer.valueOf(txtUsername.getText()));
                                    this.dispose();
                                    break;
                                case "manager":
                                    E_ManagerPage e_managerPage = new E_ManagerPage(Integer.valueOf(txtUsername.getText()));
                                    e_managerPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                    e_managerPage.setSize(650, 500);
                                    e_managerPage.setResizable(false);
                                    e_managerPage.setLocationRelativeTo(null);
                                    e_managerPage.setVisible(true);
                                    this.dispose();
                                    break;
                            }
                        }
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Username should be in integer format.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Cancel":
                this.dispose();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Incorrect login details.", "Error", JOptionPane.ERROR_MESSAGE);
                txtPassword.setText("");
                break;
        }
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        LogInForm gui = new LogInForm();
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(300, 250);
        gui.setResizable(false);
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }
}