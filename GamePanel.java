    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;

    public class GamePanel extends JPanel {
        private Car car;

        public GamePanel() {
            car = new Car(375, 450);  // Start the car at the bottom of the screen

            setFocusable(true);  // Makes sure the panel listens to keyboard input
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_LEFT) {
                        car.moveLeft();  // Move the car left
                    }
                    if (key == KeyEvent.VK_RIGHT) {
                        car.moveRight();  // Move the car right
                    }
                    if (key == KeyEvent.VK_UP) {
                        car.moveUp();  // Move the car left
                    }
                    if (key == KeyEvent.VK_DOWN) {
                        car.moveDown();  // Move the car left
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);  // Set background to gray (representing the road)
            car.draw(g);  // Draw the car
        }

        // Method to start the game loop
        public void startGame() {
            Timer timer = new Timer(1000 / 60, e -> repaint());  // Refresh 60 times per second
            timer.start();
        }
    }
