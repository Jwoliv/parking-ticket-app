package com.example.parkingticketapp.shared.mapper;

import com.example.parkingticketapp.mapper.AddressMapper;
import com.example.parkingticketapp.mapper.ParkingMapper;
import com.example.parkingticketapp.shared.dto.ParkingDto;
import com.example.parkingticketapp.shared.response.DeletedResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = {ParkingMapper.class, AddressMapper.class})
public interface DeletedResponseMapper {
    @Mapping(target = "deletedElement", source = "parking")
    @Mapping(target = "timeDeleted", expression = "java(getCurrentTime())")
    @Mapping(target = "isSucceed", expression = "java(true)")
    DeletedResponse<ParkingDto> toResponse(ParkingDto parking);

    default LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
