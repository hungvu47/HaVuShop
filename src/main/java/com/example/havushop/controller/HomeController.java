package com.example.havushop.controller;

import com.example.havushop.dto.ProductDTO;
import com.example.havushop.model.Cart;
import com.example.havushop.model.User;
import com.example.havushop.repository.UserRepository;
import com.example.havushop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    ImageService imageService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryService categoryService;

    //Product User
    @GetMapping("/home")
    public String getHome(Model model, Authentication authentication) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("images", imageService.getAllImages());

        cartService.getCart(model, authentication);
        return "index";
    }


    @GetMapping("/gioi-thieu")
    public String introduction(Model model, Authentication authentication) {
        cartService.getCart(model, authentication);
        return "head/introduction";
    }

    @GetMapping("/lien-he")
    public String contact(Model model, Authentication authentication){
        cartService.getCart(model, authentication);
        return "head/contact";
    }

    @GetMapping("/collections")
    public String listproduct(Model model, Authentication authentication){
        model.addAttribute("images", imageService.getAllImages());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategories());

        cartService.getCart(model, authentication);
        return  "head/collection";
    }
}
