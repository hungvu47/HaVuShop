package com.example.havushop.controller;

import com.example.havushop.model.Address;
import com.example.havushop.model.Order;
import com.example.havushop.model.User;
import com.example.havushop.service.AddressService;
import com.example.havushop.service.CartService;
import com.example.havushop.service.OrderService;
import com.example.havushop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/account")
public class CustomerAccount {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @GetMapping
    public String showInfoAccount(Model model, Authentication authentication) {
        User loggedInUser = userService.getLoggedInUser();

        model.addAttribute("fullName", loggedInUser.getFullName());
        model.addAttribute("username", loggedInUser.getUsername());
        model.addAttribute("email", loggedInUser.getEmail());
        model.addAttribute("phoneNumber", loggedInUser.getPhoneNumber());

        cartService.getCart(model, authentication);
        return "account/accountInfo";
    }

    @GetMapping("/orders")
    public String showOrderAccount(Model model, Authentication authentication, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        List<Order> orders = orderService.getAllOrderOfUser(user);
        model.addAttribute("orders", orders);
        cartService.getCart(model, authentication);

        return "account/accountOrder";
    }

    @GetMapping("/changepassword")
    public String changePassword(Model model, Authentication authentication) {

        cartService.getCart(model, authentication);
        return "account/changePassword";
    }

    @GetMapping("/addresses")
    public String showAddressAccount(Model model, Authentication authentication, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        List<Address> addresses = addressService.getAddressOfUser(user);
        model.addAttribute("addresses", addresses);

        cartService.getCart(model, authentication);
        return "account/address";
    }
}
