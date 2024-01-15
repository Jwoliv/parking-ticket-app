package com.example.parkingticketapp.repository;

import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.repository.interfaces.ParkingRepository;
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
public class ParkingRepositoryImpl implements ParkingRepository {
    @Setter(onMethod = @__(@Autowired))
    private SessionFactory sessionFactory;

    @Override
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

    @Override
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

    @Override
    public Parking deleteById(Long id) {
        Transaction transaction = null;
        Parking parking = new Parking();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Parking> optParking = Optional.ofNullable(session.find(Parking.class, id));
            if (optParking.isPresent()) {
                session.remove(optParking.get());
                parking = optParking.get();
            }
            transaction.commit();
        } catch (Exception ex) {
            catchException(ex, transaction);
        }
        return parking;
    }


    @Override
    public Parking updateById(Long id, Parking parking) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Parking> optParking = Optional.ofNullable(session.find(Parking.class, id));
            if (optParking.isPresent()) {
                session.merge(parking);
            }
            transaction.commit();
        } catch (Exception ex) {
            catchException(ex, transaction);
        }
        return parking;
    }

    @Override
    public void updateAvailableParkingSpaces(Long id, Long availableParkingSpaces) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Parking> optParking = Optional.ofNullable(session.find(Parking.class, id));
            if (optParking.isPresent()) {
                Parking parking = optParking.get();
                parking.setAvailableParkingSpaces(availableParkingSpaces);
                session.merge(parking);
            }
            transaction.commit();
        } catch (Exception ex) {
            catchException(ex, transaction);
        }
    }

    private void catchException(Exception ex, Transaction tr) {
        log.error(ex.getMessage(), ex);
        Objects.requireNonNull(tr).rollback();
    }

    private void catchException(Exception ex) {
        log.error(ex.getMessage(), ex);
    }

}
