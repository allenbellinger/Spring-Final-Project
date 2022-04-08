package com.promineotech.movie.dao.updateorder;

import com.promineotech.movie.entity.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public interface UpdateOrderDao {
    Order updateOrder(Order order, Movie movie, NamedParameterJdbcTemplate jdbcTemplate);
}