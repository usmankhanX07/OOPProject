import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Car Game");
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private int carX = 180;
    private int carY = 500;
    private int carWidth = 40;
    private int carHeight = 60;
    private int speed = 10;
    private Timer timer;

    public GamePanel() {
        setBackground(Color.GREEN);
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(Color.WHITE);
        g.fillRect(100, 0, 200, getHeight());


        g.setColor(Color.RED);
        g.fillRect(carX, carY, carWidth, carHeight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT && carX > 100) {
            carX -= speed;
        }
        if (key == KeyEvent.VK_RIGHT && carX + carWidth < 300) {
            carX += speed;
        }

        repaint();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
