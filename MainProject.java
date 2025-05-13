package OOPProject;
import javax.swing.*;

public class MainProject {
    public static void main(String[] args) {
        String difficulty = DifficultySelector.processDifficulty();

        JFrame frame = new JFrame("High Speed Chase 2");
        GamePanel panel = new GamePanel(difficulty);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setResizable(false);  // Disable resizing
        frame.add(panel);
        frame.setVisible(true);
        panel.startGame();
    }
}

