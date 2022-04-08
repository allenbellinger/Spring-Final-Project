package com.promineotech.movie.service;

import com.promineotech.movie.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Long userId, Long movieId);

    List<Order> fetchOrders();

    Order updateOrder(Long orderId, Long movieId);

    Order deleteOrder(Long orderId);
}
