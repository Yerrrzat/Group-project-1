package controllers.interfaces;

import java.math.BigDecimal;

public interface IOrderItemController {
    String createOrderItem(int orderId, int productId, int quantity, BigDecimal price);

    String getOrderItemById(int id);

    String getOrderItemsByOrderId(int orderId);
}
