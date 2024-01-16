package com.example.parkingticketapp.utils;

import com.example.parkingticketapp.enums.TypeAction;
import com.example.parkingticketapp.mapper.ParkingMapper;
import com.example.parkingticketapp.mapper.TicketMapper;
import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.shared.response.ParkingActivityResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ParkingActivityResponseGenerator {
    @Setter(onMethod = @__(@Autowired))
    private TicketMapper ticketMapper;
    @Setter(onMethod = @__(@Autowired))
    private ParkingMapper parkingMapper;

    public ParkingActivityResponse generateParkingActivityResponse(Ticket ticket, Parking parking, TypeAction typeAction) {
        return ParkingActivityResponse.builder()
                .ticket(ticketMapper.entityTicketToDto(ticket))
                .parking(parkingMapper.entityParkingToDto(parking))
                .typeAction(typeAction)
                .activeTime(LocalDateTime.now())
                .build();
    }
}
