package Nov4Notes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyDrawApp{

    private JFrame frame;
    private JButton button1, button2;

    public static void main(String[] args) {
        MyDrawApp app = new MyDrawApp();
        app.go();
    }

    public void go(){
        frame = new JFrame("My Draw App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button1 = new JButton("Change Color");
        button1.addActionListener(event -> {
            System.out.println(event);
            frame.repaint();
        });

        button2 = new JButton("Change Label");
        button2.addActionListener(event -> button2.setText("What?" + n++));

        MyDrawPanel panel = new MyDrawPanel();
        frame.getContentPane().add(BorderLayout.SOUTH, button1);
        frame.getContentPane().add(BorderLayout.EAST, button2);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(300, 300);
        frame.setVisible(true);

    }

    public static int n = 0;

    class ColorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.repaint();
        }
    }

    class LabelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            button2.setText("What?" + n++);
        }
    }
//    public void actionPerformed(ActionEvent e){
//        if(e.getSource() == button1){
//            frame.repaint();
//        } else{
//            button2.setText("What?" + n++);
//        }
//    }
}
