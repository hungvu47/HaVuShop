package com.example.havushop.repository;

import com.example.havushop.model.Cart;
import com.example.havushop.model.CartItem;
import com.example.havushop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProduct(Cart cart, Product product);
}
