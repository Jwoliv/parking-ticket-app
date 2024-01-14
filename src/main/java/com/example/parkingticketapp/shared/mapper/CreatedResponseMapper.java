package com.example.parkingticketapp.shared.mapper;

import com.example.parkingticketapp.mapper.AddressMapper;
import com.example.parkingticketapp.mapper.ParkingMapper;
import com.example.parkingticketapp.shared.dto.ParkingDto;
import com.example.parkingticketapp.shared.response.CreatedResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = {ParkingMapper.class, AddressMapper.class})
public interface CreatedResponseMapper {
    @Mapping(target = "createdElement", source = "parking")
    @Mapping(target = "timeCreated", expression = "java(getCurrentTime())")
    CreatedResponse<ParkingDto> toResponse(ParkingDto parking);

    default LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
