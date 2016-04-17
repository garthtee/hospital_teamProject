package core;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Garth Toland on 17/04/2016.
 * Description:
 */
public class ViewShift extends JFrame implements ActionListener {

    public static void getViewShift(Shift shift) {
        ViewShift viewShift = new ViewShift(shift);
        viewShift.setVisible(true);
        viewShift.pack();
        viewShift.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewShift.setResizable(false);
        viewShift.setLocationRelativeTo(null);
    }

    public ViewShift(Shift shift) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ok":
                this.dispose();
                ShiftMainPage.getShiftMainPage();
                break;
        }
    }
}
