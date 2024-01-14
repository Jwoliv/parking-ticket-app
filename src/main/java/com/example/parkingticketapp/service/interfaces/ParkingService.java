package com.example.parkingticketapp.service.interfaces;

import com.example.parkingticketapp.shared.dto.ParkingDto;
import com.example.parkingticketapp.shared.response.CreatedResponse;
import com.example.parkingticketapp.shared.response.DeletedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ParkingService {

    ResponseEntity<ParkingDto> generateInfoAboutParking(Long id);
    ResponseEntity<CreatedResponse<ParkingDto>> saveNewParking(ParkingDto parking);
    ResponseEntity<DeletedResponse<ParkingDto>> deleteById(Long id);
}
