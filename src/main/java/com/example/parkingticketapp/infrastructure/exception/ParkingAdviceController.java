package com.example.parkingticketapp.infrastructure.exception;

import com.example.parkingticketapp.exception.CustomException;
import com.example.parkingticketapp.shared.dto.CustomExceptionDto;
import com.example.parkingticketapp.mapper.ExceptionDtoMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParkingAdviceController {
    @Setter(onMethod = @__(@Autowired))
    private ExceptionDtoMapper exceptionDtoMapper;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionDto> handleCustomException(CustomException ex) {
        return ResponseEntity.ok(exceptionDtoMapper.exceptionToDto(ex));
    }
}
