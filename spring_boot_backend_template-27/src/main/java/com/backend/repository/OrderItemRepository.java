package com.backend.repository;


import com.backend.entity.Order;
import com.backend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderOrderId(Long orderId);
    List<OrderItem> findByOrder(Order order);
    void deleteByOrderOrderId(Long orderId);
}