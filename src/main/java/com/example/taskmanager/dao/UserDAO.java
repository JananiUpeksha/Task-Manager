package com.example.taskmanager.dao;

import com.example.taskmanager.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
