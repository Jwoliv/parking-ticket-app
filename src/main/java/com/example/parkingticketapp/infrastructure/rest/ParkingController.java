package com.example.parkingticketapp.infrastructure.rest;

import com.example.parkingticketapp.service.interfaces.ParkingService;
import com.example.parkingticketapp.shared.dto.ParkingDto;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {
    @Setter(onMethod = @__(@Autowired))
    private ParkingService parkingService;

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDto> findParkingInfoById(@PathVariable("id") Long id) {
        return parkingService.generateInfoAboutParking(id);
    }

    @PostMapping
    public ResponseEntity<ParkingDto> saveNewParking(@RequestBody ParkingDto parking) {
        return parkingService.saveNewParking(parking);
    }
}
