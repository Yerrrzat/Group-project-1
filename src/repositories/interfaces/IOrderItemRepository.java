package repositories.interfaces;

import models.OrderItem;

import java.util.List;

public interface IOrderItemRepository {
    boolean createOrderItem(OrderItem orderItem);

    OrderItem getOrderItemById(int id);

    List<OrderItem> getOrderItemsByOrderId(int orderId);
}
