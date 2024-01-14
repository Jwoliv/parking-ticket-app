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
public class DeleteResponse<T> {
    private T deletedElement;
    private LocalDateTime timeDeleted;
    private Boolean isSucceed;
}
