import db.DBConnection;
import ui.LoginFrame;

public class Main {
    public static void main(String[] args) {
        DBConnection.connect();
        DBConnection.createTable();

        new LoginFrame();
        }
    }




