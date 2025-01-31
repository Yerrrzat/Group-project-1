package models;

import java.sql.Timestamp;
import java.util.List;

public class Order {
    private int id;
    private int user_id;
    private Timestamp orderDate;
    private String status;
    private List<OrderItem> orderItems;

    public Order(int userId, String status) {
        this.user_id = user_id;
        this.status = status;
    }

    public Order(int id, int userId, Timestamp orderDate, String status) {
        this(userId, status);
        this.id = id;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int userid) {
        this.user_id = userid;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + user_id +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}