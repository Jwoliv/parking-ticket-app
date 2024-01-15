package com.example.parkingticketapp.mapper;

import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Float TEN_PERCENTAGE = 0.1F;

    @Mapping(target = "user.change", source = "ticket.user.ticket")
    @Mapping(target = "user.bonusMoney", expression = "java(generateBonusMoney(user,ticket))")
    @Mapping(target = "user.tickets", expression = "java(addTicketToUser(user,ticket))")
    User updateUserChange(User user, Ticket ticket);

    default Float generateBonusMoney(User user, Ticket ticket) {
        return user.getBonusMoney() + (ticket.getAmountBonusMoney() * TEN_PERCENTAGE);
    }

    default List<Ticket> addTicketToUser(User user, Ticket ticket) {
        List<Ticket> updatedTickets = ticket.getUser().getTickets();
        updatedTickets.add(ticket);
        return updatedTickets;
    }
}
