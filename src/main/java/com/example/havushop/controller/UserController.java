package com.example.havushop.controller;

import com.example.havushop.dto.UserDTO;
import com.example.havushop.model.Role;
import com.example.havushop.model.User;
import com.example.havushop.service.RoleService;
import com.example.havushop.service.UserService;
import com.example.havushop.utility.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserConverter userConverter;

    //Account
    @GetMapping("/admin/users")
    public String getAcc(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "admin/user/listUser";
    }

    @GetMapping("/admin/users/add")
    public String getUserAdd(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("roles", roleService.getAllRole());
        return "admin/user/usersAdd";
    }

    @PostMapping("/admin/users/add")
    public String postUserAdd(@ModelAttribute("userDTO") UserDTO userDTO, RedirectAttributes redirectAttributes){
        if (userService.isUsernameExists(userDTO.getUsername())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Tên người dùng đã tồn tại. Vui lòng chọn một tên khác.");
            return "redirect:/admin/users/add";
        }
        else {
            if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
                redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu không được để trống");
                return "redirect:/admin/users/add";
            } else {
                userService.addUser(userDTO);
                redirectAttributes.addFlashAttribute("successMessage", "Thêm tài khoản thành công");
                return "redirect:/admin/users";
            }
        }
    }

    @GetMapping("/admin/users/delete/{userId}")
    public String removeUser(@PathVariable Long userId){
        userService.removeUserById(userId);
        return "redirect:/admin/users";
    }//delete 1 user

    @GetMapping("/admin/users/update/{userId}")
    public String getEditUser(@PathVariable Long userId, Model model){
        Optional<User> opUser = userService.getUserById(userId);
        User user = opUser.orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO userDTO = userConverter.convertEntityToDTO(user);
        model.addAttribute("userDTO", userDTO);
        return "admin/user/userEdit";
    }

    @PostMapping("/admin/users/update/{userId}")
    public String postEditUser(@PathVariable Long userId,
                               @ModelAttribute("userDTO") UserDTO userDTO,
                               RedirectAttributes redirectAttributes) {
        userService.updateUser(userId, userDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thông tin người dùng thành công");
        return "redirect:/admin/users";
    }


}
