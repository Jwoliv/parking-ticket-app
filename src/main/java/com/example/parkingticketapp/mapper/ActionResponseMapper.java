package com.example.parkingticketapp.mapper;

import com.example.parkingticketapp.shared.dto.ParkingDto;
import com.example.parkingticketapp.shared.dto.UserDto;
import com.example.parkingticketapp.shared.enums.CrudAction;
import com.example.parkingticketapp.shared.response.ActionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = {ParkingMapper.class, AddressMapper.class})
public interface ActionResponseMapper {
    @Mapping(target = "element", source = "parking")
    @Mapping(target = "action", source = "crudAction")
    @Mapping(target = "timeAction", expression = "java(getCurrentTime())")
    @Mapping(target = "isSucceed", expression = "java(true)")
    ActionResponse<ParkingDto> toResponse(ParkingDto parking, CrudAction crudAction);

    @Mapping(target = "element", source = "parking")
    @Mapping(target = "action", source = "crudAction")
    @Mapping(target = "timeAction", expression = "java(getCurrentTime())")
    @Mapping(target = "isSucceed", expression = "java(true)")
    ActionResponse<UserDto> toResponse(UserDto parking, CrudAction crudAction);

    default LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
