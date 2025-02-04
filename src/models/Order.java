package models;

import java.sql.Timestamp;

public class Order {
    private int id;
    private int user_id;
    private String order_date;
    private String status;
    private double total_price;


    public Order(int id, String status) {
        this.id = id;
        this.status = status;
    }


    public Order(int userId, String orderDate, String status, double totalPrice) {
        this.user_id = userId;
        this.order_date = String.valueOf(orderDate);
        this.status = status;
        this.total_price = totalPrice;
    }

    public Order(int id, int userId, String orderDate, String status, double totalPrice) {
        this(userId, orderDate, status, totalPrice);
        this.id = id;
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

    public String getOrderDate() {
        return order_date;
    }

    public void setOrderDate(String orderDate) {
        this.order_date = order_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public double getTotalPrice() {
        return total_price;
    }

    public void setTotalPrice(double totalPrice) {
        this.total_price = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + user_id +
                ", orderDate=" + order_date +
                ", status='" + status + '\'' +
                ", totalPrice=" + total_price +

                '}';
    }
}