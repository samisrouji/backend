package com.sami.ecommerce.service;

import com.sami.ecommerce.entity.Order;
import com.sami.ecommerce.repository.OrderRepository;
import com.sami.ecommerce.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order create(Order order) {
        // Verify cart exists
        cartRepository.findById(order.getCart().getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        order.setStatus(Order.Status.PENDING);
        return orderRepository.save(order);
    }

    public Order updateStatus(Long orderId, Order.Status status) {
        Order order = getById(orderId);
        order.setStatus(status);

        // Only set orderDate when status is PAID
        if (status == Order.Status.PAID && order.getOrderDate() == null) {
            order.setOrderDate(java.time.LocalDateTime.now());
        }

        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
