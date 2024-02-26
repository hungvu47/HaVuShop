package com.example.havushop.service;

import com.example.havushop.dto.UserDTO;
import com.example.havushop.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> getAllUser();

    void addUser(UserDTO userDTO);

    void updateUser(Long userId, UserDTO userDTO);

    void removeUserById(Long userId);

    Optional<User> getUserById(Long userId);

    public User getUserByUsername(String userName);

    public Long getUserIdByUsername(String username);

    boolean isUsernameExists(String username);

    public User getLoggedInUser();
}
