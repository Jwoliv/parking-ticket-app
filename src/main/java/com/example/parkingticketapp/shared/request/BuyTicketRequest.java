package com.example.parkingticketapp.shared.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BuyTicketRequest {
    private Long parkingId;
    private LocalDateTime startTime;
    private Float amountPayedMoney;
    private String personalKey;
}
