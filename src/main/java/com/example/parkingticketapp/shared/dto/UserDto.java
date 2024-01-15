package com.example.parkingticketapp.shared.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String surname;
    private Float change;
    private Date dateBorn;
    private String personalKey;
    private Float bonusMoney;
    private List<TicketDto> tickets;
}
