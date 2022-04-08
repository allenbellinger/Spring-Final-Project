package com.promineotech.movie.controller;

import java.util.List;

import com.promineotech.movie.entity.Movie;
import com.promineotech.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MovieControllerImplementation implements MovieController {
    @Autowired
    private MovieService movieService;

    @Override
    public List<Movie> fetchMovies() {
        log.info("Controller: The list of movies was returned");
        return movieService.fetchMovies();
    }
}
