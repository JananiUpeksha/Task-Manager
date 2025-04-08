package com.example.taskmanager.service.impl;

import com.example.taskmanager.dao.UserDAO;
import com.example.taskmanager.dto.UserDTO;
import com.example.taskmanager.entity.impl.UserEntity;
import com.example.taskmanager.service.UserService;
import com.example.taskmanager.utill.AppUtill;
import com.example.taskmanager.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
