package com.Imart.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Imart.Entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}