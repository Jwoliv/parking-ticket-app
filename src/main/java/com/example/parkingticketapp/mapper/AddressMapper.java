package com.example.parkingticketapp.mapper;

import com.example.parkingticketapp.model.Address;
import com.example.parkingticketapp.shared.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    AddressDto entityAddressToDto(Address address);
    Address dtoAddressToEntity(AddressDto addressDto);
}
