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
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "INSERT INTO orders(user_id, status,orderDate, OrderItems) VALUES(?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, order.getUserId());
            st.setString(2, order.getStatus());
            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Order getOrderById(int id) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "SELECT * FROM orders WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("order_date"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "SELECT * FROM orders";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("order_date"),
                        rs.getString("status")
                );
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }
}