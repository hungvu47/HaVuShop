package com.example.havushop.service.impl;

import com.example.havushop.dto.ProductDTO;
import com.example.havushop.model.Product;
import com.example.havushop.repository.ProductRepository;
import com.example.havushop.service.ProductService;
import com.example.havushop.utility.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductConverter productConverter;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = productConverter.convertDTOToEntity(productDTO);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long productId, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(productId).orElse(null);
        if (existingProduct != null){
            Product editProduct = productConverter.convertDTOToEntity(productDTO);

            existingProduct.setProductName(editProduct.getProductName());
            existingProduct.setPrice(editProduct.getPrice());
            existingProduct.setDescription(editProduct.getDescription());
            existingProduct.setDiscount(editProduct.getDiscount());
            existingProduct.setStatus(editProduct.getStatus());

            existingProduct.setCategory(editProduct.getCategory());

            productRepository.save(existingProduct);
        }
    }

    @Override
    public void removeProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

//    @Override
//    public List<Product> getAllProductByCategoryId(Long categoryId) {
//        return productRepository.findAllByCategory_Id(categoryId);
//    }
}
