package com.promineotech.movie.controller;

import com.promineotech.movie.entity.Order;
import com.promineotech.movie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class OrderControllerImplementation implements OrderController {

    @Autowired
    private OrderService orderService;

    @Override
    public Order createOrder(Long userId, Long movieId) {
        log.info("Controller: Order with user_id={} and movie_id={} was created.", userId, movieId);
        return orderService.createOrder(userId, movieId);
    }

    @Override
    public List<Order> fetchOrders() {
        log.info("Controller: All orders were returned.");
        return orderService.fetchOrders();
    }

    @Override
    public Order updateOrder(Long orderId, Long movieId) {
        log.info("Controller: Order with order_id={} was updated with movie_id={}.", orderId, movieId);
        return orderService.updateOrder(orderId, movieId);
    }

    @Override
    public Order deleteOrder(Long orderId) {
        log.info("Controller: Order with order_id={} was deleted.", orderId);
        return orderService.deleteOrder(orderId);
    }
}
