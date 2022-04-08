package com.promineotech.movie.dao.readorder;

import com.promineotech.movie.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ReadOrderDaoImplementation implements ReadOrderDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Order> fetchOrders() {
        log.info("DAO: The fetchOrders method was called.");

        String sql = "SELECT * FROM orders";

        return jdbcTemplate.query(sql, (rs, rowNum) -> Order.builder()
                .orderId(rs.getLong("order_id"))
                .movieId(rs.getLong("movie_id"))
                .userId(rs.getLong("user_id"))
                .price(rs.getBigDecimal("price"))
                .build()
        );
    }
}
