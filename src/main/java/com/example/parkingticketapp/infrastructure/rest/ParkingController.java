package com.example.parkingticketapp.infrastructure.rest;

import com.example.parkingticketapp.service.interfaces.ParkingService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {
    @Setter(onMethod = @__(@Autowired))
    private ParkingService parkingService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findParkingInfoById(@PathVariable("id") Long id) {
        return parkingService.generateInfoAboutParking(id);
    }
}
