package com.promineotech.movie.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Movie{
    private Long movieId;
    private String name;
    private BigDecimal price;
}
