package com.task.api.service;


import com.task.api.dto.UserCreate;
import com.task.api.entity.User;
import com.task.api.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@AllArgsConstructor
@Log4j2
@Service
@CrossOrigin()
public class UserServive {

    private final UserRepo repository;
    private final ModelMapper mapper;

    @Transactional
    public User createUser(final UserCreate userCreate) {
            log.info("Create user ");
            if (repository.findByUsername(userCreate.getUsername()).isPresent()) {
                log.error("User is present");
                throw new NoSuchElementException( "Usuario ja esta presente!");
            }
            return repository.save(mapper.map(userCreate, User.class));
    }

}
