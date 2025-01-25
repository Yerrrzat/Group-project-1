import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBC {

    // URL базы данных
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "kundyz";
    private static final String PASSWORD = "Kundyz2007";

    public static void main(String[] args) {
        Connection conn = null;

        try {
            // Подключение к базе данных
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Подключение к базе данных успешно!");

            // Пример запроса
            String query = "SELECT * FROM users";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            // Вывод результатов
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("-------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Закрытие подключения
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Соединение закрыто.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

