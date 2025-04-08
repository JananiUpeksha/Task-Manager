package com.example.taskmanager.utill;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class AppUtill {
    private final AtomicLong userCounter = new AtomicLong(1L);  // Start from 1L
    private final AtomicLong taskCounter = new AtomicLong(1L);  // Start from 1L

    // Method to generate ID based on type (User or Task)
    public synchronized String generateId(String type) {
        Long id;
        switch (type.toLowerCase()) {
            case "user":
                id = userCounter.getAndIncrement();  // Increment user ID
                break;
            case "task":
                id = taskCounter.getAndIncrement();  // Increment task ID
                break;
            default:
                throw new IllegalArgumentException("Unknown type for ID generation: " + type);  // Invalid type
        }
        return String.format("%03d", id);  // Format the ID with leading zeros (e.g., 001, 002, 003)
    }
}
