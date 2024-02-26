package com.example.havushop.service.impl;

import com.example.havushop.dto.CategoryDTO;
import com.example.havushop.model.Category;
import com.example.havushop.repository.CategoryRepository;
import com.example.havushop.service.CategoryService;
import com.example.havushop.utility.CategoryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryConverter categoryConverter;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        Category category = categoryConverter.convertDTOToEntity(categoryDTO);
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(categoryId).orElse(null);
        if (existingCategory != null) {
            Category updateCategory = categoryConverter.convertDTOToEntity(categoryDTO);
            existingCategory.setCategoryName(updateCategory.getCategoryName());
            categoryRepository.save(existingCategory);
        }
    }

    @Override
    public void removeCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public boolean existsByCategoryName(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName);
    }
}
