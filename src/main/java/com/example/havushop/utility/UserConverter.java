package com.example.havushop.utility;

import com.example.havushop.dto.UserDTO;
import com.example.havushop.model.Role;
import com.example.havushop.model.User;
import com.example.havushop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleService roleService;
    public UserDTO convertEntityToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }

    public User convertDTOToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        List<Long> roleIds = userDTO.getRoleIds();
        if (roleIds != null) {
            // Chuyển đổi roleIds thành danh sách các đối tượng Role
            List<Role> roles = roleIds.stream()
                    .map(roleService::findRoleById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            user.setRoles(roles);
        } else {
            // Nếu roleIds là null, gán danh sách roles là một danh sách rỗng
            user.setRoles(Collections.emptyList());
        }
        return user;
    }
}
