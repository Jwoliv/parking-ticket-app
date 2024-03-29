package com.example.parkingticketapp.service;

import com.example.parkingticketapp.mapper.TicketMapper;
import com.example.parkingticketapp.mapper.UserMapper;
import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.model.User;
import com.example.parkingticketapp.repository.interfaces.TicketRepository;
import com.example.parkingticketapp.service.interfaces.ParkingService;
import com.example.parkingticketapp.service.interfaces.TicketService;
import com.example.parkingticketapp.service.interfaces.UserService;
import com.example.parkingticketapp.shared.dto.TicketDto;
import com.example.parkingticketapp.shared.dto.UserDto;
import com.example.parkingticketapp.shared.request.BuyTicketRequest;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {

    @Setter(onMethod = @__(@Autowired))
    private UserService userService;
    @Setter(onMethod = @__(@Autowired))
    private ParkingService parkingService;
    @Setter(onMethod = @__(@Autowired))
    private TicketRepository ticketRepository;
    @Setter(onMethod = @__(@Autowired))
    private TicketMapper ticketMapper;
    @Setter(onMethod = @__(@Autowired))
    private UserMapper userMapper;
    @Setter(onMethod = @__(@Autowired))
    private Random random;

    @Override
    public Ticket buyTicket(BuyTicketRequest request) {
        User user = userService.findByPersonalKey(request.getPersonalKey()).orElseThrow();
        Parking parking = parkingService.findById(request.getParkingId());
        if (!parkingService.checkAvailableSeats(parking)) {
            return null; // todo throw error
        }
        if (checkAmountPaymentMoney(request, parking)) {
            return createTicket(parking, request, user);
        }
        return Ticket.builder().build();
    }

    @Override
    public Ticket findByKey(String ticketKey) {
        return ticketRepository.findByKey(ticketKey).orElseThrow();
    }

    private Boolean checkAmountPaymentMoney(BuyTicketRequest request, Parking parking) {
        Float payedMoney = request.getAmountPayedMoney();
        Float pricePerHour = parking.getPricePerHour();
        return payedMoney > pricePerHour;
    }

    private Ticket createTicket(Parking parking, BuyTicketRequest request, User user) {
        Ticket ticket = ticketMapper.generateTicketBeforeSave(request, parking, user, random);
        Ticket savedTicket = ticketRepository.save(ticket).orElseThrow();
        updateUserChange(user, savedTicket);
        decreesAvailableSpacesForParking(parking);
        return savedTicket;
    }

    private void decreesAvailableSpacesForParking(Parking parking) {
        Long availableParkingSpaces = parking.getAvailableParkingSpaces() - 1;
        parkingService.updateAvailableParkingSpaces(parking.getId(), availableParkingSpaces);
    }

    private void updateUserChange(User user, Ticket ticket) {
        User updatedUser = userService.updateUserFields(user, ticket);
        UserDto updatedUserDto = userMapper.entityUserToDto(updatedUser);
        userService.update(updatedUserDto);
    }
}
