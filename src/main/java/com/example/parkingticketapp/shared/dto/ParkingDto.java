package com.example.parkingticketapp.shared.dto;

import com.example.parkingticketapp.model.Address;
import com.example.parkingticketapp.model.Ticket;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ParkingDto {
    private Long id;
    private String title;
    private String description;
    private Address address;
    private Long totalParkingSpaces;
    private Long availableParkingSpaces;
    private Float pricePerHour;
    private List<Ticket> tickets;
}
