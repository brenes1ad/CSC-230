package Oct30Notes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGui3 {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton button = new JButton("Click Me \n if you please!");

        button.addActionListener(new ButtonListener(button));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);

        frame.setSize(300,300);
        //frame.pack();
        frame.setVisible(true);
    }

    static class ButtonListener implements ActionListener {
        private int clickCounter = 0;
        private JButton buttonLocal;

        public ButtonListener(JButton button) {
            buttonLocal = button;
        }

        public void actionPerformed(ActionEvent e){
            if (clickCounter++ == 0){
                buttonLocal.setText("I've been clicked!");
            } else if(clickCounter <= 20){
                buttonLocal.setText("Ive been clicked " + clickCounter + " times");
            } else{
                System.exit(0);
            }
        }
    }
}
