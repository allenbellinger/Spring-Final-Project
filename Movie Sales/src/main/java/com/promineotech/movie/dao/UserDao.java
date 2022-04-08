package com.promineotech.movie.dao;

import com.promineotech.movie.entity.User;

public interface UserDao {
    User saveUser(String firstName, String lastName, String username, String password);
}
