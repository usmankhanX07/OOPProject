package OOPProject;

import java.awt.*;

public class Coin {
    private int x, y;
    private final int SIZE = 20;
    private boolean collected = false;

    public Coin(int laneX) {
        this.x = laneX + 50;
        this.y = -50;
    }

    public void moveDown() {
        if (!collected) y += 5;
    }

    public void draw(Graphics g) {
        if (!collected) {
            g.setColor(Color.ORANGE);
            g.fillOval(x, y, SIZE, SIZE);
            g.setColor(Color.YELLOW);
            g.drawOval(x, y, SIZE, SIZE);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    public int getY(){
        return y;
    }
}
