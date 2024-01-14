package com.example.parkingticketapp.service.interfaces;

import com.example.parkingticketapp.shared.dto.ParkingDto;
import com.example.parkingticketapp.shared.response.DeleteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ParkingService {

    ResponseEntity<ParkingDto> generateInfoAboutParking(Long id);
    ResponseEntity<ParkingDto> saveNewParking(ParkingDto parking);
    ResponseEntity<DeleteResponse<ParkingDto>> deleteById(Long id);
}
