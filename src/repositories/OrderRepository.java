package repositories;

import data.interfaces.IDB;
import models.Order;
import repositories.interfaces.IOrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private final IDB db;

    public OrderRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createOrder(Order order) {
        try (Connection conn = db.getConnection();
             PreparedStatement st = conn.prepareStatement(
                     "INSERT INTO orders (user_id, order_date, status, total_price) VALUES (?, ?, ?, ?)"
             )) {

            st.setInt(1, order.getUserId());
            st.setString(2,order.getOrderDate());
            st.setString(3, order.getStatus());
            st.setDouble(4, order.getTotalPrice());
            st.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Order getOrderById(int id) {
        try (Connection conn = db.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM orders WHERE id = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("order_date"),
                        rs.getString("status"),
                        rs.getDouble("total_price") // Added total_price
                );
            }

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection conn = db.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM orders")) {

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("order_date"),
                        rs.getString("status"),
                        rs.getDouble("total_price") // Added total_price
                ));
            }
            return orders;

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }
}