package com.backend.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public
class OrderItemResponse {
    private Long orderItemId;
    private ProductResponse product;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;
}