package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
    public static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:Sqlite:game.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Database connected successfully");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return conn;
    }
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT NOT NULL,"
                + "password TEXT NOT NULL)";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Users table created successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

