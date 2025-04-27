package org.example.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.example.todolist.dto.TaskDto;
import org.example.todolist.dto.TaskOutDto;
import org.example.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskOutDto createTask(@RequestBody TaskDto taskDto) {
        return taskService.createTask(taskDto);
    }

    @DeleteMapping("/delete/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }
}
