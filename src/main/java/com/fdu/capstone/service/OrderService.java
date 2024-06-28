package com.fdu.capstone.service;

import com.fdu.capstone.model.Order;
import com.fdu.capstone.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByBuyerId(userId);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
