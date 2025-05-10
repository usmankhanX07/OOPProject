package OOPProject;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DifficultySelector {

    private static String selectedDifficulty;

    public static String processDifficulty() {
        // Load the background image
        try {
            Image backgroundImage = javax.imageio.ImageIO.read(new File("C:\\Users\\irfan\\Desktop\\FAST\\OOPLab\\src\\OOPProject\\Pictures\\policeTape.jpeg"));  // Adjust path as needed
        } catch (IOException e) {
            System.out.println("Background image failed to load.");
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Select Difficulty");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);  // Increased size
        frame.setLayout(null);
        frame.setResizable(false);

        frame.getContentPane().setBackground(Color.BLACK);

        ArrayList<JLabel> labels = getJLabels();
        for(JLabel l: labels){
            l.setForeground(Color.RED);
            l.setBounds(50, 70, 400, 50);
            frame.add(l);
        }

        JButton easyBtn = new JButton("Easy");
        JButton mediumBtn = new JButton("Medium");
        JButton hardBtn = new JButton("Hard");
        JButton[] buttons = {easyBtn,mediumBtn,hardBtn};

        Font f1 = new Font("Consolas",Font.BOLD,18);
        int counter = 0;
        for(JButton b: buttons){
            b.setBackground(Color.BLACK);
            b.setForeground(Color.RED);
            b.setFont(f1);
            b.setBounds(190, 150+counter*80, 120, 60);
            counter++;
            frame.add(b);
        }
        
        easyBtn.addActionListener(e -> {
            selectedDifficulty = "easy";
            frame.dispose();
        });

        mediumBtn.addActionListener(e -> {
            selectedDifficulty = "medium";
            frame.dispose();
        });

        hardBtn.addActionListener(e -> {
            selectedDifficulty = "hard";
            frame.dispose();
        });

        frame.setVisible(true);

        // Wait for a difficulty to be selected
        while (selectedDifficulty == null) {
            try {
                Thread.sleep(100);  // Wait a bit before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return selectedDifficulty;
    }

    private static ArrayList<JLabel> getJLabels() {
        JLabel titleLabel = new JLabel("High Speed Chase 2", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 30));

        JLabel difficultyLabel = new JLabel("Choose your difficulty", SwingConstants.CENTER);
        difficultyLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        return new ArrayList<>(Arrays.asList(titleLabel, difficultyLabel));
    }
}
