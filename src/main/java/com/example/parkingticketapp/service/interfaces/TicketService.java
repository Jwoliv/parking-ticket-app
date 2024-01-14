package com.example.parkingticketapp.service.interfaces;

import com.example.parkingticketapp.shared.dto.TicketDto;
import com.example.parkingticketapp.shared.request.BuyTicketRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TicketService {
    ResponseEntity<TicketDto> buyTicket(BuyTicketRequest request);
}
