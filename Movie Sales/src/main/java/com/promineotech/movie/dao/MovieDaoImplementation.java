package com.promineotech.movie.dao;

import com.promineotech.movie.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
@Slf4j
public class MovieDaoImplementation implements MovieDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> fetchMovies() {
        log.info("DAO: The fetchMovies method was called.");

        String sql = "SELECT * FROM movies";

        return jdbcTemplate.query(sql, (rs, rowNum) -> Movie.builder()
                .movieId(rs.getLong("movie_id"))
                .name(rs.getString("name"))
                .price(new BigDecimal(rs.getString("price")))
                .build()
        );
    }
}
