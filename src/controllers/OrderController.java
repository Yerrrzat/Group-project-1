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
    public String createOrder(int user_id, String order_date, String status, double total_price) {
        Order order = new Order(user_id, status);
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