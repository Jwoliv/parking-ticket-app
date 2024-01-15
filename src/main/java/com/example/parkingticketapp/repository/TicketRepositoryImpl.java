package com.example.parkingticketapp.repository;

import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.repository.interfaces.TicketRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Repository
public class TicketRepositoryImpl implements TicketRepository {
    @Setter(onMethod = @__(@Autowired))
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public Ticket findById(Long id) {
        Ticket ticket = new Ticket();
        try (Session session = sessionFactory.openSession()) {
            ticket = session.find(Ticket.class, id);
        } catch (Exception ex) {
            catchException(ex);
        }
        return ticket;
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
        } catch (Exception ex) {
            catchException(ex, transaction);
        }
        return Optional.of(ticket);
    }

    @Override
    public Ticket deleteById(Long id) {
        Transaction transaction = null;
        Ticket ticket = new Ticket();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Ticket> optTicket = Optional.ofNullable(session.find(Ticket.class, id));
            if (optTicket.isPresent()) {
                session.remove(optTicket.get());
                ticket = optTicket.get();
            }
            transaction.commit();
        } catch (Exception ex) {
            catchException(ex, transaction);
        }
        return ticket;
    }


    @Override
    public Ticket updateById(Long id, Ticket ticket) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Ticket> optTicket = Optional.ofNullable(session.find(Ticket.class, id));
            if (optTicket.isPresent()) {
                session.merge(ticket);
            }
            transaction.commit();
        } catch (Exception ex) {
            catchException(ex, transaction);
        }
        return ticket;
    }

    private void catchException(Exception ex, Transaction tr) {
        log.error(ex.getMessage(), ex);
        Objects.requireNonNull(tr).rollback();
    }

    private void catchException(Exception ex) {
        log.error(ex.getMessage(), ex);
    }
}