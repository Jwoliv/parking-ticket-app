package com.example.parkingticketapp.shared.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingDto {
    private Long id;
    private String title;
    private String description;
    private AddressDto address;
    private Long totalParkingSpaces;
    private Long availableParkingSpaces;
    private Float pricePerHour;
}
