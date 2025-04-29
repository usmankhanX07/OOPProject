import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Vehicle> vehicles = new ArrayList<>(5);

        JFrame frame = new JFrame("High Speed Chase 2");
        GamePanel panel = new GamePanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);  // Set window size
        frame.setResizable(false);  // Disable resizing
        frame.add(panel);
        frame.setVisible(true);

        panel.startGame();  // Start the game
    }

}
