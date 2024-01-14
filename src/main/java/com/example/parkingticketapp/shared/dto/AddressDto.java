package com.example.parkingticketapp.shared.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private Long id;
    private String city;
    private String street;
    private String number;
}
