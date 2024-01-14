package com.example.parkingticketapp.shared.response;


import com.example.parkingticketapp.shared.enums.CrudAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionResponse<T> {
    private T element;
    private CrudAction action;
    private LocalDateTime timeAction;
    private Boolean isSucceed;
}
