package org.example.todolist.dto;

import lombok.Data;

@Data
public class TaskOutDto {
    private Long id;
    private String title;
    private String description;
    private String priority;
    private String status;
    private Long userId;
}
