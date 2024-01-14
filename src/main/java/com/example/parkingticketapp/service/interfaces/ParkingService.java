package com.example.parkingticketapp.service.interfaces;

import com.example.parkingticketapp.shared.dto.ParkingDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ParkingService {

    ResponseEntity<ParkingDto> generateInfoAboutParking(Long id);
}
