package ui;

import model.Player;
import service.GameService;
import javax.swing.*;
import java.awt.*;

public class BattleFrame {

    JFrame frame;
    JLabel titleLabel, player1Label, player2Label, turnLabel;
    JLabel player1Icon, player2Icon;
    JProgressBar player1Bar, player2Bar;
    JTextArea battleLog;
    JScrollPane scrollPane;

    JButton attackButton, healButton, defendButton;
    Player player1, player2;
    GameService gameService;
    boolean player1Turn = true;

    public BattleFrame() {

        frame = new JFrame("Battle Game");
        frame.getContentPane().setBackground(new Color(25, 25, 35));

        player1 = new Player("Player 1", 100, 20);
        player2 = new Player("Player 2", 100, 20);

        gameService = new GameService();

        titleLabel = new JLabel("MULTIPLAYER BATTLE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(Color.YELLOW);

        player1Label = new JLabel("Player 1 HP: 100");
        player2Label = new JLabel("Player 2 HP: 100");
        turnLabel = new JLabel("Turn: Player 1");

        player1Label.setFont(new Font("Arial", Font.BOLD, 22));
        player2Label.setFont(new Font("Arial", Font.BOLD, 22));
        turnLabel.setFont(new Font("Arial", Font.BOLD, 20));

        player1Label.setForeground(Color.WHITE);
        player2Label.setForeground(Color.WHITE);
        turnLabel.setForeground(Color.CYAN);

        player1Bar = new JProgressBar(0, 100);
        player1Bar.setValue(100);
        player1Bar.setStringPainted(true);
        player1Bar.setForeground(Color.GREEN);

        player2Bar = new JProgressBar(0, 100);
        player2Bar.setValue(100);
        player2Bar.setStringPainted(true);
        player2Bar.setForeground(Color.GREEN);

        attackButton = new JButton("Attack");
        healButton = new JButton("Heal");
        defendButton = new JButton("Defend");

        attackButton.setBackground(Color.RED);
        attackButton.setForeground(Color.WHITE);

        healButton.setBackground(Color.GREEN);
        healButton.setForeground(Color.WHITE);

        defendButton.setBackground(Color.BLUE);
        defendButton.setForeground(Color.WHITE);

        attackButton.setFocusPainted(false);
        healButton.setFocusPainted(false);
        defendButton.setFocusPainted(false);

        attackButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        healButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        defendButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        player1Icon = new JLabel("Player 1");
        player2Icon = new JLabel("Player 2");

        player1Icon.setFont(new Font("Arial", Font.BOLD, 20));
        player2Icon.setFont(new Font("Arial", Font.BOLD, 20));

        player1Icon.setForeground(Color.RED);
        player2Icon.setForeground(Color.CYAN);

        player1Icon.setHorizontalAlignment(SwingConstants.CENTER);
        player2Icon.setHorizontalAlignment(SwingConstants.CENTER);

        player1Icon.setBounds(80, 140, 220, 30);
        player2Icon.setBounds(560, 140, 220, 30);

        battleLog = new JTextArea();
        battleLog.setEditable(false);
        battleLog.setBackground(Color.BLACK);
        battleLog.setForeground(Color.GREEN);
        battleLog.setFont(new Font("Consolas", Font.PLAIN, 14));
        battleLog.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        scrollPane = new JScrollPane(battleLog);

        titleLabel.setBounds(210, 10, 350, 40);

        player1Icon.setBounds(80, 110, 100, 100);
        player2Icon.setBounds(560, 110, 100, 100);

        player1Label.setBounds(250, 60, 300, 40);
        player1Bar.setBounds(250, 100, 180, 25);

        player2Label.setBounds(250, 140, 300, 40);
        player2Bar.setBounds(250, 180, 180, 25);

        turnLabel.setBounds(280, 240, 300, 40);

        attackButton.setBounds(100, 320, 140, 45);
        healButton.setBounds(280, 320, 140, 45);
        defendButton.setBounds(460, 320, 140, 45);

        scrollPane.setBounds(150, 400, 420, 100);

        attackButton.addActionListener(e -> playTurn("attack"));
        healButton.addActionListener(e -> playTurn("heal"));
        defendButton.addActionListener(e -> playTurn("defend"));

        frame.add(titleLabel);
        frame.add(player1Icon);
        frame.add(player2Icon);
        frame.add(player1Label);
        frame.add(player2Label);
        frame.add(player1Bar);
        frame.add(player2Bar);
        frame.add(turnLabel);
        frame.add(attackButton);
        frame.add(healButton);
        frame.add(defendButton);
        frame.add(scrollPane);

        frame.setSize(700, 570);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void playTurn(String action) {

        Player currentPlayer = player1Turn ? player1 : player2;
        Player opponent = player1Turn ? player2 : player1;

        if (action.equals("attack")) {

            if (player1Turn) {
                player1Icon.setLocation(120, 110);
            } else {
                player2Icon.setLocation(520, 110);
            }

            gameService.attack(currentPlayer, opponent);
            battleLog.append(currentPlayer.getName() + " attacked " + opponent.getName() + "\n");

            Timer timer = new Timer(200, e -> {
                player1Icon.setLocation(80, 110);
                player2Icon.setLocation(560, 110);
            });

            timer.setRepeats(false);
            timer.start();

        } else if (action.equals("heal")) {
            gameService.heal(currentPlayer);
            battleLog.append(currentPlayer.getName() + " healed\n");

        } else {
            gameService.defend(currentPlayer);
            battleLog.append(currentPlayer.getName() + " defended\n");
        }

        player1Label.setText("Player 1 HP: " + player1.getHealth());
        player2Label.setText("Player 2 HP: " + player2.getHealth());

        player1Bar.setValue(player1.getHealth());
        player2Bar.setValue(player2.getHealth());

        if (player1.getHealth() <= 30) player1Bar.setForeground(Color.RED);
        else if (player1.getHealth() <= 60) player1Bar.setForeground(Color.ORANGE);
        else player1Bar.setForeground(Color.GREEN);

        if (player2.getHealth() <= 30) player2Bar.setForeground(Color.RED);
        else if (player2.getHealth() <= 60) player2Bar.setForeground(Color.ORANGE);
        else player2Bar.setForeground(Color.GREEN);

        String winner = gameService.checkWinner(player1, player2);

        if (winner != null) {
            int choice = JOptionPane.showConfirmDialog(
                    frame,
                    winner + " Wins! Play Again?",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION
            );

            frame.dispose();

            if (choice == JOptionPane.YES_OPTION) {
                new BattleFrame();
            } else {
                new GameFrame();
            }
            return;
        }

        player1Turn = !player1Turn;
        turnLabel.setText("Turn: " + (player1Turn ? "Player 1" : "Player 2"));
    }
}