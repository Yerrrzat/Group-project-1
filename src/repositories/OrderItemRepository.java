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
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "INSERT INTO order_items(order_id, product_id, quantity, price) VALUES(?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, orderItem.getOrderId());
            st.setInt(2, orderItem.getProductId());
            st.setInt(3, orderItem.getQuantity());
            st.setBigDecimal(4, orderItem.getPrice());
            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "SELECT * FROM order_items WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("price")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "SELECT * FROM order_items WHERE order_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            List<OrderItem> orderItems = new ArrayList<>();
            while (rs.next()) {
                OrderItem orderItem = new OrderItem(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("price")
                );
                orderItems.add(orderItem);
            }
            return orderItems;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }
}