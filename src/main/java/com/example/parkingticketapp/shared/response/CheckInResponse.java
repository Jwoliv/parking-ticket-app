package com.example.parkingticketapp.shared.response;

import com.example.parkingticketapp.shared.dto.ParkingDto;
import com.example.parkingticketapp.shared.dto.TicketDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CheckInResponse {
    private TicketDto ticket;
    private ParkingDto parking;
    private LocalDateTime checkInTime;
}