package com.promineotech.movie.dao.readorder;

import com.promineotech.movie.entity.*;

import java.util.List;

public interface ReadOrderDao {

    List<Order> fetchOrders();
}