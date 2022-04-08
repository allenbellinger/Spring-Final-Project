package com.promineotech.movie.service;

import com.promineotech.movie.dao.MovieDao;
import com.promineotech.movie.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class MovieServiceImplementation implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Transactional(readOnly = true)
    @Override
    public List<Movie> fetchMovies() {
        log.info("Service: The fetchMovies method was called.");

        List<Movie> movies = movieDao.fetchMovies();

        if(movies.isEmpty()) {
            String msg = "No movies were found.";
            throw new NoSuchElementException(msg);
        }

        return movies;
    }
}
