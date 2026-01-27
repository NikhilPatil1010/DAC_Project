package com.backend.entity;



import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 1000)
    private String description;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column(name = "original_price", precision = 10, scale = 2)
    private BigDecimal originalPrice;
    
    @Column(precision = 5, scale = 2)
    private BigDecimal discount = BigDecimal.ZERO;
    
    private Integer stock = 0;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    
    @Column(precision = 3, scale = 2)
    private BigDecimal rating = BigDecimal.ZERO;
    
    @Column(name = "review_count")
    private Integer reviewCount = 0;
    
    private String brand;
    
    @Column(name = "deal_of_day")
    private Boolean dealOfDay = false;
    
    @Column(columnDefinition = "JSON")
    private String features;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Feedback> feedbacks;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<InventoryLog> inventoryLogs;
    
    public String[] getFeaturesArray() {
        if (features == null || features.isEmpty()) {
            return new String[0];
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(features, String[].class);
        } catch (Exception e) {
            return new String[0];
        }
    }
    
    public void setFeaturesArray(String[] featuresArray) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.features = mapper.writeValueAsString(featuresArray);
        } catch (Exception e) {
            this.features = "[]";
        }
    }
}