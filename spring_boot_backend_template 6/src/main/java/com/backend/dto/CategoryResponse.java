package com.backend.dto;

import lombok.Data;

@Data
public
class CategoryResponse {
    private Long categoryId;
    private String name;
    private String description;
    private String imageUrl;
    private String slug;
}