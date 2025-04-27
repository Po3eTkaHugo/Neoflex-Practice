package org.example.todolist.service;

import lombok.RequiredArgsConstructor;
import org.example.todolist.dto.TaskDto;
import org.example.todolist.dto.TaskOutDto;
import org.example.todolist.entity.Task;
import org.example.todolist.entity.User;
import org.example.todolist.repository.TaskRepository;
import org.example.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

import static org.example.todolist.Constants.TO_DO;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    @Transactional
    public TaskOutDto createTask(TaskDto taskDto) {

        Long userId = taskDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id=" +  userId + " not found!"));

        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setPriority(taskDto.getPriority());
        task.setStatus(TO_DO);
        task.setUser(user);

        return toOutDto(taskRepository.save(task));
    }

    @Transactional(readOnly = true)
    public List<TaskOutDto> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId).stream().sorted(Comparator.comparing(Task::getPriority)).map(this::toOutDto).toList();
    }

    @Transactional
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    private TaskOutDto toOutDto(Task task) {
        TaskOutDto dto = new TaskOutDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setPriority(task.getPriority());
        dto.setUserId(task.getUser().getId());

        return dto;
    }

}
