import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private Car car;
    private Truck truck;
    private Helicopter helicopter;
    private boolean gameRunning = true;  // To control game state

    public GamePanel() {
        car = new Car(375, 450);  // Start the car at the bottom of the screen
        truck = new Truck(375, 0);  // Start truck at the top (y = 0)
        helicopter = new Helicopter(-200, 600); // Start helicopter off-screen bottom-left

        setFocusable(true);  // Makes sure the panel listens to keyboard input
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!gameRunning) return;  // Ignore input if game over

                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    car.moveLeft();
                }
                if (key == KeyEvent.VK_RIGHT) {
                    car.moveRight();
                }
                if (key == KeyEvent.VK_UP) {
                    car.moveUp();
                }
                if (key == KeyEvent.VK_DOWN) {
                    car.moveDown();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);  // Set background to white

        if (gameRunning) {
            car.draw(g);
            truck.draw(g);
            helicopter.draw(g);
        } else {
            // Game over screen
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over!", 250, 300);
        }
    }

    public void startGame() {
        Timer timer = new Timer(1000 / 10, e -> {  // 60 FPS
            if (gameRunning) {
                truck.moveDown();  // Truck moves down
                helicopter.flyDiagonally();  // Helicopter flies across screen
                checkCollision();
            }
            repaint();
        });
        timer.start();
    }

    private void checkCollision() {
        Rectangle carBounds = car.getBounds();
        Rectangle truckBounds = truck.getBounds();
        if (carBounds.intersects(truckBounds)) {
            gameRunning = false;
        }
    }
}
