package com.promineotech.movie.service;

import com.promineotech.movie.dao.OrderDao;
import com.promineotech.movie.dao.createorder.CreateOrderDao;
import com.promineotech.movie.dao.deleteorder.DeleteOrderDao;
import com.promineotech.movie.dao.readorder.ReadOrderDao;
import com.promineotech.movie.dao.updateorder.UpdateOrderDao;
import com.promineotech.movie.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class OrderServiceImplementation implements OrderService {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private CreateOrderDao createOrderDao;

    @Autowired
    private ReadOrderDao readOrderDao;

    @Autowired
    private UpdateOrderDao updateOrderDao;

    @Autowired
    private DeleteOrderDao deleteOrderDao;

    @Qualifier("orderDaoImplementation")
    @Autowired
    private OrderDao orderDao;

    @Transactional
    @Override
    public Order createOrder(Long userId, Long movieId) {
        log.info("Service: The createOrder method was called.");

        User user = getUser(userId);
        Movie movie = getMovie(movieId);

        return createOrderDao.saveOrder(user, movie, jdbcTemplate);
    }

    @Transactional
    @Override
    public List<Order> fetchOrders() {
        log.info("Service: The fetchOrders method was called.");

        List<Order> orders = readOrderDao.fetchOrders();

        if(orders.isEmpty()) {
            String msg = "No orders were found.";
            throw new NoSuchElementException(msg);
        }

        return orders;
    }

    @Override
    public Order updateOrder(Long orderId, Long movieId) {
        log.info("Service: The updateOrder method was called.");

        Order order = getOrder(orderId);
        Movie movie = getMovie(movieId);

        return updateOrderDao.updateOrder(order, movie, jdbcTemplate);
    }

    @Override
    public Order deleteOrder(Long orderId) {
        log.info("Service: The deleteOrder method was called.");

        Order order = getOrder(orderId);

        return deleteOrderDao.deleteOrder(order, jdbcTemplate);
    }

    private User getUser(Long userId) {
        return orderDao.fetchUser(userId, jdbcTemplate).orElseThrow(
                () -> new NoSuchElementException("User with ID=" + userId + " was not found"));
    }

    private Movie getMovie(Long movieId) {
        return orderDao.fetchMovie(movieId, jdbcTemplate).orElseThrow(
                () -> new NoSuchElementException("Movie with ID=" + movieId + " was not found"));
    }

    private Order getOrder(Long orderId) {
        return orderDao.fetchOrder(orderId, jdbcTemplate).orElseThrow(
                () -> new NoSuchElementException("Order with ID=" + orderId + " was not found"));
    }
}
