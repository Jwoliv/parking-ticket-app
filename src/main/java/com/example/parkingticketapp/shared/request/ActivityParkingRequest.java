package com.example.parkingticketapp.shared.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActivityParkingRequest {
    private Long parkingId;
    private String ticketKey;
    private String personalKey;
}
