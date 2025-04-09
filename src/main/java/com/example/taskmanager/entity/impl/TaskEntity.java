package com.example.taskmanager.entity.impl;

import com.example.taskmanager.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "task")
public class TaskEntity implements SuperEntity {
    @Id
    private Long id;
    @Column(length = 50)
    private String title;
    @Column(length = 100)
    private String description;
    private String status;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
