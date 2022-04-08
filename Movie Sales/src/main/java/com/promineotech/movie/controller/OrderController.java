package com.promineotech.movie.controller;

import com.promineotech.movie.entity.Order;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RequestMapping("/orders")
@OpenAPIDefinition(info = @Info(title = "Movie Order Service"), servers = {
        @Server(url = "http://localhost:8080", description = "Local server.")})
public interface OrderController {

    @Operation(
            summary = "Create an order for a movie.",
            description = "Returns the created order.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The created order is returned.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Order.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The request parameters are invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "A movie or user was not found with the input criteria.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred.",
                            content = @Content(mediaType = "application/json"))
            },
            parameters = {
                    @Parameter(
                            name = "userId",
                            required = true,
                            description = "The user_id for the order."),
                    @Parameter(
                            name = "movieId",
                            required = true,
                            description = "The movie_id for the order.")
            }
    )
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    Order createOrder(@RequestParam Long userId, @RequestParam Long movieId);

    @Operation(
            summary = "Retrieve all orders.",
            description = "Returns all orders that exist in the database.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of orders is returned.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Order.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The request parameters are invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No orders were found.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred.",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<Order> fetchOrders();

    @Operation(
            summary = "Update an order.",
            description = "Returns the updated order given the user_id and movie_id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The updated order is returned.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Order.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The request parameters are invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "An order was not found with given user_id and movie_id.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred.",
                            content = @Content(mediaType = "application/json"))
            },
            parameters = {
                    @Parameter(
                            name = "orderId",
                            required = true,
                            description = "The order_id of the order you want to change."),
                    @Parameter(
                            name = "movieId",
                            required = true,
                            description = "The movie_id of the new movie you want.")
            }
    )
    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    Order updateOrder(@RequestParam Long orderId, @RequestParam Long movieId);

    @Operation(
            summary = "Delete an order.",
            description = "Returns the deleted order given a user_id and movie_id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The deleted order is returned.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Order.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The request parameters are invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "An order was not found with given user_id and movie_id.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred.",
                            content = @Content(mediaType = "application/json"))
            },
            parameters = {
                    @Parameter(
                            name = "orderId",
                            required = true,
                            description = "The order_id of the order you want to delete")
            }
    )
    @DeleteMapping
    @ResponseStatus(code = HttpStatus.OK)
    Order deleteOrder(@RequestParam Long orderId);
}
