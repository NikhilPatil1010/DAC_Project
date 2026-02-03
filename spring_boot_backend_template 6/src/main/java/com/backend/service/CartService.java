
package com.backend.service;

import com.backend.dto.*;
import com.backend.entity.*;
import com.backend.exception.ResourceNotFoundException;
import com.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    
    public CartResponse getCart(Long userId) {
        Cart cart = getOrCreateCart(userId);
        return convertToResponse(cart);
    }
    
    public CartResponse addItem(Long userId, CartItemRequest request) {
        Cart cart = getOrCreateCart(userId);
        Product product = productRepository.findById(request.getProductId())
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        
        CartItem existingItem = cartItemRepository.findByCartAndProduct(cart, product)
            .orElse(null);
        
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(request.getQuantity());
            cartItemRepository.save(newItem);
        }
       

        cartRepository.save(cart);
        return convertToResponse(cart);
    }
    
    public CartResponse updateQuantity(Long userId, Long productId, Integer quantity) {
        Cart cart = getCartByUserId(userId);
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
            .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart"));
        
        if (quantity <= 0) {
            cartItemRepository.delete(cartItem);
        } else {
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
        
        return convertToResponse(cart);
    }
    
    public CartResponse removeItem(Long userId, Long productId) {
        Cart cart = getCartByUserId(userId);
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        
        cartItemRepository.findByCartAndProduct(cart, product)
            .ifPresent(cartItemRepository::delete);
        
        return convertToResponse(cart);
    }
    
    public void clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cartItemRepository.deleteByCart(cart);
    }
    
    private Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserUserId(userId)
            .orElseGet(() -> {
                User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
                
                Cart newCart = new Cart();
                newCart.setUser(user);
                return cartRepository.save(newCart);
            });
    }
    
    private Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }
    
    private CartResponse convertToResponse(Cart cart) {
        CartResponse response = new CartResponse();
        response.setCartId(cart.getCartId());
        response.setTotal(cart.getTotal());
        response.setItemCount(cart.getItemCount());
        response.setCreatedAt(cart.getCreatedAt());
        
        List<CartItemResponse> items = cart.getCartItems().stream()
            .map(this::convertItemToResponse)
            .collect(Collectors.toList());
        response.setItems(items);
        
        return response;
    }
    
    private CartItemResponse convertItemToResponse(CartItem item) {
        CartItemResponse response = new CartItemResponse();
        response.setCartItemId(item.getCartItemId());
        response.setQuantity(item.getQuantity());
        response.setSubtotal(item.getSubtotal());
        response.setAddedAt(item.getAddedAt());
        
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductId(item.getProduct().getProductId());
        productResponse.setName(item.getProduct().getName());
        productResponse.setPrice(item.getProduct().getPrice());
        productResponse.setImageUrl(item.getProduct().getImageUrl());
        productResponse.setBrand(item.getProduct().getBrand());
        response.setProduct(productResponse);
        
        return response;
    }
}