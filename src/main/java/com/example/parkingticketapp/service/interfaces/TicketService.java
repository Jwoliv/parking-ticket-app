package com.example.parkingticketapp.service.interfaces;

import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.shared.dto.TicketDto;
import com.example.parkingticketapp.shared.request.BuyTicketRequest;
import org.springframework.stereotype.Service;

@Service
public interface TicketService {
    Ticket buyTicket(BuyTicketRequest request);
    Ticket findByKey(String ticketKey);
}
