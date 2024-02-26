package com.example.havushop.service;

import com.example.havushop.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    List<Role> getAllRole();

    Optional<Role> findRoleById(Long roleId);
}
