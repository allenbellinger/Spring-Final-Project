package com.promineotech.movie.dao.deleteorder;

import com.promineotech.movie.entity.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public interface DeleteOrderDao {
    Order deleteOrder(Order order, NamedParameterJdbcTemplate jdbcTemplate);
}