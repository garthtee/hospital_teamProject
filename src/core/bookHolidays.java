package core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bookHolidays extends JFrame implements ActionListener {

	private JButton btnBook, btnCancel;
	private JLabel lblTitle;
	private JLabel lblstart, lblend;
	private JPanel pnlmain, btnPanel, pnltitle;
	private String startDate, endDate;
	private JTextField startField, endField;
	
	public bookHolidays(){
		setLayout(new BorderLayout());
        setTitle("Place holiday booking");
	
        
        pnltitle = new JPanel();
        JLabel lblTitle = new JLabel("Book Holiday");
        lblTitle.setFont(new Font("Sans Serif", Font.ITALIC, 24));
        pnltitle.add(lblTitle, BorderLayout.CENTER);
        add(pnltitle, BorderLayout.NORTH);
        
        
        pnlmain= new JPanel();
        pnlmain.setLayout(new GridLayout(2, 2, 10, 10));
        JLabel lblstart = new JLabel("Start Date");
        lblstart.setFont(new Font("Sans Serif", Font.ITALIC, 24));      
        JTextField startField= new JTextField();
        pnlmain.add(lblstart);
        pnlmain.add(startField);
        JLabel lblend = new JLabel("End Date");
        lblend.setFont(new Font("Sans Serif", Font.ITALIC, 24));
        JTextField endField= new JTextField();
        pnlmain.add(lblend);   
        pnlmain.add(endField);
        add(pnlmain, BorderLayout.CENTER);
        
              
        
        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(1, 2, 10, 10));
        btnPanel.add(btnBook = new JButton("Place Request"));
        btnPanel.add(btnCancel = new JButton("Cancel"));
        btnPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(btnPanel, BorderLayout.SOUTH);
        
        
        
        btnBook.addActionListener(this);
        btnCancel.addActionListener(this);
        
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		switch (e.getActionCommand()) {
        
        case "Place Request":
            JOptionPane.showMessageDialog(null, "Request submitted!", "Request", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

//        	DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);


//        	String st= startField.getText();
//            startDate = format.parse(st);
        	
//        	String end= endField.getText();
//        	endDate = format.parse(end);
        	
        	break;
        	
        case "Cancel":
            this.dispose();
        	
        	break;
		
		
		
		
	}
	
	
	}
}
