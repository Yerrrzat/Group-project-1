package models;

public class OrderItem {
    private int id;
    private int order_id;
    private int device_id;
    private int quantity;
    private int price;

    public OrderItem(int orderId, int device_id, int quantity, int price) {
        this.order_id = orderId;
        this.device_id = device_id;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem(int id, int orderId, int device_id, int quantity, int price) {
        this(orderId, device_id, quantity, price);
        this.id = id;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return order_id;
    }

    public void setOrderId(int orderId) {
        this.order_id = orderId;
    }

    public int getProductId() {
        return device_id;
    }

    public void setProductId(int productId) {
        this.device_id = device_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + order_id +
                ", productId=" + device_id +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}