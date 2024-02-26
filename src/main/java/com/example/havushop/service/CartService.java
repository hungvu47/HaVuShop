package com.example.havushop.service;

import com.example.havushop.model.Cart;
import com.example.havushop.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public interface CartService {
    public void addItemToCart(Long userId, Long productId, int quantity);

    public void updateCartItemQuantity(Long cartItemId, int quantityChange);

    public double caulculateTotal(Cart cart);

    public void getCart(Model model, Authentication authentication);

    public void removeCartItem(Long cartItemId);

    public Cart getCartByUser(User user);
}
