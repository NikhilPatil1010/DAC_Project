package com.backend.controller;

import com.backend.dto.*;
import com.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Pass userId as request parameter
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestParam Long userId,
                                                     @RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(userId, request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getUserOrders(@RequestParam Long userId) {
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@RequestParam Long userId,
                                                      @PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId, userId));
    }
}
