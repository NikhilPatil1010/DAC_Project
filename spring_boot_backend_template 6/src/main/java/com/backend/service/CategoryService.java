package com.backend.service;



import com.backend.dto.CategoryResponse;
import com.backend.entity.Category;
import com.backend.exception.ResourceNotFoundException;
import com.backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return convertToResponse(category);
    }
    
    public CategoryResponse getCategoryBySlug(String slug) {
        Category category = categoryRepository.findBySlug(slug)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return convertToResponse(category);
    }
    
    public CategoryResponse createCategory(String name, String description, String slug, String imageUrl) {
        if (categoryRepository.existsBySlug(slug)) {
            throw new RuntimeException("Category with this slug already exists");
        }
        
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        category.setSlug(slug);
        category.setImageUrl(imageUrl);
        
        Category savedCategory = categoryRepository.save(category);
        return convertToResponse(savedCategory);
    }
    
    public CategoryResponse updateCategory(Long id, String name, String description, String imageUrl) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        
        category.setName(name);
        category.setDescription(description);
        category.setImageUrl(imageUrl);
        
        Category updatedCategory = categoryRepository.save(category);
        return convertToResponse(updatedCategory);
    }
    
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
    }
    
    private CategoryResponse convertToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setCategoryId(category.getCategoryId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        response.setImageUrl(category.getImageUrl());
        response.setSlug(category.getSlug());
        return response;
    }
}