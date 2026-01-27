package com.backend.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ProductResponse {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal originalPrice;
    private BigDecimal discount;
    private Integer stock;
    private String imageUrl;
    private CategoryResponse category;
    private BigDecimal rating;
    private Integer reviewCount;
    private String brand;
    private Boolean dealOfDay;
    private List<String> features;
}