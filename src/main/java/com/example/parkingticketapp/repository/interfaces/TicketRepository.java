package com.example.parkingticketapp.repository.interfaces;

import com.example.parkingticketapp.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository {
    Ticket findById(Long id);
    Optional<Ticket> save(Ticket ticket);
    Ticket deleteById(Long id);
    Ticket updateById(Long id, Ticket ticket);
    Optional<Ticket> findByKey(String ticketKey);
}
