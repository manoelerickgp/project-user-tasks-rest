package com.task.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;


@Getter
@Entity(name = "tb_task")
@Setter
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;

    private String startAt;
    private String endAt;
    private String priority;

    private UUID idUser;

    @CreationTimestamp
    private Instant createAt;

}
