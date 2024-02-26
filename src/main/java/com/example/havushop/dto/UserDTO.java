package com.example.havushop.dto;

import com.example.havushop.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long userId;

    private String username;

    private String password;

    private String fullName;

    private String email;

    private String phoneNumber;

    private List<Long> roleIds;
}
