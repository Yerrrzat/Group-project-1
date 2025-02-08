package controllers;

import controllers.interfaces.IOrderController;
import models.Order;
import repositories.interfaces.IOrderRepository;

public class OrderController implements IOrderController {
    private final IOrderRepository orderRepository;

    public OrderController(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public String createOrder(int user_id, String order_date, String status, double total_price) {
        return orderRepository.createOrder(new Order(user_id, order_date, status, total_price))
                ? "Order created successfully"
                : "Order creation failed";
    }

    @Override
    public String getOrderById(int id) {
        return orderRepository.getOrderById(id) != null
                ? orderRepository.getOrderById(id).toString()
                : "Order not found";
    }

    @Override
    public String getAllOrders() {
        return orderRepository.getAllOrders().toString();
    }

    @Override
    public String getFullOrderDescription(int orderId) {
        return orderRepository.getFullOrderDescription(orderId);
    }
}
