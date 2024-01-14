package com.example.parkingticketapp.service;

import com.example.parkingticketapp.exception.CustomException;
import com.example.parkingticketapp.mapper.ParkingMapper;
import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.repository.ParkingRepository;
import com.example.parkingticketapp.service.interfaces.ParkingService;
import com.example.parkingticketapp.shared.dto.ParkingDto;
import com.example.parkingticketapp.shared.mapper.CreatedResponseMapper;
import com.example.parkingticketapp.shared.mapper.DeletedResponseMapper;
import com.example.parkingticketapp.shared.response.CreatedResponse;
import com.example.parkingticketapp.shared.response.DeletedResponse;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.example.parkingticketapp.exception.data.MessageException.ELEMENT_WITH_ID_DOES_NOT_EXIST;

@Slf4j
@Service
public class ParkingServiceImpl implements ParkingService {
    @Setter(onMethod = @__(@Autowired))
    private ParkingRepository parkingRepository;
    @Setter(onMethod = @__(@Autowired))
    private ParkingMapper parkingMapper;
    @Setter(onMethod = @__(@Autowired))
    private DeletedResponseMapper deletedResponseMapper;
    @Setter(onMethod = @__(@Autowired))
    private CreatedResponseMapper createdResponseMapper;

    @Override
    public ResponseEntity<ParkingDto> generateInfoAboutParking(Long id) {
        Parking parking = parkingRepository.findById(id);
        checkExistedParking(id, parking);
        ParkingDto parkingDto = parkingMapper.entityParkingToDto(parking);
        return ResponseEntity.ok(parkingDto);
    }

    @Override
    public ResponseEntity<CreatedResponse<ParkingDto>> saveNewParking(ParkingDto parkingDto) {
        Parking parking = parkingMapper.dtoParkingToEntity(parkingDto);
        Parking savedParking = parkingRepository.save(parking).orElseThrow();
        ParkingDto newParkingDto =  parkingMapper.entityParkingToDto(savedParking);
        CreatedResponse<ParkingDto> response = createdResponseMapper.toResponse(newParkingDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<DeletedResponse<ParkingDto>> deleteById(Long id) {
        Parking parking = parkingRepository.deleteById(id);
        ParkingDto parkingDto = parkingMapper.entityParkingToDto(parking);
        DeletedResponse<ParkingDto> response = deletedResponseMapper.toResponse(parkingDto);
        return ResponseEntity.ok(response);
    }


    private static void checkExistedParking(Long id, Parking parking) {
        if (Objects.isNull(parking)) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format(ELEMENT_WITH_ID_DOES_NOT_EXIST, Parking.class.getSimpleName(), id));
        }
    }
}
