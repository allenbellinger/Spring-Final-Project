package com.promineotech.movie;

import com.promineotech.ComponentScanMarker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class MovieSales {

    public static void main(String[] args) {
        SpringApplication.run(MovieSales.class, args);
    }
}