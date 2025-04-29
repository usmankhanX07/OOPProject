import javax.swing.*;
import java.awt.*;

public class Helicopter extends Vehicle {
    private Image helicopterImage;
    private boolean useImage = false;

    Helicopter(int startX, int startY) {
        super(200, 100, startX, startY);

        try {
            helicopterImage = new ImageIcon("C:\\Users\\k243032\\Desktop\\OOPProject\\heli.jpg").getImage();
            useImage = true;
        } catch (Exception e) {
            System.out.println("Could not load helicopter image. Using shape instead.");
        }
    }

    public void flyDiagonally() {
        x += 5;
        y -= 3;
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
            y -= 20;
        }
    }

    public void moveDown() {
        if (y < 450) {
            y += 10;
        }
    }

    public void draw(Graphics g) {
        if (useImage && helicopterImage != null) {
            g.drawImage(helicopterImage, x, y, WIDTH, HEIGHT, null);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
