import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/your_database"; // ссылка
    private static final String USER = "your_username"; // имя
    private static final String PASSWORD = "your_password"; // пароль

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established successfully!");


        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close connection: " + e.getMessage());
                }
            }
        }
    }
}