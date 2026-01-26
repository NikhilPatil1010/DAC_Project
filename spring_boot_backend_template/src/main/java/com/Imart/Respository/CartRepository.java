package com.Imart.Respository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Imart.Entity.Cart;
import com.Imart.Entity.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}