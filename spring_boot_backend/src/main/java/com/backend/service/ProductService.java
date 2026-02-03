package com.backend.service;



import com.backend.dto.*;
import com.backend.entity.Category;
import com.backend.entity.Product;
import com.backend.exception.ResourceNotFoundException;
import com.backend.repository.CategoryRepository;
import com.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return convertToResponse(product);
    }
    
    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryCategoryId(categoryId).stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public List<ProductResponse> getDealOfDayProducts() {
        return productRepository.findByDealOfDayTrue().stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public List<ProductResponse> searchProducts(String query) {
        return productRepository.searchProducts(query).stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public List<ProductResponse> filterProducts(Long categoryId, BigDecimal minPrice, 
                                               BigDecimal maxPrice, BigDecimal minRating) {
        return productRepository.filterProducts(categoryId, minPrice, maxPrice, minRating).stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();
        updateProductFromRequest(product, request);
        
        Product savedProduct = productRepository.save(product);
        return convertToResponse(savedProduct);
    }
    
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        
        updateProductFromRequest(product, request);
        
        Product updatedProduct = productRepository.save(product);
        return convertToResponse(updatedProduct);
    }
    
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
    
    private void updateProductFromRequest(Product product, ProductRequest request) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setOriginalPrice(request.getOriginalPrice());
        product.setDiscount(request.getDiscount());
        product.setStock(request.getStock());
        product.setImageUrl(request.getImageUrl());
        product.setBrand(request.getBrand());
        product.setDealOfDay(request.getDealOfDay());
        product.setFeaturesArray(request.getFeatures().toArray(new String[0]));
        
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            product.setCategory(category);
        }
    }
    
    private ProductResponse convertToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setProductId(product.getProductId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setOriginalPrice(product.getOriginalPrice());
        response.setDiscount(product.getDiscount());
        response.setStock(product.getStock());
        response.setImageUrl(product.getImageUrl());
        response.setRating(product.getRating());
        response.setReviewCount(product.getReviewCount());
        response.setBrand(product.getBrand());
        response.setDealOfDay(product.getDealOfDay());
        response.setFeatures(List.of(product.getFeaturesArray()));
        
        if (product.getCategory() != null) {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setCategoryId(product.getCategory().getCategoryId());
            categoryResponse.setName(product.getCategory().getName());
            categoryResponse.setDescription(product.getCategory().getDescription());
            categoryResponse.setImageUrl(product.getCategory().getImageUrl());
            categoryResponse.setSlug(product.getCategory().getSlug());
            response.setCategory(categoryResponse);
        }
        
        return response;
    }
}