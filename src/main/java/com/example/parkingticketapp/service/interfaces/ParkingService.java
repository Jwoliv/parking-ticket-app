package com.example.parkingticketapp.service.interfaces;

import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.shared.dto.ParkingDto;
import com.example.parkingticketapp.shared.response.ActionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ParkingService {
    Parking findById(Long id);
    ResponseEntity<ParkingDto> generateInfoAboutParking(Long id);
    ResponseEntity<ActionResponse<ParkingDto>> saveNewParking(ParkingDto parking);
    ResponseEntity<ActionResponse<ParkingDto>> deleteById(Long id);
    ResponseEntity<ActionResponse<ParkingDto>> updateExistedParking(ParkingDto parkingDto);
}
