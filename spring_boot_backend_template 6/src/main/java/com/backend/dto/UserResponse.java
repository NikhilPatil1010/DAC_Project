package com.backend.dto;

import lombok.Data;

@Data
public
class UserResponse {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String role;
    
}