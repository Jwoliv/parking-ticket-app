package com.example.parkingticketapp.mapper;

import com.example.parkingticketapp.model.User;
import com.example.parkingticketapp.shared.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface UserMapper {
    @Mapping(target = "tickets", ignore = true)
    UserDto entityUserToDto(User user);
    User dtoUserToEntity(UserDto userDto);
}
