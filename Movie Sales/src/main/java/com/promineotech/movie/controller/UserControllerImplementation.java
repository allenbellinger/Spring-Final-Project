package com.promineotech.movie.controller;

import com.promineotech.movie.entity.User;
import com.promineotech.movie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserControllerImplementation implements UserController{
    @Autowired
    private UserService movieUserService;

    @Override
    public User createUser(String firstName, String lastName, String username, String password) {
        log.info("Controller: User created: first_name={}, last_name={}, username={}, password={}", firstName, lastName, username, password);
        return movieUserService.createUser(firstName, lastName, username, password);
    }

}
