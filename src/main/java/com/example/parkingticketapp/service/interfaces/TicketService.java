package com.example.parkingticketapp.service.interfaces;

import com.example.parkingticketapp.shared.dto.TicketDto;
import com.example.parkingticketapp.shared.request.BuyTicketRequest;
import org.springframework.stereotype.Service;

@Service
public interface TicketService {
    TicketDto buyTicket(BuyTicketRequest request);
}
