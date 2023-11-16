package com.task.api.service;

import com.task.api.dto.TaskDto;
import com.task.api.entity.Task;
import com.task.api.exceptions.NotFoundException;
import com.task.api.repo.TaskRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepo repository;
    private final ModelMapper mapper;

    @Transactional(readOnly = true)
    public List<?> getTaskByUserId(UUID userId) {
        return this.repository.findByIdUser(userId);
    }

    @Transactional
    public TaskDto createTask(TaskDto taskDto) {
        var task = repository.save(mapper.map(taskDto, Task.class));
        return mapper.map(task, TaskDto.class);

    }

    @Transactional
    public TaskDto updateTask(UUID id, TaskDto taskDetails) {
        Optional<Task> taskOptional = repository.findById(id);
        if (!taskOptional.isPresent()) {
            throw new NotFoundException("Task Not Found");
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
        task = repository.save(task);
        return mapper.map(task, TaskDto.class);
    }

    @Transactional
    public void deleteTask(UUID taskId) {
        var task = this.repository.findById(taskId).orElse(null);
        if (task == null) {
            throw new NotFoundException("Task Not Found");
        }
        repository.deleteById(taskId);
    }
}