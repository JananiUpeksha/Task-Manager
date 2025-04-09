package com.example.taskmanager.service.impl;

import com.example.taskmanager.dao.TaskDAO;
import com.example.taskmanager.dao.UserDAO;
import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.entity.impl.TaskEntity;
import com.example.taskmanager.entity.impl.UserEntity;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.utill.AppUtill;
import com.example.taskmanager.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AppUtill appUtill;

    @Autowired
    private Mapping mapping;

    @Override
    public TaskDTO saveTask(TaskDTO taskDTO) {
        Long generatedId = Long.valueOf(appUtill.generateId("task"));
        taskDTO.setId(generatedId);
        taskDTO.setCreatedAt(LocalDateTime.now());

        UserEntity user = userDAO.findById(taskDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + taskDTO.getUserId()));

        TaskEntity taskEntity = mapping.toTaskEntity(taskDTO);
        taskEntity.setUser(user);

        TaskEntity saved = taskDAO.save(taskEntity);
        return mapping.toTaskDTO(saved);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskDAO.findAll().stream()
                .map(mapping::toTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        return taskDAO.findById(id)
                .map(mapping::toTaskDTO)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    /*@Override
    public TaskDTO updateTask(TaskDTO taskDTO) {
        TaskEntity existing = taskDAO.findById(taskDTO.getId())
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskDTO.getId()));

        existing.setTitle(taskDTO.getTitle());
        existing.setDescription(taskDTO.getDescription());
        existing.setStatus(taskDTO.getStatus());

        if (!existing.getUser().getId().equals(taskDTO.getUserId())) {
            UserEntity newUser = userDAO.findById(taskDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + taskDTO.getUserId()));
            existing.setUser(newUser);
        }

        TaskEntity updated = taskDAO.save(existing);
        return mapping.toTaskDTO(updated);
    }*/
    @Override
    public TaskDTO updateTask(TaskDTO taskDTO) {
        TaskEntity existing = taskDAO.findById(taskDTO.getId())
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskDTO.getId()));

        existing.setTitle(taskDTO.getTitle());
        existing.setDescription(taskDTO.getDescription());
        existing.setStatus(taskDTO.getStatus());

        if (!existing.getUser().getId().equals(taskDTO.getUserId())) {
            UserEntity newUser = userDAO.findById(taskDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + taskDTO.getUserId()));
            existing.setUser(newUser);
        }

        TaskEntity updated = taskDAO.save(existing);
        return mapping.toTaskDTO(updated);
    }

    @Override
    public void deleteTask(Long id) {
        taskDAO.deleteById(id);
    }
}
