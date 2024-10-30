package Oct30Notes;

import javax.swing.*;
public class SimpleGui1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton button = new JButton("Click Me \n if you please!");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);

        frame.setSize(300,300);
        //frame.pack();
        frame.setVisible(true);
    }
}
