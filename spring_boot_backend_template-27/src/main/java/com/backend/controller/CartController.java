package com.backend.controller;



import com.backend.dto.*;
import com.backend.security.CurrentUser;
import com.backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    
    private final CartService cartService;
    
    @GetMapping
    public ResponseEntity<CartResponse> getCart(@CurrentUser Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }
    
    @PostMapping("/items")
    public ResponseEntity<CartResponse> addItem(@CurrentUser Long userId, 
                                               @RequestBody CartItemRequest request) {
        return ResponseEntity.ok(cartService.addItem(userId, request));
    }
    
    @PutMapping("/items/{productId}")
    public ResponseEntity<CartResponse> updateQuantity(@CurrentUser Long userId,
                                                      @PathVariable Long productId,
                                                      @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.updateQuantity(userId, productId, quantity));
    }
    
    @DeleteMapping("/items/{productId}")
    public ResponseEntity<CartResponse> removeItem(@CurrentUser Long userId,
                                                  @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeItem(userId, productId));
    }
    
    @DeleteMapping
    public ResponseEntity<Void> clearCart(@CurrentUser Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}