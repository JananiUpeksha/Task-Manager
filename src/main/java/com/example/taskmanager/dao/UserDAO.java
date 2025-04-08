package com.example.taskmanager.dao;

import com.example.taskmanager.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<UserEntity, Long> {
}
