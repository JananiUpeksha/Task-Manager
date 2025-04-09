package com.example.taskmanager.dao;

import com.example.taskmanager.entity.impl.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDAO extends JpaRepository<TaskEntity, Long> {
}
