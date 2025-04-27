package org.example.todolist.service;

import lombok.RequiredArgsConstructor;
import org.example.todolist.dto.TaskDto;
import org.example.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    /*public TaskDto createTask(TaskDto taskDto) {
        return taskRepository.save();
    }*/

}
