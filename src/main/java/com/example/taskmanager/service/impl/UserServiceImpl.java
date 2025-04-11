package com.example.taskmanager.service.impl;

import com.example.taskmanager.dao.UserDAO;
import com.example.taskmanager.dto.UserDTO;
import com.example.taskmanager.entity.impl.UserEntity;
import com.example.taskmanager.service.UserService;
import com.example.taskmanager.utill.AppUtill;
import com.example.taskmanager.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private AppUtill appUtill;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        Long generatedId = Long.valueOf(appUtill.generateId("user"));
        userDTO.setId(generatedId);
        UserEntity saved = userDAO.save(mapping.toUserEntity(userDTO));
        return mapping.toUserDTO(saved);
    }
    @Override
    public Optional<UserDTO> findByUsername(String username) {
        Optional<UserEntity> userByUsername = userDAO.findByUsername(username);
        return userByUsername.map(mapping::toUserDTO);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                (UserDetails) userDAO.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userDAO.findAll().stream()
                .map(mapping::toUserDTO)
                .collect(Collectors.toList());
    }

}
