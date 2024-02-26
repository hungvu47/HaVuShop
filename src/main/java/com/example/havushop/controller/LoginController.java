package com.example.havushop.controller;

import com.example.havushop.model.User;
import com.example.havushop.repository.RoleRepository;
import com.example.havushop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/register")
    public String registerGet(Model model) {
        model.addAttribute("user", new User());
        return "login/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        // Kiểm tra xem tên người dùng đã tồn tại chưa
        if (userRepository.findByUsername(user.getUsername()) != null) {
            redirectAttributes.addFlashAttribute("error", "Tên người dùng đã tồn tại");
            return "redirect:/register";
        }

        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // Lưu người dùng vào cơ sở dữ liệu
        userRepository.save(user);

        // Chuyển hướng sau khi đăng ký
        return "redirect:/login?registered";
    }
}
