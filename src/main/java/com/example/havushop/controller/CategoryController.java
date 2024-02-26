package com.example.havushop.controller;

import com.example.havushop.dto.CategoryDTO;
import com.example.havushop.model.Category;
import com.example.havushop.service.CategoryService;
import com.example.havushop.utility.CategoryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryConverter categoryConverter;

    //Category
    @GetMapping("/admin/categories")
    public String getCategory(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/category/listCategory";
    }

    @GetMapping("/admin/categories/add")
    public String getCategoryAdd(Model model) {
        model.addAttribute("categoryDTO", new CategoryConverter());
        return "admin/category/addCategory";
    }
    @PostMapping("/admin/categories/add")
    public String postCategoryAdd(@ModelAttribute("categoryDTO") CategoryDTO categoryDTO, RedirectAttributes redirectAttributes) {
        if (categoryService.existsByCategoryName(categoryDTO.getCategoryName())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Tên danh mục đã tồn tại. Vui lòng chọn một tên khác.");
            return "redirect:/admin/categories/add";
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Thêm danh mục thành công");
            categoryService.addCategory(categoryDTO);
            return "redirect:/admin/categories";
        }
    }

    @GetMapping("/admin/categories/delete/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) {
        categoryService.removeCategoryById(categoryId);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{categoryId}")
    public String getCategory(@PathVariable Long categoryId, Model model) {
        Optional<Category> opCategory = categoryService.getCategoryById(categoryId);
        Category category = opCategory.orElseThrow(() -> new RuntimeException("Category not found"));
        CategoryDTO categoryDTO = categoryConverter.convertEntityToDTO(category);
        model.addAttribute("categoryDTO", categoryDTO);
        return "admin/category/editCategory";
    }

    @PostMapping("/admin/categories/update/{categoryId}")
    public String postUpdateCate(@PathVariable Long categoryId,
                                @ModelAttribute("categoryDTO") CategoryDTO categoryDTO,
                                RedirectAttributes redirectAttributes){
        categoryService.updateCategory(categoryId, categoryDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thông tin danh mục thành công");
        return "redirect:/admin/categories";
    }
}
