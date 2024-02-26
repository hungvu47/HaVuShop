package com.example.havushop.dto;

import com.example.havushop.model.Category;
import com.example.havushop.model.Color;
import com.example.havushop.model.Image;
import com.example.havushop.model.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProductDTO {
    private Long productId;
    private String productName;
    private double price;
    private String description;
    private String discount;
    private String status;

    private Long categoryId;
    private String categoryName;

    private Category category;
    private Set<Color> colors = new HashSet<>();
    private Set<Size> sizes = new HashSet<>();
    private List<Image> images = new ArrayList<>();
}
