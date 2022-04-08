package com.promineotech.movie.dao.createorder;

import com.promineotech.movie.dao.OrderDaoImplementation;
import com.promineotech.movie.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateOrderDaoImplementation extends OrderDaoImplementation implements CreateOrderDao {
    @Override
    public Order saveOrder(User user, Movie movie, NamedParameterJdbcTemplate jdbcTemplate) {
        log.info("DAO: The saveOrder method was called.");
        SqlParams params = generateInsertSql(user, movie);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(params.sql, params.source, keyHolder);

        Long orderId = keyHolder.getKey().longValue();

        return Order.builder()
                .orderId(orderId)
                .userId(user.getUserId())
                .movieId(movie.getMovieId())
                .price(movie.getPrice())
                .build();
    }

    private SqlParams generateInsertSql(User user, Movie movie) {
        String sql = "INSERT INTO orders (user_id, movie_id, price) " +
                "VALUES (:user_id, :movie_id, :price)";
        SqlParams params = new SqlParams();

        params.sql = sql;
        params.source.addValue("user_id", user.getUserId());
        params.source.addValue("movie_id", movie.getMovieId());
        params.source.addValue("price", movie.getPrice());

        return params;
    }
}
