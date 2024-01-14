package com.example.parkingticketapp.mapper;

import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.shared.dto.ParkingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParkingMapper {
    ParkingMapper INSTANCE = Mappers.getMapper(ParkingMapper.class);
    @Mapping(target = "addressDto", source = "address")
    ParkingDto entityParkingToDto(Parking parking);
    @Mapping(target = "address", source = "addressDto")
    Parking dtoParkingToEntity(ParkingDto parkingDto);
}
