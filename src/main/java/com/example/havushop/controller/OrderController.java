package com.example.havushop.controller;

import com.example.havushop.model.Cart;
import com.example.havushop.model.Order;
import com.example.havushop.model.User;
import com.example.havushop.service.CartService;
import com.example.havushop.service.OrderService;
import com.example.havushop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/checkout")
    public String payment(Model model, Authentication authentication) {

        if (authentication != null && authentication.isAuthenticated()) {
            // Lấy thông tin người dùng từ Authentication
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Lấy thông tin khách hàng từ UserDetails hoặc username
            String username = userDetails.getUsername();
            User customer = userService.getUserByUsername(username);

            // Thêm thông tin khách hàng vào model
            model.addAttribute("customer", customer);
        }

        cartService.getCart(model, authentication);

        return "cart/orderView";
    }

        @PostMapping("/checkout")
    public String processCheckout(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());

        // Lấy giỏ hàng của người dùng
        Cart cart = cartService.getCartByUser(user);

        if (!cart.getCartItems().isEmpty()) {
            orderService.saveOrder(cart);
            return "redirect:/account/orders";
        } else {
            return "redirect:/cart/view";
        }
    }

    @GetMapping("/admin/orders")
    public String getOrderList(Model model) {
        model.addAttribute("orders", orderService.getAllOrder());
        return "admin/order/listOrder";
    }

    @GetMapping("/admin/order-details/{id}")
    public  String getOrderDetails(@PathVariable Long id,Model model) {
        Order order = orderService.getOrderOfUser(id);

        if (order != null) {
            model.addAttribute("order", order);
            return "admin/order/orderDetail";
        } else {
            // Xử lý trường hợp không tìm thấy đơn hàng
            return "redirect:/error";
        }
    }

    @GetMapping("/admin/orders/cancle/{id}")
    public String cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return "redirect:/admin/orders";
    }
}
