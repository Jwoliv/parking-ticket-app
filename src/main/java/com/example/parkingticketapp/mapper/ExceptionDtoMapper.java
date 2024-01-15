package com.example.parkingticketapp.mapper;

import com.example.parkingticketapp.exception.CustomException;
import com.example.parkingticketapp.shared.dto.CustomExceptionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ParkingMapper.class, AddressMapper.class})
public interface ExceptionDtoMapper {
    CustomExceptionDto exceptionToDto(CustomException ex);
}
