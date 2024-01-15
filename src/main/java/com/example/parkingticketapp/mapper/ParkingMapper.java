package com.example.parkingticketapp.mapper;

import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.shared.dto.ParkingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, ParkingDto.class, UserMapper.class})
public interface ParkingMapper {
    ParkingDto entityParkingToDto(Parking parking);
    Parking dtoParkingToEntity(ParkingDto parkingDto);
}
