package com.example.taskmanager.service;

import com.example.taskmanager.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);

    Optional<UserDTO> findByUsername(String username);

    UserDetailsService userDetailsService();

    List<UserDTO> findAllUsers();
}
