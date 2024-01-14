package com.example.parkingticketapp.service.interfaces;

import com.example.parkingticketapp.shared.dto.ParkingDto;
import com.example.parkingticketapp.shared.response.ActionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ParkingService {

    ResponseEntity<ParkingDto> generateInfoAboutParking(Long id);
    ResponseEntity<ActionResponse<ParkingDto>> saveNewParking(ParkingDto parking);
    ResponseEntity<ActionResponse<ParkingDto>> deleteById(Long id);
    ResponseEntity<ParkingDto> updateExistedParking(ParkingDto parkingDto);
}
