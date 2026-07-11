package ui;
import javax.swing.*;

import service.UserService;
public class LoginFrame {
    JFrame frame;
    JLabel titleLabel,userLabel, passLabel;
    JTextField userField;
    JPasswordField passField;
    JButton loginButton, registerButton;

    public LoginFrame() {

        frame = new JFrame("Login");
        frame.getContentPane().setBackground(new java.awt.Color(230, 240, 255));

        titleLabel = new JLabel("Multiplayer Battle Game");
        titleLabel.setBounds(110, 20, 250, 30);
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));

        userLabel = new JLabel("Username:");
        passLabel = new JLabel("Password:");
        userLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        passLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

        userField = new JTextField();
        passField = new JPasswordField();

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        loginButton.setFocusPainted(false);
        registerButton.setFocusPainted(false);
        loginButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        registerButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

        loginButton.addActionListener(e -> {

            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter username and password");
                return;
            }

            UserService userService = new UserService();

            boolean login = userService.loginUser(username, password);

            if (login) {
                JOptionPane.showMessageDialog(frame, "Login Successful");
                new GameFrame();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
            }
        });
        registerButton.addActionListener(e -> {
            new RegisterFrame();
            frame.dispose();
        });

        userLabel.setBounds(80, 80, 100, 30);
        userField.setBounds(180, 80, 150, 30);

        passLabel.setBounds(80, 130, 100, 30);
        passField.setBounds(180, 130, 150, 30);

        loginButton.setBounds(100, 200, 100, 35);
        registerButton.setBounds(220, 200, 100, 35);

        frame.add(titleLabel);
        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginButton);
        frame.add(registerButton);


        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

