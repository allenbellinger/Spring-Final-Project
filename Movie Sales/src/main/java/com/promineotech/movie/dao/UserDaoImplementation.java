package com.promineotech.movie.dao;

import com.promineotech.movie.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserDaoImplementation implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public User saveUser(String firstName, String lastName, String username, String password) {
        log.info("DAO: The saveUser method was called.");
        SqlParams params = generateInsertSql(firstName, lastName, username, password);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(params.sql, params.source, keyHolder);

        Long userId = keyHolder.getKey().longValue();

        return User.builder()
                .userId(userId)
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .password(password)
                .build();
    }

    private SqlParams generateInsertSql(String firstName, String lastName, String username, String password) {
        String sql = "INSERT INTO users (first_name, last_name, username, password) " +
                "VALUES (:first_name, :last_name, :username, :password)";
        SqlParams params = new UserDaoImplementation.SqlParams();

        params.sql = sql;
        params.source.addValue("first_name", firstName);
        params.source.addValue("last_name", lastName);
        params.source.addValue("username", username);
        params.source.addValue("password", password);

        return params;
    }

    class SqlParams {
        String sql;
        MapSqlParameterSource source = new MapSqlParameterSource();
    }
}