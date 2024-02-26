package com.example.havushop.controller;

import com.example.havushop.service.CategoryService;
import com.example.havushop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin/index";
    }
}
