package com.task.api.service;

import com.task.api.dto.TaskDto;
import com.task.api.entity.Task;
import com.task.api.repo.TaskRepo;
import com.task.api.utils.Utils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepo repository;
    private final ModelMapper mapper;

    @Transactional
    public ResponseEntity<?> createTask(TaskDto task) {
        repository.save(mapper.map(task, Task.class));
        return ResponseEntity.status(201).build();
    }


    @Transactional(readOnly = true)
    public ResponseEntity<?> getTaskByUserId(UUID userId) {
        var list = this.repository.findByIdUser(userId);
        return ResponseEntity.ok(list);
    }


    public ResponseEntity<?> updateTask(UUID id, TaskDto taskDetails) {
        var task = this.repository.findById(id).orElse(null);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tarefa não encontrada");
        }
        if (!task.getIdUser().equals(task.getIdUser())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuário sem permissão para alterar esta tarefa");
        }
        Utils.copyNonNullProperties(taskDetails, task);
        var taskUpdated = this.repository.save(task);
        return ResponseEntity.ok().body(taskUpdated);
    }
}
