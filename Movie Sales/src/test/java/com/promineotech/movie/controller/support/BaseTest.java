package com.promineotech.movie.controller.support;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

public class BaseTest {
    @LocalServerPort
    private int serverPort;

    @Autowired
    @Getter
    private TestRestTemplate restTemplate;

    protected String getBaseUriForMovies() {
        return String.format("http://localhost:%d/movies", serverPort);
    }

    protected String getBaseUriForOrders() {
        return String.format("http://localhost:%d/orders", serverPort);
    }

    protected String getBaseUriForUsers() {
        return String.format("http://localhost:%d/users", serverPort);
    }
}
