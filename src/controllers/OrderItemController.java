package controllers;

import controllers.interfaces.IOrderItemController;
import models.OrderItem;
import repositories.interfaces.IOrderItemRepository;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemController implements IOrderItemController {
    private final IOrderItemRepository orderItemRepository;

    public OrderItemController(IOrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public String createOrderItem(int orderId, int device_id, int quantity, int price) {
        OrderItem orderItem = new OrderItem(orderId, device_id, quantity, price);
        boolean created = orderItemRepository.createOrderItem(orderItem);
        return created ? "Order Item created successfully" : "Order Item creation failed";
    }

    @Override
    public String getOrderItemById(int id) {
        OrderItem orderItem = orderItemRepository.getOrderItemById(id);
        return (orderItem == null) ? "Order Item not found" : orderItem.toString();
    }

    @Override
    public String getOrderItemsByOrderId(int orderId) {
        List<OrderItem> orderItems = orderItemRepository.getOrderItemsByOrderId(orderId);
        StringBuilder sb = new StringBuilder();
        for (OrderItem orderItem : orderItems) {
            sb.append(orderItem.toString()).append("\n");
        }
        return sb.toString();
    }
}