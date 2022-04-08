package com.promineotech.movie.dao;

import com.promineotech.movie.entity.Movie;

import java.util.List;

public interface MovieDao {

    List<Movie> fetchMovies();
}
