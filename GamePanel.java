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
    private Vehicle truck;      // adding runtime polymorphism
    private Helicopter helicopter;
    private ArrayList<Coin> coins = new ArrayList<>();
    private int scoreCount = 0;
    private int timeCounter;

    private final int LANE_WIDTH = 120;
    private final int NUM_LANES = 5;
    private final int PANEL_WIDTH = 750;
    private Random rand = new Random();
    private boolean gameRunning = true;

    private String difficulty;
    private int frameRate;
    private int truckSpeed;
    private String carManeuverability;

    public GamePanel(String difficulty){
        this.difficulty = difficulty;
        incorporateDifficulty();
//        timeCounter = 0;

        this.car = new Car(360, 640, carManeuverability);   //460
        this.truck = new Truck(200 + rand.nextInt(250), -200, truckSpeed);
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

    public void incorporateDifficulty(){
        switch(difficulty){
            case "easy":{
                truckSpeed = 5;
                frameRate = 60;
                carManeuverability = "light";
            }break;
            case "medium":{
                truckSpeed = 7;
                frameRate = 80;
                carManeuverability = "intermediate";
            }break;
            case "hard":{
                truckSpeed = 9;
                frameRate = 100;
                carManeuverability = "expert";
            }break;
            default:truckSpeed = 5; frameRate = 80;
        }
    }

    public void startGame() {
        Timer timer = new Timer(1000 / frameRate, e -> {
            if (gameRunning) {
                truck.moveDown();
                helicopter.flyDiagonallyEastward();
                for (Coin coin : coins) {
                    coin.moveDown();
                }
                checkCollision();
                checkCoinCollision();

                if (truck.getY() > getHeight()) {
                    truck = new Truck(200 + rand.nextInt(260), -200, truckSpeed);
                }

                if (helicopter.getX() > getWidth() || helicopter.getY() < -100) {
                    helicopter = new Helicopter(-200, 600 - rand.nextInt(400));
                }
//                    else{
//                        helicopter = new Helicopter(1000, 600 - rand.nextInt(400));
//                    }

                timeCounter++;
            }
            repaint();
        });
        timer.start();
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
                if(difficulty.equals("hard")){coin.moveDown();}
                coin.draw(g);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Score: " + scoreCount, 10, 30);
            g.drawString("Timer: "+ timeCounter, 10, 60);

        } else {
            g.setColor(Color.RED);
            g.setFont(new Font("Consolas", Font.BOLD, 40));
            g.drawString("Game Over!", 270, 300);
            g.drawString("You survived for "+timeCounter+" seconds", 100,350);
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

    private void checkCollision() {
        Rectangle carBounds = car.getBounds();
        Rectangle truckBounds = truck.getBounds();
        if (carBounds.intersects(truckBounds)) {
            gameRunning = false;
        }
    }

    private void spawnCoins() {
        while (coins.size() < 5) {
            int lane = rand.nextInt(3) + 1;
            int x = (PANEL_WIDTH - (LANE_WIDTH * NUM_LANES)) / 2 + lane * LANE_WIDTH;
            coins.add(new Coin(x));
        }
    }



    private void checkCoinCollision () {
        Rectangle carBounds = car.getBounds();
        Iterator<Coin> iterator = coins.iterator();

        while (iterator.hasNext()) {
            Coin coin = iterator.next();
            if (carBounds.intersects(coin.getBounds())) {
                iterator.remove();
                scoreCount++;
            } else if (coin.getY() > getHeight()) {
                iterator.remove();
            }
        }

        spawnCoins();
    }

}
