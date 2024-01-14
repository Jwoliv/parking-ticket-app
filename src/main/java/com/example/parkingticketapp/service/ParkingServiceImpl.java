package com.example.parkingticketapp.service;

import com.example.parkingticketapp.mapper.ParkingMapper;
import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.repository.ParkingRepository;
import com.example.parkingticketapp.service.interfaces.ParkingService;
import com.example.parkingticketapp.shared.dto.ParkingDto;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ParkingServiceImpl implements ParkingService {
    @Setter(onMethod = @__(@Autowired))
    private ParkingRepository parkingRepository;
    @Setter(onMethod = @__(@Autowired))
    private ParkingMapper parkingMapper;

    @Override
    public ResponseEntity<ParkingDto> generateInfoAboutParking(Long id) {
        Parking parking = parkingRepository.findById(id);
        ParkingDto parkingDto = parkingMapper.entityParkingToDto(parking);
        return ResponseEntity.ok(parkingDto);
    }

    @Override
    public ResponseEntity<ParkingDto> saveNewParking(ParkingDto parkingDto) {
        Parking parking = parkingMapper.dtoParkingToEntity(parkingDto);
        Parking savedParking = parkingRepository.save(parking).orElseThrow();
        return ResponseEntity.ok(parkingMapper.entityParkingToDto(savedParking));
    }
}
