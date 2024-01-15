package com.example.parkingticketapp.mapper;

import com.example.parkingticketapp.model.User;
import com.example.parkingticketapp.shared.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, ParkingMapper.class, TicketMapper.class})
public interface UserMapper {
    UserDto entityUserToDto(User user);
    User dtoUserToEntity(UserDto userDto);
}
