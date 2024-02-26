package com.example.havushop.service.impl;

import com.example.havushop.model.Role;
import com.example.havushop.repository.RoleRepository;
import com.example.havushop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }
}
