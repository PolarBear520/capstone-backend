package com.fdu.capstone.controller;

import com.fdu.capstone.model.Order;
import com.fdu.capstone.model.Product;
import com.fdu.capstone.service.OrderService;
import com.fdu.capstone.service.ProductService;
import com.fdu.capstone.config.FixedUserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FixedUserConfig fixedUserConfig;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        // 使用固定的用户ID
        order.setBuyerId(fixedUserConfig.getFixedUserId());

        // 获取并设置产品实例
        if (order.getProduct() != null && order.getProduct().getId() != null) {
            Product product = productService.getProductById(order.getProduct().getId());
            if (product == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 如果product为null，返回400错误
            }
            order.setProduct(product);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 确保传递的订单包含有效的产品ID
        }

        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/my-orders")
    public ResponseEntity<List<Order>> getMyOrders() {
        // 使用固定的用户ID
        Long buyerId = fixedUserConfig.getFixedUserId();
        List<Order> orders = orderService.getOrdersByBuyerId(buyerId);
        return ResponseEntity.ok(orders);
    }
}


