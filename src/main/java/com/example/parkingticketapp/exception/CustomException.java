package com.example.parkingticketapp.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomException extends RuntimeException {
    private final HttpStatus status;
    private final Integer codeResponse;
    private final String message;
    private final LocalDateTime localDateTime;

    public CustomException(HttpStatus status, String message) {
        this.status = status;
        this.codeResponse = status.value();
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }
}
