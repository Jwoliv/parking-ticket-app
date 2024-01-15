package com.example.parkingticketapp.repository;

import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.model.User;
import com.example.parkingticketapp.repository.interfaces.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Transactional
@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final Float TEN_PERCENTAGE = 0.1F;

    @Setter(onMethod = @__(@Autowired))
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        User user = new User();
        try (Session session = sessionFactory.openSession()) {
            user = session.find(User.class, id);
        } catch (Exception ex) {
            catchException(ex);
        }
        return user;
    }

    @Override
    public Optional<User> save(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception ex) {
            catchException(ex, transaction);
        }
        return Optional.of(user);
    }

    @Override
    public User deleteById(Long id) {
        Transaction transaction = null;
        User user = new User();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Optional<User> optUser = Optional.ofNullable(session.find(User.class, id));
            if (optUser.isPresent()) {
                user = optUser.get();
                session.remove(optUser.get());
            }
            transaction.commit();
        } catch (Exception ex) {
            catchException(ex, transaction);
        }
        return user;
    }


    @Override
    public User updateById(Long id, User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Optional<User> optUser = Optional.ofNullable(session.find(User.class, id));
            if (optUser.isPresent()) {
                session.merge(user);
            }
            transaction.commit();
        } catch (Exception ex) {
            catchException(ex, transaction);
        }
        return user;
    }

    @Override
    public User findByPersonalKey(String personalKey) {
        User user = new User();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("personal_key"), personalKey));
            user = session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception ex) {
            catchException(ex);
        }
        return user;
    }


    @Override
    public User updateUserFields(User user, Ticket ticket) {
        user.setChange(generateChange(user, ticket));
        user.setBonusMoney(generateBonusMoney(user, ticket));
        user.setTickets(addTicketToUser(user, ticket));
        return user;
    }

    private Float generateChange(User user, Ticket ticket) {
        Float ticketChange = ticket.getChange();
        return user.getChange() + ticketChange;
    }

    private Float generateBonusMoney(User user, Ticket ticket) {
        return user.getBonusMoney() + (ticket.getAmountBonusMoney() * TEN_PERCENTAGE);
    }

    private List<Ticket> addTicketToUser(User user, Ticket ticket) {
        List<Ticket> updatedTickets = user.getTickets();
        updatedTickets.add(ticket);
        return updatedTickets;
    }


    private void catchException(Exception ex, Transaction tr) {
        log.error(ex.getMessage(), ex);
        Objects.requireNonNull(tr).rollback();
    }

    private void catchException(Exception ex) {
        log.error(ex.getMessage(), ex);
    }
}
