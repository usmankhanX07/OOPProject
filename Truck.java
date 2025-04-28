import javax.swing.*;
import java.awt.*;

public class Truck extends Vehicle{
    private Image truckImage;
    private boolean useImage = false;  // Toggle this to true to use image instead of shapes

    Truck(int startX, int startY) {
        super(120,200, startX, startY);

        // Load image (make sure car.png is in your project directory)
        try {
            carImage = new ImageIcon("C:\\Users\\TEMP.KHIFAST.002\\Desktop\\OOP\\src\\car.jpg").getImage();
            useImage = true;  // Set to true if image loads successfully
        } catch (Exception e) {
            System.out.println("Could not load car image. Using shape instead.");
        }
    }

    @Override
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
            y+=10;
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
