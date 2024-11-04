package Nov4Notes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyDrawApp implements ActionListener {

    private JFrame frame;
    public static void main(String[] args) {
        MyDrawApp app = new MyDrawApp();
        app.go();
    }

    public void go(){
        frame = new JFrame("My Draw App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Change Color");
        button.addActionListener(this);

        MyDrawPanel panel = new MyDrawPanel();
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(300, 300);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        frame.repaint();
    }

}
