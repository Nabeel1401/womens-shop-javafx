package womenshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/womenshop?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "Nabeelahmad@14";


    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println(" Connected to MySQL successfully!");
            }
        } catch (SQLException e) {
            System.err.println(" Database connection failed! Reason: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(" MySQL driver not found! Make sure mysql-connector-j is added.");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println(" Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println(" Error closing database connection: " + e.getMessage());
        }
    }
}
