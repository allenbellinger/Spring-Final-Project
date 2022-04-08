package com.promineotech.movie.dao.updateorder;

import com.promineotech.movie.dao.OrderDaoImplementation;
import com.promineotech.movie.entity.Movie;
import com.promineotech.movie.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdateOrderDaoImplementation extends OrderDaoImplementation implements UpdateOrderDao {
    @Override
    public Order updateOrder(Order order, Movie movie, NamedParameterJdbcTemplate jdbcTemplate) {
        log.info("DAO: The updateOrder method was called.");
        SqlParams params = generateUpdateSql(order, movie);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(params.sql, params.source, keyHolder);

        return Order.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .movieId(movie.getMovieId())
                .price(movie.getPrice())
                .build();
    }

    private SqlParams generateUpdateSql(Order order, Movie movie) {
        String sql = "UPDATE orders SET movie_id = :movie_id, price = :price " +
                "WHERE order_id = :order_id";
        SqlParams params = new SqlParams();

        params.sql = sql;
        params.source.addValue("movie_id", movie.getMovieId());
        params.source.addValue("price", movie.getPrice());
        params.source.addValue("order_id", order.getOrderId());

        return params;
    }
}
