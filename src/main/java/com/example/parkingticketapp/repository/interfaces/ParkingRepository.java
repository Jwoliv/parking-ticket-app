package com.example.parkingticketapp.repository.interfaces;

import com.example.parkingticketapp.model.Parking;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingRepository {
    Optional<Parking> findById(Long id);
    Optional<Parking> save(Parking parking);
    Parking deleteById(Long id);
    Parking updateById(Long id, Parking parking);
    void updateAvailableParkingSpaces(Long id, Long availableParkingSpaces);
}
