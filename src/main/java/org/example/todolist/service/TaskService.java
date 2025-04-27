package org.example.todolist.service;

import lombok.RequiredArgsConstructor;
import org.example.todolist.dto.TaskDto;
import org.example.todolist.entity.Task;
import org.example.todolist.entity.User;
import org.example.todolist.repository.TaskRepository;
import org.example.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import static org.example.todolist.Constants.TO_DO;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public void createTask(TaskDto taskDto) {

        Long userId = taskDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id=" +  userId + " not found!"));

        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setPriority(taskDto.getPriority());
        task.setStatus(TO_DO);
        task.setUser(user);

        taskRepository.save(task);
    }

}
