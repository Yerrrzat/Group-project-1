package controllers.interfaces;

public interface IOrderController {
    String createOrder(int user_id, String order_date, String status, double total_price);
    String getOrderById(int id);
    String getAllOrders();
    String getFullOrderDescription(int orderId);
}
