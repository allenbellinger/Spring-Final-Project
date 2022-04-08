package com.promineotech.movie.service;

import com.promineotech.movie.dao.UserDao;
import com.promineotech.movie.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserDao movieUserDao;

    @Transactional
    @Override
    public User createUser(String firstName, String lastName, String username, String password) {
        log.info("Service: The createUser method was called.");
        return movieUserDao.saveUser(firstName, lastName, username, password);
    }
}
