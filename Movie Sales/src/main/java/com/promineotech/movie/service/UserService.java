package com.promineotech.movie.service;

import com.promineotech.movie.entity.User;

public interface UserService {
    User createUser(String firstName, String lastName, String username, String password);
}
