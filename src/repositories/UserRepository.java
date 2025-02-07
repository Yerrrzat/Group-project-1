package repositories;

import data.interfaces.IDB;
import models.User;
import repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
        try (Connection conn = db.getConnection()) {
            String sql = "INSERT INTO users(name, surname, email, password, address, phone) VALUES (?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setString(5, user.getAddress());
            st.setString(6, user.getPhone());
            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }
    
    @Override
    public User getUserById(int id) {
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
                        rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getString("phone"));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT * FROM users";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
                        rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getString("phone")));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return users;
    }

    @Override
    public boolean deleteUser(int id) {
        try (Connection conn = db.getConnection()) {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateUser(int id, String name, String surname) {
        try (Connection conn = db.getConnection()) {
            String sql = "UPDATE users SET name = ?, surname = ? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, surname);
            st.setInt(3, id);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
                        rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getString("phone"));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public int getUserIdByEmail(String email) {
        User user = getUserByEmail(email);
        return (user != null) ? user.getId() : -1;
    }

    @Override
    public String getUserRoleById(int userId) {
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT role FROM users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return "USER"; // Default role if not found
    }
}
