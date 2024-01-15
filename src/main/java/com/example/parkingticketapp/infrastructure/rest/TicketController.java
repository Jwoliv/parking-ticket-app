package com.example.parkingticketapp.infrastructure.rest;

import com.example.parkingticketapp.service.interfaces.TicketService;
import com.example.parkingticketapp.shared.dto.TicketDto;
import com.example.parkingticketapp.shared.request.BuyTicketRequest;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Setter(onMethod = @__(@Autowired))
    private TicketService ticketService;

    @PostMapping("/buy-ticket")
    private ResponseEntity<TicketDto> buyTicket(@RequestBody BuyTicketRequest request) {
        return ticketService.buyTicket(request);
    }
}
