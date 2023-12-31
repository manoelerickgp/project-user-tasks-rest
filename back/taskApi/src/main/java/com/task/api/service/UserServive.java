package com.task.api.service;


import com.task.api.dto.UserCreate;
import com.task.api.entity.User;
import com.task.api.exceptions.NotFoundException;
import com.task.api.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Log4j2
@Service
public class UserServive {

    private final UserRepo repository;
    private final ModelMapper mapper;

    @Transactional
    public void createUser(UserCreate userCreate) {
            log.info("Create user ");
            if (repository.findByUsername(userCreate.getUsername()).isPresent()) {
                log.error("User is present");
                throw new NotFoundException("Este usuário já existe");
            }
            repository.save(mapper.map(userCreate, User.class));
    }

    @Transactional
    public List<User> findUserById() {
        return repository.findAll();
    }

}
