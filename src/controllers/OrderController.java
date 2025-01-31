package controllers;

import controllers.interfaces.IOrderController;
import models.Order;
import repositories.interfaces.IOrderRepository;

import java.util.List;

public class OrderController implements IOrderController {
    private final IOrderRepository orderRepository;

    public OrderController(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public String createOrder(int userId, String status) {
        Order order = new Order(userId, status);
        boolean created = orderRepository.createOrder(order);
        return created ? "Order created successfully" : "Order creation failed";
    }

    @Override
    public String getOrderById(int id) {
        Order order = orderRepository.getOrderById(id);
        return (order == null) ? "Order not found" : order.toString();
    }

    @Override
    public String getAllOrders() {
        List<Order> orders = orderRepository.getAllOrders();
        StringBuilder sb = new StringBuilder();
        for (Order order : orders) {
            sb.append(order.toString()).append("\n");
        }
        return sb.toString();
    }
}