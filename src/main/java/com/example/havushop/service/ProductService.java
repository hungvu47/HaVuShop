package com.example.havushop.service;

import com.example.havushop.dto.ProductDTO;
import com.example.havushop.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    public List<Product> getAllProducts();

    public void addProduct(ProductDTO productDTO);

    public void updateProduct(Long productId, ProductDTO productDTO);

    public void removeProductById(Long productId);

    Product getProductById(Long productId);

//    public List<Product> getAllProductByCategoryId(Long categoryId);
}
