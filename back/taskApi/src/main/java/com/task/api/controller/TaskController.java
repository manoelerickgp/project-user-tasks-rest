package com.task.api.controller;


import com.task.api.dto.TaskDto;
import com.task.api.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDto task) {
        return service.createTask(task);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskByUserId(@PathVariable(value = "id") UUID userId) {
        return this.service.getTaskByUserId(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(value = "id") UUID taskId,
                                           @RequestBody TaskDto taskDetails) {
        return this.service.updateTask(taskId, taskDetails);
    }


}
