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
            st.setString(2, order.getOrderDate());
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
                        rs.getDouble("total_price")
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
                        rs.getDouble("total_price")
                ));
            }
            return orders;

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String getFullOrderDescription(int orderId) {
        String sql = "SELECT o.id AS order_id, o.order_date, o.status, o.total_price, " +
                "u.id AS user_id, u.name AS user_name, u.email AS user_email, " +
                "oi.device_id, oi.quantity, oi.price, d.name AS device_name " +
                "FROM orders o " +
                "JOIN users u ON o.user_id = u.id " +
                "LEFT JOIN order_items oi ON o.id = oi.order_id " +
                "LEFT JOIN devices d ON oi.device_id = d.id " +
                "WHERE o.id = ?";

        StringBuilder orderDetails = new StringBuilder();

        try (Connection conn = db.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();

            boolean isFirstRow = true;
            while (rs.next()) {
                if (isFirstRow) {
                    orderDetails.append("Order ID: ").append(rs.getInt("order_id")).append("\n")
                            .append("Date: ").append(rs.getString("order_date")).append("\n")
                            .append("Status: ").append(rs.getString("status")).append("\n")
                            .append("Total Price: ").append(rs.getDouble("total_price")).append("\n\n")
                            .append("Buyer Details:\n")
                            .append("User ID: ").append(rs.getInt("user_id")).append("\n")
                            .append("Name: ").append(rs.getString("user_name")).append("\n")
                            .append("Email: ").append(rs.getString("user_email")).append("\n\n")
                            .append("Order Items:\n");
                    isFirstRow = false;
                }

                int deviceId = rs.getInt("device_id");
                if (deviceId != 0) {
                    orderDetails.append(" - Device ID: ").append(deviceId).append("\n")
                            .append("   Name: ").append(rs.getString("device_name")).append("\n")
                            .append("   Quantity: ").append(rs.getInt("quantity")).append("\n")
                            .append("   Price: ").append(rs.getDouble("price")).append("\n\n");
                }
            }

            return orderDetails.length() > 0 ? orderDetails.toString() : "Order not found or has no items.";

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
            return "Error retrieving order details.";
        }
    }
}
