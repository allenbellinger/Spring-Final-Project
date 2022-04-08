package com.promineotech.movie.dao.deleteorder;

import com.promineotech.movie.dao.OrderDaoImplementation;
import com.promineotech.movie.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeleteOrderDaoImplementation extends OrderDaoImplementation implements DeleteOrderDao {
    @Override
    public Order deleteOrder(Order order, NamedParameterJdbcTemplate jdbcTemplate) {
        log.info("DAO: The deleteOrder method was called.");
        SqlParams params = generateDeleteSql(order);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(params.sql, params.source, keyHolder);

        return Order.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .movieId(order.getMovieId())
                .price(order.getPrice())
                .build();
    }

    private SqlParams generateDeleteSql(Order order) {
        String sql = "DELETE FROM orders WHERE order_id = :order_id";
        SqlParams params = new SqlParams();

        params.sql = sql;
        params.source.addValue("order_id", order.getOrderId());

        return params;
    }
}
