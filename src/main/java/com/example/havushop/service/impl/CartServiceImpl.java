package com.example.havushop.service.impl;

import com.example.havushop.model.Cart;
import com.example.havushop.model.CartItem;
import com.example.havushop.model.Product;
import com.example.havushop.model.User;
import com.example.havushop.repository.CartItemRepository;
import com.example.havushop.repository.CartRepository;
import com.example.havushop.repository.ProductRepository;
import com.example.havushop.repository.UserRepository;
import com.example.havushop.service.CartService;
import com.example.havushop.service.ImageService;
import com.example.havushop.service.ProductService;
import com.example.havushop.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public void addItemToCart(Long userId, Long productId, int quantity) {
        // Kiểm tra xem user có giỏ hàng chưa
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Xử lý khi user không tồn tại
            return;
        }

        Cart cart = user.getCart();
        if (cart == null) {
            // Nếu user chưa có giỏ hàng, tạo mới
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);

            userRepository.save(user);
        }

        // Kiểm tra xem sản phẩm có tồn tại không
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            // Xử lý khi sản phẩm không tồn tại
            return;
        }

        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        CartItem existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);
        if (existingCartItem != null) {
            // Nếu sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
        } else {
            // Nếu sản phẩm chưa tồn tại trong giỏ hàng, tạo mới CartItem
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cart.getCartItems().add(cartItem);

        }

        // Lưu lại thay đổi
        productRepository.save(product);
    }

    @Override
    public void updateCartItemQuantity(Long cartItemId, int quantityChange) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);
        if (cartItem != null) {
            int newQuantity = cartItem.getQuantity() + quantityChange;

            newQuantity = Math.max(newQuantity, 0);

            cartItem.setQuantity(newQuantity);

            cartItemRepository.save(cartItem);
        } else {

        }
    }

    @Override
    public double caulculateTotal(Cart cart) {
        double total = 0;
        for (CartItem cartItem : cart.getCartItems()) {
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public void getCart(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // Người dùng đã đăng nhập
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.getUserByUsername(userDetails.getUsername());

            Cart cart = user.getCart();
            if (cart == null) {
                // Nếu user chưa có giỏ hàng, tạo mới
                cart = new Cart();
                cart.setUser(user);
                user.setCart(cart);

                userRepository.save(user);
            }

            double total = caulculateTotal(cart);

            if (cart != null) {
                model.addAttribute("cart", cart);
                model.addAttribute("total", total);

                if (cart.getCartItems().isEmpty()) {
                    model.addAttribute("message", "Giỏ hàng trống.");
                }
            } else {
                model.addAttribute("message", "Giỏ hàng của bạn không tồn tại.");
            }
        } else {
            // Người dùng chưa đăng nhập
            model.addAttribute("message", "Chưa đăng nhập");
        }
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }


    @Override
    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }
}
