package com.example.parkingticketapp.shared.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatedResponse<T> {
    private T createdElement;
    private LocalDateTime timeCreated;
}
