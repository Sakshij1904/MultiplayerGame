package service;
import db.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserService {
    //user register
    public void registerUser(User user) {
        if(user.getUsername().trim().isEmpty() || user.getPassword().trim().isEmpty()){
            return;
        }
        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";

        try {
            Connection conn = DBConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());

            pstmt.executeUpdate();

            System.out.println("User Registered Successfully");
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //login validation
    public boolean loginUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";

        try {
            Connection conn = DBConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                conn.close();
                return true;
            }

            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}

