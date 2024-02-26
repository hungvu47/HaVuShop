package com.example.havushop.utility;

import com.example.havushop.dto.CategoryDTO;
import com.example.havushop.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public CategoryDTO convertEntityToDTO(Category category){
        CategoryDTO cateDTO = new CategoryDTO();
        cateDTO.setCategoryId(category.getCategoryId());
        cateDTO.setCategoryName(category.getCategoryName());
        return cateDTO;
    }

    public Category convertDTOToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setCategoryName(categoryDTO.getCategoryName());
        return category;
    }
}
