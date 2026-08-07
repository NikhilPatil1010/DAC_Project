package com.backend.repository;



import com.backend.entity.Order;
import com.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserUserIdOrderByOrderDateDesc(Long userId);
    List<Order> findByUserOrderByOrderDateDesc(User user);
    
    @org.springframework.data.jpa.repository.Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM com.backend.entity.Order o")
    java.math.BigDecimal calculateTotalRevenue();
    
    long countByStatus(Order.OrderStatus status);
}