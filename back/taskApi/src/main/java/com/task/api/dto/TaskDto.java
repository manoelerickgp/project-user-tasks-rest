package com.task.api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskDto {

    private String title;
    private String description;
    private UUID idUser;
    private String startAt;
    private String endAt;
    private String priority;

}
