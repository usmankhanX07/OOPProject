import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Truck extends Vehicle {
    private Image truckImage;
    private boolean useImage = false;

    Truck(int startX, int startY) {
        super(120, 200, startX, startY);

        try {
            truckImage = new ImageIcon("C:\\Users\\k243032\\Desktop\\OOPProject\\truck1.jpg").getImage();
            useImage = true;
        } catch (Exception e) {
            System.out.println("Could not load car image. Using shape instead.");
        }
    }

    @Override
    public void moveLeft() {
        x-=5;
    }

    @Override
    public void moveRight() {
        x+=5;
    }

    @Override
    public void moveUp() {
        y-=5;
    }

    @Override
    public void moveDown() {
        y += 5;
    }


    public void draw(Graphics g) {
        if (useImage && truckImage != null) {
            g.drawImage(truckImage, x, y, WIDTH, HEIGHT, null);
        } else {
            Graphics2D g2 = (Graphics2D) g;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
