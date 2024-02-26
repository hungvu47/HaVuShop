package com.example.havushop.controller;

import com.example.havushop.dto.CategoryDTO;
import com.example.havushop.dto.ProductDTO;
import com.example.havushop.dto.UserDTO;
import com.example.havushop.model.Category;
import com.example.havushop.model.Product;
import com.example.havushop.model.User;
import com.example.havushop.service.CartService;
import com.example.havushop.service.CategoryService;
import com.example.havushop.service.ImageService;
import com.example.havushop.service.ProductService;
import com.example.havushop.utility.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CartService cartService;

    //Product
    @GetMapping("/admin/products")
    public String getProduct(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "admin/product/listProduct";
    }

    @GetMapping("/admin/products/add")
    public String getProductAdd(Model model) {
        model.addAttribute("products", new  ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/product/productAdd";
    }

    @PostMapping("/admin/products/add")
    public String postProductAdd(@ModelAttribute("productDTO") ProductDTO productDTO,
                                 RedirectAttributes redirectAttributes){
        productService.addProduct(productDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm sản phẩm thành công");
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId){
        productService.removeProductById(productId);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/update/{productId}")
    public String getEditProduct(@PathVariable Long productId, Model model) {
        Product product = productService.getProductById(productId);
//        Product product = opProduct.orElseThrow(() -> new RuntimeException("User not found"));
        ProductDTO productDTO = productConverter.convertEntityToDTO(product);
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/product/editProduct";
    }


    @PostMapping("/admin/products/update/{productId}")
    public String updateProduct(@PathVariable Long productId,
                                @ModelAttribute("productDTO") ProductDTO productDTO,
                                RedirectAttributes redirectAttributes) {
        productService.updateProduct(productId, productDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật sản phẩm thành công");
        return "redirect:/admin/products";
    }


    //product details
    @GetMapping("/product/{productId}")
    public String getProductDetails(@PathVariable Long productId, Model model, Authentication authentication) {
        Product product = productService.getProductById(productId);

        model.addAttribute("product", product);

        cartService.getCart(model, authentication);
        return "product/productDetails";
    }

}
