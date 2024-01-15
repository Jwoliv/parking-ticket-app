package com.example.parkingticketapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BeanConfig {
    @Bean
    private Random random() {
        return new Random();
    }
}
