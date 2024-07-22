package com.fdu.capstone.controller;

import com.fdu.capstone.model.Order;
import com.fdu.capstone.model.Product;
import com.fdu.capstone.model.User;
import com.fdu.capstone.service.OrderService;
import com.fdu.capstone.service.ProductService;
import com.fdu.capstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserService userService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User currentUser = userService.findByEmail(email);

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        order.setBuyerId(currentUser.getId());

        if (order.getProduct() != null && order.getProduct().getId() != null) {
            Product product = productService.getProductById(order.getProduct().getId());
            if (product == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            order.setProduct(product);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User currentUser = userService.findByEmail(email);

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Order> orders = orderService.getOrdersByBuyerId(currentUser.getId());
        return ResponseEntity.ok(orders);
    }
}
//package com.fdu.capstone.controller;
//
//import com.fdu.capstone.model.Order;
//import com.fdu.capstone.model.Product;
//import com.fdu.capstone.service.OrderService;
//import com.fdu.capstone.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/orders")
//public class OrderController {
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private ProductService productService;
//
//    private static final Long FIXED_USER_ID = 1L; // 设置固定用户ID
//
//    @PostMapping
//    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//        // 使用固定用户ID
//        Long fixedUserId = FIXED_USER_ID;
//
//        order.setBuyerId(fixedUserId);
//
//        if (order.getProduct() != null && order.getProduct().getId() != null) {
//            Product product = productService.getProductById(order.getProduct().getId());
//            if (product == null) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//            }
//            order.setProduct(product);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        Order createdOrder = orderService.createOrder(order);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
//        Order order = orderService.getOrderById(id);
//        return order != null ? ResponseEntity.ok(order) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Order>> getAllOrders() {
//        List<Order> orders = orderService.getAllOrders();
//        return ResponseEntity.ok(orders);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
//        try {
//            orderService.deleteOrder(id);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @GetMapping("/my-orders")
//    public ResponseEntity<List<Order>> getMyOrders() {
//        // 使用固定用户ID
//        Long fixedUserId = FIXED_USER_ID;
//
//        List<Order> orders = orderService.getOrdersByBuyerId(fixedUserId);
//        return ResponseEntity.ok(orders);
//    }
//}

