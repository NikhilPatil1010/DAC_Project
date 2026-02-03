package com.backend.entity;



import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;
    
    @OneToOne
    @JoinColumn(
        name = "user_id", 
        referencedColumnName = "user_id", 
        unique = true,
        foreignKey = @ForeignKey(name = "FK_cart_user")
    )
    private User user;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();
    
    public BigDecimal getTotal() {
        return cartItems.stream()
            .map(CartItem::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public Integer getItemCount() {
        return cartItems.stream()
            .mapToInt(CartItem::getQuantity)
            .sum();
    }
}