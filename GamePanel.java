package OOPProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePanel extends JPanel {
    private Car car;
    private Truck truck;
    private Helicopter helicopter;
    private ArrayList<Coin> coins = new ArrayList<>();
    private int coinCounter = 0;

    private final int LANE_WIDTH = 120;
    private final int NUM_LANES = 5;
    private final int PANEL_WIDTH = 750;
    private Random rand = new Random();
    private boolean gameRunning = true;

    public GamePanel() {
        this.car = new Car(375, 460);
        this.truck = new Truck(200 + rand.nextInt(150), -200);
        this.helicopter = new Helicopter(-200, 600 - rand.nextInt(400));

        spawnCoins();

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!gameRunning) return;

                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) { car.moveLeft(); }
                if (key == KeyEvent.VK_RIGHT) { car.moveRight(); }
                if (key == KeyEvent.VK_UP) { car.moveUp(); }
                if (key == KeyEvent.VK_DOWN) { car.moveDown(); }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (car.didCarImageFailToLoad()) {
            setBackground(Color.BLACK);
            g.setColor(Color.RED);
            g.setFont(new Font("Consolas", Font.BOLD, 24));
            g.drawString("Error: Car image failed to load.", 150, 250);
            g.drawString("Please check your image path or dependencies.", 80, 300);
            return;
        }

        setBackground(Color.black);

        if (gameRunning) {
            drawRoad(g);
            car.draw(g);
            truck.draw(g);
            helicopter.draw(g);

            for (Coin coin : coins) {
                coin.moveDown();
                coin.draw(g);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Score: " + coinCounter, 10, 30);

        } else {
            g.setColor(Color.RED);
            g.setFont(new Font("Consolas", Font.BOLD, 40));
            g.drawString("Game Over!", 270, 300);
        }
    }

    private void spawnCoins() {
        while (coins.size() < 5) {
            int lane = rand.nextInt(3) + 1;
            int x = (PANEL_WIDTH - (LANE_WIDTH * NUM_LANES)) / 2 + lane * LANE_WIDTH;
            coins.add(new Coin(x));
        }
    }

    private void drawRoad(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int startX = (PANEL_WIDTH - (LANE_WIDTH * NUM_LANES)) / 2;

        for (int i = 0; i < NUM_LANES; i++) {
            int x = startX + i * LANE_WIDTH;
            g2d.setColor((i == 0 || i == NUM_LANES - 1) ? Color.GRAY : Color.BLACK);
            g2d.fillRect(x, 0, LANE_WIDTH, getHeight());
        }

        float[] dashPattern = {15, 15};
        Stroke dashedWhite = new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0);
        Stroke dashedYellow = new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0);

        for (int i = 0; i <= NUM_LANES; i++) {
            int x = startX + i * LANE_WIDTH;
            g2d.setColor((i == 0 || i == NUM_LANES) ? Color.GREEN : Color.WHITE);
            g2d.setStroke((i == 0 || i == NUM_LANES) ? dashedYellow : dashedWhite);
            g2d.drawLine(x, 0, x, getHeight());
        }

        g2d.setStroke(new BasicStroke());
    }

    public void startGame() {
        Timer timer = new Timer(1000 / 60, e -> {
            if (gameRunning) {
                truck.moveDown();
                helicopter.flyDiagonally();
                for (Coin coin : coins) {
                    coin.moveDown();
                }
                checkCollision();
                checkCoinCollision();

                if (truck.getY() > getHeight()) {
                    truck = new Truck(200 + rand.nextInt(150), -200);
                }

                if (helicopter.getX() > getWidth() || helicopter.getY() < -100) {
                    helicopter = new Helicopter(-200, 600 - rand.nextInt(400));
                }
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

    private void checkCoinCollision() {
        Rectangle carBounds = car.getBounds();
        Iterator<Coin> iterator = coins.iterator();

        while (iterator.hasNext()) {
            Coin coin = iterator.next();
            if (carBounds.intersects(coin.getBounds())) {
                iterator.remove();
                coinCounter++;
            } else if (coin.getY() > getHeight()) {
                iterator.remove();
            }
        }

        spawnCoins();
    }
}

//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Random;
//
//public class GamePanel extends JPanel {
//    private Car car;
//    private Truck truck;
//    private Helicopter helicopter;
//
//    private ArrayList<Coin> coins = new ArrayList<>();
//    private int coinCounter = 0;
//
//    public GamePanel() {
//        this.car = new Car(375, 450);
//        this.truck = new Truck(375, -100);
//        this.helicopter = new Helicopter(-200, 600);
//
//        spawnCoins();
//
//        setFocusable(true);
//        addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (!gameRunning) return;
//
//                int key = e.getKeyCode();
//                if (key == KeyEvent.VK_LEFT) car.moveLeft();
//                if (key == KeyEvent.VK_RIGHT) car.moveRight();
//                if (key == KeyEvent.VK_UP) car.moveUp();
//                if (key == KeyEvent.VK_DOWN) car.moveDown();
//            }
//        });
//    }
//
//    private void spawnCoins() {
//        for (int i = 0; i < 5; i++) {
//            int lane;
//            int x;
//
//            lane = rand.nextInt(3) + 1;
//            x = (PANEL_WIDTH - (LANE_WIDTH * NUM_LANES)) / 2 + lane * LANE_WIDTH;
//
//            coins.add(new Coin(x));
//        }
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        setBackground(Color.BLACK);
//
//        if (gameRunning) {
//            drawRoad(g);
//            car.draw(g);
//            truck.draw(g);
//            helicopter.draw(g);
//
//            for (Coin coin : coins) {
//                coin.moveDown();
//                coin.draw(g);
//            }
//
//            g.setColor(Color.BLUE);
//            g.setFont(new Font("Arial", Font.BOLD, 24));
//            g.drawString("Score: " + coinCounter, 20, 30);
//
//        } else {
//            g.setColor(Color.RED);
//            g.setFont(new Font("Consolas", Font.BOLD, 40));
//            g.drawString("Game Over!", 250, 300);
//        }
//    }
//
//    private void drawRoad(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        int startX = (PANEL_WIDTH - (LANE_WIDTH * NUM_LANES)) / 2;
//
//        for (int i = 0; i < NUM_LANES; i++) {
//            int x = startX + i * LANE_WIDTH;
//
//            if (i == 0 || i == NUM_LANES - 1) {
//                g2d.setColor(Color.YELLOW);
//                g2d.fillRect(x, 0, LANE_WIDTH, getHeight());
//            } else {
//                g2d.setColor(Color.BLACK);
//                g2d.fillRect(x, 0, LANE_WIDTH, getHeight());
//            }
//        }
//
//        float[] dashPattern = {15, 15};
//        Stroke dashedWhite = new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0);
//        Stroke dashedYellow = new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0);
//
//        for (int i = 0; i <= NUM_LANES; i++) {
//            int x = startX + i * LANE_WIDTH;
//            if (i == 0 || i == NUM_LANES) {
//                g2d.setColor(Color.GREEN);
//                g2d.setStroke(dashedYellow);
//            } else {
//                g2d.setColor(Color.WHITE);
//                g2d.setStroke(dashedWhite);
//            }
//            g2d.drawLine(x, 0, x, getHeight());
//        }
//
//        g2d.setStroke(new BasicStroke());
//    }
//
//    public void startGame() {
//        Timer timer = new Timer(1000 / 15, e -> {
//            if (gameRunning) {
//                truck.moveDown();
//                truck.moveStraight();
//                if (truck.y > PANEL_HEIGHT) {
//                    truck.spawnTruck();
//                }
//                helicopter.flyDiagonally();
//                checkCollision();
//                checkCoinCollision();
//            }
//            repaint();
//        });
//        timer.start();
//    }
//
//    private void checkCollision() {
//        Rectangle carBounds = car.getBounds();
//        Rectangle truckBounds = truck.getBounds();
//        if (carBounds.intersects(truckBounds)) {
//            gameRunning = false;
//        }
//    }
//
//    private void checkCoinCollision() {
//        Rectangle carBounds = car.getBounds();
//        Iterator<Coin> iterator = coins.iterator();
//
//        while (iterator.hasNext()) {
//            Coin coin = iterator.next();
//            if (carBounds.intersects(coin.getBounds())) {
//                iterator.remove();
//                coinCounter++;
//            }
//        }
//
//        if (coins.size() < 3) {
//            spawnCoins();
//        }
//    }
//
//    private Random rand = new Random();
//    private boolean gameRunning = true;
//
//    private final int LANE_WIDTH = 120;
//    private final int NUM_LANES = 5;
//    private final int PANEL_WIDTH = 750;
//    private final int PANEL_HEIGHT = 600;
//}
