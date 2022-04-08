package com.promineotech.movie.dao;

import com.promineotech.movie.entity.Movie;
import com.promineotech.movie.entity.Order;
import com.promineotech.movie.entity.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Optional;

public interface OrderDao {
    Optional<User> fetchUser(Long userId, NamedParameterJdbcTemplate jdbcTemplate);

    Optional<Movie> fetchMovie(Long movieId, NamedParameterJdbcTemplate jdbcTemplate);

    Optional<Order> fetchOrder(Long orderId, NamedParameterJdbcTemplate jdbcTemplate);
}
