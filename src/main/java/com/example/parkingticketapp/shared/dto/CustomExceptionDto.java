package com.example.parkingticketapp.shared.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomExceptionDto {
    private HttpStatus status;
    private String message;
    private LocalDateTime localDateTime;
}
