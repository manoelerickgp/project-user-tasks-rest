package com.task.api.controller;

import com.task.api.dto.UserCreate;
import com.task.api.service.UserServive;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@Log4j2
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServive service;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreate userCreate){
       return new ResponseEntity<>(this.service.createUser(userCreate), HttpStatus.CREATED);
    }

}



