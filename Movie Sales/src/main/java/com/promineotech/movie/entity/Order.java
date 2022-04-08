package com.promineotech.movie.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Order {
  private Long orderId;
  private Long userId;
  private Long movieId;
  private BigDecimal price;
}
