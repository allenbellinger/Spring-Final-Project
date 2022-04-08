package com.promineotech.movie.dao.createorder;

import com.promineotech.movie.entity.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public interface CreateOrderDao {
    Order saveOrder(User user, Movie movie, NamedParameterJdbcTemplate jdbcTemplate);
}