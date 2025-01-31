package repositories;

import data.interfaces.IDB;
import models.OrderItem;
import repositories.interfaces.IOrderItemRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository implements IOrderItemRepository {
    private final IDB db;

    public OrderItemRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createOrderItem(OrderItem orderItem) {
        try (Connection conn = db.getConnection();
             PreparedStatement st = conn.prepareStatement(
                     "INSERT INTO order_items(order_id, device_id, quantity, price) VALUES (?, ?, ?, ?)"
             )) {

            st.setInt(1, orderItem.getOrderId());
            st.setInt(2, orderItem.getDeviceId());
            st.setInt(3, orderItem.getQuantity());
            st.setDouble(4, orderItem.getPrice()); // Changed setInt to setDouble
            st.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        try (Connection conn = db.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM order_items WHERE id = ?")) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("device_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("price") // Changed getInt to getDouble
                );
            }

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        try (Connection conn = db.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM order_items WHERE order_id = ?")) {

            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                orderItems.add(new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("device_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("price") // Changed getInt to getDouble
                ));
            }
            return orderItems;

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }
}