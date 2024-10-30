package Oct30Notes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGui2 implements ActionListener{

    private JButton button;

    public static void main(String[] args) {
        SimpleGui2 gui = new SimpleGui2();
        gui.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        button = new JButton("Click Me");
        button.addActionListener(this);
        frame.getContentPane().add(button);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);

        frame.setSize(300,300);
        frame.setVisible(true);
    }


    private int clickCounter = 0;
    public void actionPerformed(ActionEvent e){
        if (clickCounter++ == 0){
            button.setText("I've been clicked!");
        } else if(clickCounter <= 20){
            button.setText("Ive been clicked " + clickCounter + " times");
        } else{
            System.exit(0);
        }
    }
}
