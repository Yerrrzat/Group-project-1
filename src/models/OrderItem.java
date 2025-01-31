package models;

public class OrderItem {
    private int id;
    private int order_id;
    private int device_id;
    private int quantity;
    private double price; // Changed from int to double

    public OrderItem(int orderId, int deviceId, int quantity, double price) {
        this.order_id = orderId;
        this.device_id = deviceId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem(int id, int orderId, int deviceId, int quantity, double price) {
        this(orderId, deviceId, quantity, price);
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

    public int getDeviceId() {
        return device_id;
    }

    public void setDeviceId(int deviceId) {
        this.device_id = deviceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() { // Changed return type from int to double
        return price;
    }

    public void setPrice(double price) { // Changed parameter type from int to double
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + order_id +
                ", deviceId=" + device_id +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
