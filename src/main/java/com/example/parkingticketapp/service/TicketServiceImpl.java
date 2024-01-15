package com.example.parkingticketapp.service;

import com.example.parkingticketapp.mapper.TicketMapper;
import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.model.User;
import com.example.parkingticketapp.repository.interfaces.TicketRepository;
import com.example.parkingticketapp.service.interfaces.ParkingService;
import com.example.parkingticketapp.service.interfaces.TicketService;
import com.example.parkingticketapp.service.interfaces.UserService;
import com.example.parkingticketapp.shared.dto.TicketDto;
import com.example.parkingticketapp.shared.request.BuyTicketRequest;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {
    private static final Float TEN_PERCENTAGE = 0.1F;

    @Setter(onMethod = @__(@Autowired))
    private UserService userService;
    @Setter(onMethod = @__(@Autowired))
    private ParkingService parkingService;
    @Setter(onMethod = @__(@Autowired))
    private TicketRepository ticketRepository;
    @Setter(onMethod = @__(@Autowired))
    private TicketMapper ticketMapper;
    @Setter(onMethod = @__(@Autowired))
    private Random random;

    @Override
    public ResponseEntity<TicketDto> buyTicket(BuyTicketRequest request) {
        User user = userService.findByPersonalKey(request.getPersonalKey()).orElseThrow();
        Parking parking = parkingService.findById(request.getParkingId());

        if (parking.getAvailableParkingSpaces() == 0) {
            return null; // todo throw error
        }

        Float payedMoney = request.getAmountPayedMoney();
        Float pricePerHour = parking.getPricePerHour();

        if (payedMoney > pricePerHour) {
            return ResponseEntity.ok(createTicket(parking, request, user));
        }
        return null;
    }

    private TicketDto createTicket(Parking parking, BuyTicketRequest request, User user) {
        Ticket ticket = ticketMapper.generateTicketBeforeSave(request, parking, user, random);
        ticket = ticketRepository.save(ticket).orElseThrow();
        updateUserChange(user, ticket);
        return null;
    }

    private void updateUserChange(User user, Ticket ticket) {
        user.setBonusMoney(ticket.getUser().getChange());
        user.setBonusMoney(user.getBonusMoney() + (ticket.getAmountBonusMoney() * TEN_PERCENTAGE));
        user.getTickets().add(ticket);
        userService.update(user);
    }
}
