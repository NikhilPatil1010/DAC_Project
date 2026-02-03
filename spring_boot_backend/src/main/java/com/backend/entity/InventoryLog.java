package com.backend.entity;



import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_log")
@Data
public class InventoryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @Column(name = "quantity_changed", nullable = false)
    private Integer quantityChanged;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "change_type", nullable = false)
    private ChangeType changeType;
    
    @Column(name = "change_date")
    private LocalDateTime changeDate = LocalDateTime.now();
    
    private String notes;
    
    public enum ChangeType {
        RESTOCK, SALE, RETURN, DAMAGE
    }
}