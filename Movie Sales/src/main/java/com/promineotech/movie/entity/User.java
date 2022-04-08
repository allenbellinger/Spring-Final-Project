package com.promineotech.movie.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
