package com.example.parkingticketapp.mapper;

import com.example.parkingticketapp.model.Address;
import com.example.parkingticketapp.shared.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ParkingMapper.class})
public interface AddressMapper {
    AddressDto entityAddressToDto(Address address);
    @Mapping(target = "parking", ignore = true)
    Address dtoAddressToEntity(AddressDto addressDto);
}
