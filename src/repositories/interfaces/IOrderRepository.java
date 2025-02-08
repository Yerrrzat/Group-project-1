package repositories.interfaces;

import models.Order;

import java.util.List;

public interface IOrderRepository {

    boolean createOrder(Order order);

    Order getOrderById(int id);
    String getFullOrderDescription(int orderId);

    List<Order> getAllOrders();
}
