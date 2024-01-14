package com.example.parkingticketapp.shared.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TicketDto {
    private Long id;
    private Long numberPlace;
    private Float amountPayedMoney;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String key;
    private ParkingDto parking;
    private UserDto user;
}
