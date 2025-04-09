package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    TaskDTO saveTask(TaskDTO taskDTO);
    List<TaskDTO> getAllTasks();
    TaskDTO getTaskById(Long id);
    TaskDTO updateTask(TaskDTO taskDTO);
    void deleteTask(Long id);
}
