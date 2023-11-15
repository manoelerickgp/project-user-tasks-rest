package com.task.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserCreate {

    private String name;
    private String username;
    private String password;

}