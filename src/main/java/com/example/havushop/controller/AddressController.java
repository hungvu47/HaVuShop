package com.example.havushop.controller;

import com.example.havushop.dto.AddressDTO;
import com.example.havushop.model.Address;
import com.example.havushop.model.User;
import com.example.havushop.service.AddressService;
import com.example.havushop.service.UserService;
import com.example.havushop.utility.AddressConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private UserService userService;

    @GetMapping("/account/address")
    public String getAddress(Model model) {

        model.addAttribute("addressDTO", new AddressDTO());
        return "account/addAddress";
    }

    @PostMapping("/account/saveAddress")
    public String saveAddress(@ModelAttribute("addressDTO") AddressDTO addressDTO, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        // Chuyển đổi DTO thành entity và lưu vào cơ sở dữ liệu
        Address address = addressConverter.convertDtoToEntity(addressDTO);
        address.setUser(user);
        addressService.saveAddress(address);
        return "redirect:/account/addresses";
    }

}
