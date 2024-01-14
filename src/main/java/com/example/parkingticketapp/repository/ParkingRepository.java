package com.example.parkingticketapp.repository;

import com.example.parkingticketapp.model.Parking;
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
@Transactional
public class ParkingRepository {
    @Setter(onMethod = @__(@Autowired))
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public Parking findById(Long id) {
        Parking parking = new Parking();
        try (Session session = sessionFactory.openSession()) {
            parking = session.find(Parking.class, id);
        } catch (Exception ex) {
            catchException(ex);
        }
        return parking;
    }

    public Optional<Parking> save(Parking parking) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(parking);
            transaction.commit();
        } catch (Exception ex) {
            catchException(ex, transaction);
        }
        return Optional.of(parking);
    }

    public Boolean deleteById(Long id) {
        Transaction transaction = null;
        boolean deletedItemExisted = false;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            Optional<Parking> optParking = Optional.ofNullable(session.getReference(Parking.class, id));
            if (optParking.isPresent()) {
                session.remove(optParking.get());
                deletedItemExisted = true;
            }
            transaction.commit();
        } catch (Exception ex) {
            catchException(ex, transaction);
        }
        return deletedItemExisted;
    }

    private void catchException(Exception ex, Transaction tr) {
        log.error(ex.getMessage(), ex);
        Objects.requireNonNull(tr).rollback();
    }

    private void catchException(Exception ex) {
        log.error(ex.getMessage(), ex);
    }

}
