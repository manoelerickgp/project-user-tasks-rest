package com.task.api.service;

import com.task.api.dto.TaskDto;
import com.task.api.entity.Task;
import com.task.api.repo.TaskRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepo repository;
    private final ModelMapper mapper;

    @Transactional
    public Task createTask(TaskDto task) {
        return repository.save(mapper.map(task, Task.class));
    }


    @Transactional(readOnly = true)
    public List<Task> getTaskByUserId(UUID userId) {
        return this.repository.findByIdUser(userId);
    }


    @Transactional
    public Task updateTask(UUID id, TaskDto taskDetails) {
        Optional<Task> taskOptional = repository.findById(id);
        if (!taskOptional.isPresent()) {
            throw new NoSuchElementException("Task Not Found");
        }

        Task task = taskOptional.get();
        if (taskDetails.getTitle() != null && !taskDetails.getTitle().isEmpty()) {
            task.setTitle(taskDetails.getTitle());
        }
        if (taskDetails.getDescription() != null && !taskDetails.getDescription().isEmpty()) {
            task.setDescription(taskDetails.getDescription());
        }
        if (taskDetails.getIdUser() != null) {
            task.setIdUser(taskDetails.getIdUser());
        }
        if (taskDetails.getStartAt() != null && !taskDetails.getStartAt().isEmpty()) {
            task.setStartAt(taskDetails.getStartAt());
        }
        if (taskDetails.getEndAt() != null && !taskDetails.getEndAt().isEmpty()) {
            task.setEndAt(taskDetails.getEndAt());
        }
        if (taskDetails.getPriority() != null && !taskDetails.getPriority().isEmpty()) {
            task.setPriority(taskDetails.getPriority());
        }
        return repository.save(task);
    }

    @Transactional
    public void deleteTask(UUID taskId) {
        var task = this.repository.findById(taskId).orElse(null);
        if (task == null) {
            throw new NoSuchElementException("");
        }
        repository.deleteById(taskId);
    }
}