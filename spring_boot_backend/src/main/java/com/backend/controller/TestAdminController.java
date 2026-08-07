package com.backend.controller;

import com.backend.repository.OrderRepository;
import com.backend.repository.ProductRepository;
import com.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/test")
@RequiredArgsConstructor
public class TestAdminController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @GetMapping("/users")
    public ResponseEntity<Long> testUsers() {
        return ResponseEntity.ok(userRepository.count());
    }

    @GetMapping("/products")
    public ResponseEntity<Long> testProducts() {
        return ResponseEntity.ok(productRepository.count());
    }

    @GetMapping("/orders")
    public ResponseEntity<Long> testOrders() {
        return ResponseEntity.ok(orderRepository.count());
    }

    @GetMapping("/revenue")
    public ResponseEntity<String> testRevenue() {
        return ResponseEntity.ok(String.valueOf(orderRepository.calculateTotalRevenue()));
    }
}
