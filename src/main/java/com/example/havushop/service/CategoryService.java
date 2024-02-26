package com.example.havushop.service;

import com.example.havushop.dto.CategoryDTO;
import com.example.havushop.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {

    public List<Category> getAllCategories();

    public void addCategory(CategoryDTO categoryDTO);

    public void updateCategory(Long categoryId, CategoryDTO categoryDTO);

    public void removeCategoryById(Long categoryId);

    Optional<Category> getCategoryById(Long categoryId);

    boolean existsByCategoryName(String categoryName);
}
