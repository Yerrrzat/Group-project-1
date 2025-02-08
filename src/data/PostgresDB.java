package data;

import data.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    private String host;
    private String username;
    private String password;
    private String dbName;

    private Connection connection;

    
    public PostgresDB(String host, String username, String password, String dbName) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
    }

    public PostgresDB(String host, String postgres, String qalzatov8745) {
    }

    @Override
    public Connection getConnection() {
        String connectionUrl = "jdbc:postgresql://" + host + "/" + dbName;
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("org.postgresql.Driver"); 
            connection = DriverManager.getConnection(connectionUrl, username, password);
            return connection;
        } catch (Exception e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; 
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close connection: " + e.getMessage());
            }
        }
    }
}
