package com.example.parkingticketapp.mapper;

import com.example.parkingticketapp.model.Address;
import com.example.parkingticketapp.shared.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto entityAddressToDto(Address address);
    Address dtoAddressToEntity(AddressDto addressDto);
}
