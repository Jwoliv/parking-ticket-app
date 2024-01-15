package com.example.parkingticketapp.shared.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TicketDto {
    private Long id;
    private Long numberPlace;
    private Float amountBonusMoney;
    private Integer amountPayedMoney;
    private Float change;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String key;
    private UserDto user;
    private ParkingDto parking;
}
