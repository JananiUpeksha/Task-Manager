package com.example.taskmanager.utill;
import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.dto.UserDTO;
import com.example.taskmanager.entity.impl.TaskEntity;
import com.example.taskmanager.entity.impl.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    // ===== User Mapping =====
    public UserEntity toUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public UserDTO toUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public List<UserDTO> toUserDTOList(List<UserEntity> userEntityList) {
        return modelMapper.map(userEntityList, new TypeToken<List<UserDTO>>() {}.getType());
    }

    // ===== Task Mapping =====
    public TaskEntity toTaskEntity(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, TaskEntity.class);
    }

    public TaskDTO toTaskDTO(TaskEntity taskEntity) {
        return modelMapper.map(taskEntity, TaskDTO.class);
    }

    public List<TaskDTO> toTaskDTOList(List<TaskEntity> taskEntityList) {
        return modelMapper.map(taskEntityList, new TypeToken<List<TaskDTO>>() {}.getType());
    }
}
