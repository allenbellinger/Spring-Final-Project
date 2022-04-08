package com.promineotech.movie.controller;

import com.promineotech.movie.entity.User;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Validated
@RequestMapping("/users")
@OpenAPIDefinition(info = @Info(title = "Users Service"), servers = {
        @Server(url = "http://localhost:8080", description = "Local server.")})
public interface UserController {
    @Operation(
            summary = "Create a user",
            description = "Returns the created user",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The created user is returned.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The request parameters are invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "A created user was not found.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred.",
                            content = @Content(mediaType = "application/json"))
            },
            parameters = {
                    @Parameter(
                            name = "firstName",
                            required = true,
                            description = "The user's first name."),
                    @Parameter(
                            name = "lastName",
                            required = true,
                            description = "The user's last name."),
                    @Parameter(
                            name = "username",
                            required = true,
                            description = "The user's username."),
                    @Parameter(
                            name = "password",
                            required = true,
                            description = "The user's password.")
            }
    )
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    User createUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username, @RequestParam String password);
}
