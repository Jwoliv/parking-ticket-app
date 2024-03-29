package com.example.parkingticketapp.service.interfaces;

import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.shared.dto.ParkingDto;
import com.example.parkingticketapp.shared.request.ActivityParkingRequest;
import com.example.parkingticketapp.shared.response.ActionResponse;
import com.example.parkingticketapp.shared.response.ParkingActivityResponse;
import org.springframework.stereotype.Service;

@Service
public interface ParkingService {
    Parking findById(Long id);
    ParkingDto generateInfoAboutParking(Long id);
    ActionResponse<ParkingDto> saveNewParking(ParkingDto parking);
    ActionResponse<ParkingDto> deleteById(Long id);
    ActionResponse<ParkingDto> updateExistedParking(ParkingDto parkingDto);
    void updateAvailableParkingSpaces(Long id, Long availableParkingSpaces);
    Boolean checkAvailableSeats(Parking parking);
    ParkingActivityResponse checkInToParking(ActivityParkingRequest check, Ticket ticket);
    ParkingActivityResponse checkOutToParking(ActivityParkingRequest check, Ticket ticket);
}
