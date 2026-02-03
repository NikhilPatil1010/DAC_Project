package com.backend.repository;



import com.backend.entity.Feedback;
import com.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByProductProductId(Long productId);
    List<Feedback> findByProductOrderByFeedbackDateDesc(Product product);
    boolean existsByUserUserIdAndProductProductId(Long userId, Long productId);
}