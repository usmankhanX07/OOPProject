import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Racing Game UI");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Welcome to the Racing Game! made by FAST students", SwingConstants.CENTER);
//        JLabel label1 = new JLabel("Made by Usman, Aleem, AbdulHadi!", SwingConstants.LEFT);
        frame.add(label);
//        frame.add(label1);

        frame.setVisible(true);
    }
}
