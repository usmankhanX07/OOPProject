import java.awt.*;
import javax.swing.*;

public class Car extends Vehicle{

    private Image carImage;
    private boolean useImage = false;  // Toggle this to true to use image instead of shapes

     Car(int startX, int startY) {
         super(60,100, startX, startY);

        // Load image (make sure car.png is in your project directory)
        try {
            carImage = new ImageIcon("C:\\Users\\k243032\\Desktop\\OOPProject\\car.jpg").getImage();
            useImage = true;  // Set to true if image loads successfully
        } catch (Exception e) {
            System.out.println("Could not load car image. Using shape instead.");
        }
    }

    public void moveLeft() {
        if (x > 200) {
            x -= 20;
        }
    }

    public void moveRight() {
        if (x < 750 - WIDTH-200) {
            x += 20;
        }
    }

    public void moveUp() {
        if (y > 0) {
            y-=40;
        }
    }

    public void moveDown() {
        if (y < 450) {
            y+=40;
        }
    }
    public void draw(Graphics g) {
        if (useImage && carImage != null) {
            g.drawImage(carImage, x, y, WIDTH, HEIGHT, null);
        } else {
            Graphics2D g2 = (Graphics2D) g;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
