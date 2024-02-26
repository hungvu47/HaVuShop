package com.example.havushop.utility;

import com.example.havushop.dto.ProductDTO;
import com.example.havushop.model.Product;
import com.example.havushop.repository.ProductRepository;
import com.example.havushop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

@Component
public class ProductConverter {

    @Autowired
    CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    public ProductDTO convertEntityToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setStatus(product.getStatus());

        productDTO.setCategoryId(product.getCategory().getCategoryId());
        productDTO.setCategoryName(product.getCategory().getCategoryName());

        productDTO.setColors(product.getColors());
        productDTO.setSizes(product.getSizes());
        productDTO.setImages(product.getImages());

        return productDTO;
    }

    public Product convertDTOToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setDiscount(productDTO.getDiscount());
        product.setStatus(productDTO.getStatus());

        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());

        product.setColors(new HashSet<>(productDTO.getColors()));
        product.setSizes(new HashSet<>(productDTO.getSizes()));
        product.setImages(new ArrayList<>(productDTO.getImages()));

        return product;
    }

    public ProductDTO getById(Long productId) {
        ProductDTO productDTO = new ProductDTO();
        Product product = productRepository.getById(productId);
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setStatus(product.getStatus());

        productDTO.setCategoryId(product.getCategory().getCategoryId());
        productDTO.setCategoryName(product.getCategory().getCategoryName());

        productDTO.setColors(product.getColors());
        productDTO.setSizes(product.getSizes());
        productDTO.setImages(product.getImages());

        return productDTO;
    }
}
