package com.promineotech.movie.dao;

import com.promineotech.movie.dao.createorder.CreateOrderDaoImplementation;
import com.promineotech.movie.dao.deleteorder.DeleteOrderDaoImplementation;
import com.promineotech.movie.dao.updateorder.UpdateOrderDaoImplementation;
import com.promineotech.movie.entity.Movie;
import com.promineotech.movie.entity.Order;
import com.promineotech.movie.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class OrderDaoImplementation implements OrderDao {
    @Override
    public Optional<User> fetchUser(Long userId, NamedParameterJdbcTemplate jdbcTemplate) {
        String sql = "SELECT * FROM users WHERE user_id = :user_id";

        Map<String, Object> params = new HashMap<>();
        params.put("user_id", userId);

        return Optional.ofNullable(jdbcTemplate.query(sql, params, new CreateOrderDaoImplementation.UserResultSetExtractor()));
    }

    @Override
    public Optional<Movie> fetchMovie(Long movieId, NamedParameterJdbcTemplate jdbcTemplate) {
        String sql = "SELECT * FROM movies WHERE movie_id = :movie_id";

        Map<String, Object> params = new HashMap<>();
        params.put("movie_id", movieId);

        return Optional.ofNullable(jdbcTemplate.query(sql, params, new UpdateOrderDaoImplementation.MovieResultSetExtractor()));
    }

    @Override
    public Optional<Order> fetchOrder(Long orderId, NamedParameterJdbcTemplate jdbcTemplate) {
        String sql = "SELECT * FROM orders WHERE order_id = :order_id";

        Map<String, Object> params = new HashMap<>();
        params.put("order_id", orderId);

        return Optional.ofNullable(jdbcTemplate.query(sql, params, new DeleteOrderDaoImplementation.OrderResultSetExtractor()));
    }

    public class UserResultSetExtractor implements ResultSetExtractor<User> {
        @Override
        public User extractData(ResultSet rs) throws SQLException, DataAccessException {
            rs.next();
            return User.builder()
                    .userId(rs.getLong("user_id"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .username(rs.getString("username"))
                    .password(rs.getString("password"))
                    .build();
        }
    }

    public class MovieResultSetExtractor implements ResultSetExtractor<Movie> {
        @Override
        public Movie extractData(ResultSet rs) throws SQLException, DataAccessException {
            rs.next();
            return Movie.builder()
                    .movieId(rs.getLong("movie_id"))
                    .name(rs.getString("name"))
                    .price(rs.getBigDecimal("price"))
                    .build();
        }
    }

    public class OrderResultSetExtractor implements ResultSetExtractor<Order> {
        @Override
        public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
            rs.next();
            return Order.builder()
                    .orderId(rs.getLong("order_id"))
                    .userId(rs.getLong("user_id"))
                    .movieId(rs.getLong("movie_id"))
                    .price(rs.getBigDecimal("price"))
                    .build();
        }
    }

    public class SqlParams {
        public String sql;
        public MapSqlParameterSource source = new MapSqlParameterSource();
    }

}
