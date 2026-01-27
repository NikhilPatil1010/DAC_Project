package com.backend.repository;



import com.backend.entity.Cart;
import com.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserUserId(Long userId);
    Optional<Cart> findByUser(User user);
    void deleteByUser(User user);
}