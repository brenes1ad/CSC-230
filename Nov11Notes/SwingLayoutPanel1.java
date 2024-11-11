package Nov11Notes;

import javax.swing.*;
import java.awt.*;

public class SwingLayoutPanel1 {

    public static void main(String[] args) {
        SwingLayoutPanel1 gui = new SwingLayoutPanel1();
        gui.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton button1 = new JButton("Shake and bake!");
        JButton button2 = new JButton("Life is good!");

        JTextField field1 = new JTextField("Your Name");
        JTextField field2 = new JTextField("Your Age");
        System.out.println(field1.getText());
        field1.addActionListener(event -> System.out.println(field1.getText()));

        panel.add(button1);
        panel.add(button2);
        panel.add(field1);
        panel.add(field2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.EAST, panel);

        frame.setSize(200, 200);
        frame.setVisible(true);



    }
}
