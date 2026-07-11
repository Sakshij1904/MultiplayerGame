package ui;
import javax.swing.*;
import model.User;
import service.UserService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class RegisterFrame {

    JFrame frame;
    JLabel titleLabel ,userLabel, passLabel;
    JTextField userField;
    JPasswordField passField;
    JButton registerButton;

    public RegisterFrame() {

        frame = new JFrame("Register");
        frame.getContentPane().setBackground(new java.awt.Color(240, 230, 255));

        titleLabel = new JLabel("Register Account");
        titleLabel.setBounds(130, 20, 200, 30);
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));


        userLabel = new JLabel("Username:");
        passLabel = new JLabel("Password:");
        userLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        passLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

        userField = new JTextField();
        passField = new JPasswordField();

        registerButton = new JButton("Register");
        registerButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        registerButton.setFocusPainted(false);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = userField.getText().trim();
                String password = new String(passField.getPassword()).trim();
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Fields cannot be empty");
                    return;
                }

                User user = new User();
                user.setUsername(username);
                user.setPassword(password);


                UserService userService = new UserService();
                userService.registerUser(user);

                JOptionPane.showMessageDialog(frame, "Registration Successful");
                new LoginFrame();
                frame.dispose();
            }
        });

        titleLabel.setBounds(120, 20, 200, 30);
        userLabel.setBounds(80, 80, 200, 30);
        userField.setBounds(180, 80, 150, 30);

        passLabel.setBounds(80, 130, 100, 30);
        passField.setBounds(180, 130, 150, 30);

        registerButton.setBounds(140, 210, 120, 35);

        frame.add(titleLabel);
        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(registerButton);

        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}