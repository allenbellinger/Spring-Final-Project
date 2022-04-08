package com.promineotech.movie.controller;

import java.util.List;

import com.promineotech.movie.entity.Movie;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RequestMapping("/movies")
@OpenAPIDefinition(info = @Info(title = "Movie Service"), servers = {
        @Server(url = "http://localhost:8080", description = "Local server.")})
public interface MovieController {

    @Operation(
            summary = "Returns the list of movies.",
            description = "Returns all movies that exist in the database.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of movies is returned.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Movie.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The request parameters are invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "There were no movies found.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred.",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<Movie> fetchMovies();
}
