package ui;
import javax.swing.*;
public class GameFrame {

    JFrame frame;
    JLabel label;
    JButton startButton, logoutButton;

    public GameFrame() {

        frame = new JFrame("Game Page");
        frame.getContentPane().setBackground(new java.awt.Color(240, 248, 255));

        label = new JLabel("Welcome to Multiplayer Game");
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        startButton = new JButton("Start Game");
        logoutButton = new JButton("Logout");
        startButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        logoutButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

        startButton.addActionListener(e -> {
            new BattleFrame();
            frame.dispose();
        });
        logoutButton.addActionListener(e -> {
            new LoginFrame();
            frame.dispose();
        });

        label.setBounds(120, 70, 400, 40);
        startButton.setBounds(200, 170, 180, 45);
        logoutButton.setBounds(200, 240, 180, 45);

        frame.add(label);
        frame.add(startButton);
        frame.add(logoutButton);

        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
