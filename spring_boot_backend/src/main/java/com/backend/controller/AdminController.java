package com.backend.controller;

import com.backend.dto.AdminAnalyticsResponse;
import com.backend.entity.Order;
import com.backend.repository.OrderRepository;
import com.backend.repository.ProductRepository;
import com.backend.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin endpoints for analytics and management")
public class AdminController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @GetMapping("/analytics")
    public ResponseEntity<AdminAnalyticsResponse> getAnalytics() {
        long totalUsers = userRepository.count();
        long totalProducts = productRepository.count();
        long totalOrders = orderRepository.count();
        BigDecimal totalRevenue = orderRepository.calculateTotalRevenue();
        long pendingOrders = orderRepository.countByStatus(Order.OrderStatus.PENDING);
        long lowStockProducts = productRepository.countByStockLessThan(10); // threshold

        AdminAnalyticsResponse response = new AdminAnalyticsResponse(
                totalUsers,
                totalProducts,
                totalOrders,
                totalRevenue.doubleValue(),
                pendingOrders,
                lowStockProducts
        );

        return ResponseEntity.ok(response);
    }
}
