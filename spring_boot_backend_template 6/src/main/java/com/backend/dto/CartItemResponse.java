package com.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CartItemResponse {
    private Long cartItemId;
    private ProductResponse product;
    private Integer quantity;
    private BigDecimal subtotal;
    private LocalDateTime addedAt;
}