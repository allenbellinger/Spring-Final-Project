package com.promineotech.movie.service;

import com.promineotech.movie.entity.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {
    List<Movie> fetchMovies();
}
