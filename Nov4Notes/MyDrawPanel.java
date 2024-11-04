package Nov4Notes;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.util.Random;

public class MyDrawPanel extends JPanel {

    Random rand = new Random();
    Image image = new ImageIcon(
            getClass().getResource("Random_Turtle.jpg")).getImage();

    public void paintComponent(Graphics g) {


        g.drawImage(image, 10, 10, this);

        switch(rand.nextInt(3)){
            case 0:
                g.setColor(Color.red);
                g.fillOval(50, 50, 100, 100);
                break;
            case 1:
                g.setColor(Color.blue);
                g.fillOval(50, 50, 100, 100);
                break;
            case 2:
                g.setColor(Color.green);
                g.fillOval(50, 50, 100, 100);
                break;
        }

        int x = rand.nextInt(getWidth());
        int y = rand.nextInt(getHeight());
        int w = rand.nextInt(getWidth() - x);
        int h = rand.nextInt(getHeight() - y);
        g.fillRect(x, y, w, h);


    }
}
