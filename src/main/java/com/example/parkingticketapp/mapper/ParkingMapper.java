package com.example.parkingticketapp.mapper;

import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.shared.dto.ParkingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, ParkingDto.class, UserMapper.class})
public interface ParkingMapper {
    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "address", ignore = true)
    ParkingDto entityParkingToDto(Parking parking);
    Parking dtoParkingToEntity(ParkingDto parkingDto);
}
