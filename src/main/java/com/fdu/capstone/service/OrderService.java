package com.fdu.capstone.service;

import com.fdu.capstone.model.Order;
import com.fdu.capstone.model.User;
import com.fdu.capstone.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        return orderOptional.orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByBuyer(User buyer) {
        return orderRepository.findByBuyer(buyer);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
