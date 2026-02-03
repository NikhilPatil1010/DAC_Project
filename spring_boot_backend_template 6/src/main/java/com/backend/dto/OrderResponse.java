package com.backend.dto;
import lombok.Data;
import com.fasterxml.jackson.databind.JsonNode;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long orderId;
    private UserResponse user;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private String status;
    private JsonNode shippingAddress;
    private List<OrderItemResponse> items;
    private PaymentResponse payment;
}
