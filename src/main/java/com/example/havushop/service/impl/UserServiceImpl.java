package com.example.havushop.service.impl;

import com.example.havushop.dto.UserDTO;
import com.example.havushop.model.User;
import com.example.havushop.repository.UserRepository;
import com.example.havushop.service.UserService;
import com.example.havushop.utility.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserConverter userConverter;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user = userConverter.convertDTOToEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long userId, UserDTO userDTO) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            User updatedUser = userConverter.convertDTOToEntity(userDTO);
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {

                existingUser.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            } else {

                existingUser.setPassword(updatedUser.getPassword());
            }

            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());

            userRepository.save(existingUser);
        }
    }

    @Override
    public void removeUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User getUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Long getUserIdByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return (user != null) ? user.getUserId() : null;
    }


    @Override
    public boolean isUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
