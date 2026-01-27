package com.backend.repository;



import com.backend.entity.InventoryLog;
import com.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InventoryLogRepository extends JpaRepository<InventoryLog, Long> {
    List<InventoryLog> findByProductProductId(Long productId);
    List<InventoryLog> findByProductOrderByChangeDateDesc(Product product);
    List<InventoryLog> findByChangeType(InventoryLog.ChangeType changeType);
}