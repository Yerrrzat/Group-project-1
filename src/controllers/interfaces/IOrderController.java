package controllers.interfaces;

public interface IOrderController {
    String createOrder(int userId, String status);

    String getOrderById(int id);

    String getAllOrders();
}
