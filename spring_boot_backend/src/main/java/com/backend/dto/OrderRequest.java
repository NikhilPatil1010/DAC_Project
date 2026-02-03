package com.backend.dto;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;

@Data
public class OrderRequest {
    private JsonNode shippingAddress;
    private String paymentMethod;
}
