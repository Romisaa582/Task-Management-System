package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ascom
 */
class DatabaseConnection {

private static final String URL = "jdbc:mysql://localhost:3306/database?useSSL=false&serverTimezone=UTC";
private static final String USER = "root";
private static final String PASSWORD = "";


    private static Connection connection;

    // Only one getConnection method is needed
    public static Connection getConnection() {
    if (connection == null) {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            e.printStackTrace(); // طباعة تفاصيل الخطأ
            System.out.println("Failed to connect to the database.");
        }
    }
    return connection;
}

}
