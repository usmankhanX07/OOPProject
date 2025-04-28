import java.awt.*;
import javax.swing.*;

public class Car extends Vehicle{


    private Image carImage;
    private boolean useImage = false;  // Toggle this to true to use image instead of shapes

     Car(int startX, int startY) {
         super(100,100, startX, startY);

        // Load image (make sure car.png is in your project directory)
        try {
            carImage = new ImageIcon("C:\\Users\\TEMP.KHIFAST.002\\Desktop\\OOP\\src\\car.jpg").getImage();
            useImage = true;  // Set to true if image loads successfully
        } catch (Exception e) {
            System.out.println("Could not load car image. Using shape instead.");
        }
    }

    public void moveLeft() {
        if (x > 0) {
            x -= 10;
        }
    }

    public void moveRight() {
        if (x < 750 - WIDTH) {
            x += 10;
        }
    }

    public void moveUp() {
        if (y > 0) {
            y-=20;
        }
    }

    public void moveDown() {
        if (y < 450) {
            y+=20;
        }
    }
    public void draw(Graphics g) {
        if (useImage && carImage != null) {
            g.drawImage(carImage, x, y, WIDTH, HEIGHT, null);
        } else {
            Graphics2D g2 = (Graphics2D) g;
//
//            // Car body
//            g2.setColor(Color.BLUE);
//            g2.fillRect(x, y + 20, WIDTH, HEIGHT - 20);
//
//            // Car cabin
//            g2.setColor(Color.CYAN);
//            g2.fillRect(x + 10, y, WIDTH - 20, 30);

//            // Wheels
//            g2.setColor(Color.BLACK);
//            g2.fillOval(x, y + HEIGHT - 10, 15, 15);  // rear wheel
//            g2.fillOval(x + WIDTH - 15, y + HEIGHT - 10, 15, 15);  // front wheel
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
