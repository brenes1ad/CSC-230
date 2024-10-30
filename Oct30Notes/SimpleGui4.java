package Oct30Notes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGui4 {
    static int clickCounter;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton button = new JButton("Click Me \n if you please!");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (clickCounter++ == 0){
                    button.setText("I've been clicked!");
                } else if(clickCounter < 20){
                    button.setText("Ive been clicked " + clickCounter + " times");
                } else{
                    System.exit(0);
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);

        frame.setSize(300,300);
        //frame.pack();
        frame.setVisible(true);
    }
}
