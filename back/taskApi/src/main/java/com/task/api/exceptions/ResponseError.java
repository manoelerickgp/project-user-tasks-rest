package com.task.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseError {

    private String message;
    private String details;
    private Integer status;
    private Instant timestamp;


}
