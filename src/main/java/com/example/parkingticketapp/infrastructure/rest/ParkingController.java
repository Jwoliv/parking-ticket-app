package com.example.parkingticketapp.infrastructure.rest;

import com.example.parkingticketapp.service.interfaces.ParkingService;
import com.example.parkingticketapp.shared.dto.ParkingDto;
import com.example.parkingticketapp.shared.response.ActionResponse;
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
        return ResponseEntity.ok(parkingService.generateInfoAboutParking(id));
    }

    @PutMapping
    public ResponseEntity<ActionResponse<ParkingDto>> updateParkingInfoById(@RequestBody ParkingDto parkingDto) {
        return ResponseEntity.ok(parkingService.updateExistedParking(parkingDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActionResponse<ParkingDto>> deleteParkingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(parkingService.deleteById(id));
    }

    @PostMapping
    public ResponseEntity<ActionResponse<ParkingDto>> saveNewParking(@RequestBody ParkingDto parking) {
        return ResponseEntity.ok(parkingService.saveNewParking(parking));
    }
}
