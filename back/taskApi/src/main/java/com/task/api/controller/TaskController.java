package com.task.api.controller;


import com.task.api.dto.TaskDto;
import com.task.api.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskByUserId(@PathVariable(value = "id") UUID userId) {
        return new ResponseEntity<>(this.service.getTaskByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDto task) {
        return new ResponseEntity<>(service.createTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(value = "id") UUID taskId, @RequestBody TaskDto taskDetails) {
        return new ResponseEntity<>(this.service.updateTask(taskId, taskDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(value = "id") UUID taskId) {
        this.service.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}