package com.example.havushop.controller;

import com.example.havushop.enums.OrderStatus;
import com.example.havushop.model.*;
import com.example.havushop.service.CartService;
import com.example.havushop.service.OrderService;
import com.example.havushop.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/view")
    public String viewCart(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Lấy thông tin người dùng đang đăng nhập
        User user = userService.getUserByUsername(userDetails.getUsername());

        // Lấy giỏ hàng của người dùng
        Cart cart = user.getCart();

        double total = cartService.caulculateTotal(cart);

        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "cart/view";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") Long productId,
                            @RequestParam("quantity") int quantity,
                            @AuthenticationPrincipal UserDetails userDetails) {
        // Lấy thông tin người dùng đang đăng nhập
        User user = userService.getUserByUsername(userDetails.getUsername());

        // Thêm sản phẩm vào giỏ hàng
        cartService.addItemToCart(user.getUserId(), productId, quantity);

        return "redirect:/cart/view";
    }

    @GetMapping("/delete/{cartItemId}")
    public String removeCartItemById(@PathVariable Long cartItemId) {
        cartService.removeCartItem(cartItemId);
        return "redirect:/cart/view";
    }

    @PostMapping("/update/{cartItemId}")
    public ResponseEntity<String> updateCartItemQuantity(
            @PathVariable Long cartItemId,
            @RequestBody Map<String, Integer> quantityChangeMap) {
        try {
            int quantityChange = quantityChangeMap.get("quantityChange");

            // Gọi service để cập nhật số lượng
            cartService.updateCartItemQuantity(cartItemId, quantityChange);

            // Trả về thông báo thành công
            return ResponseEntity.ok("Cart item updated successfully");
        } catch (Exception e) {
            // Xử lý nếu có lỗi xảy ra
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating cart item");
        }
    }


}
