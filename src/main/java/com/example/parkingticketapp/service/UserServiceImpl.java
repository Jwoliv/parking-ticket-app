package com.example.parkingticketapp.service;

import com.example.parkingticketapp.mapper.ActionResponseMapper;
import com.example.parkingticketapp.mapper.UserMapper;
import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.model.User;
import com.example.parkingticketapp.repository.interfaces.UserRepository;
import com.example.parkingticketapp.service.interfaces.UserService;
import com.example.parkingticketapp.shared.dto.UserDto;
import com.example.parkingticketapp.shared.enums.CrudAction;
import com.example.parkingticketapp.shared.response.ActionResponse;
import com.example.parkingticketapp.utils.UniqueKeyGenerator;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Setter(onMethod = @__(@Autowired))
    private UserRepository userRepository;
    @Setter(onMethod = @__(@Autowired))
    private UserMapper userMapper;
    @Setter(onMethod = @__(@Autowired))
    private ActionResponseMapper actionResponseMapper;

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(userRepository.findById(id));
    }

    @Override
    public ResponseEntity<ActionResponse<UserDto>> save(UserDto user) {
        user.setPersonalKey(UniqueKeyGenerator.generateTicketKey());
        User savedUser = userRepository.save(userMapper.dtoUserToEntity(user)).orElseThrow();
        UserDto savedUserDto = userMapper.entityUserToDto(savedUser);
        ActionResponse<UserDto> response = actionResponseMapper.toResponse(savedUserDto, CrudAction.CREATE);
        return ResponseEntity.ok(response);
    }

    @Override
    public void update(UserDto user) {
        userRepository.updateById(user.getId(), userMapper.dtoUserToEntity(user));
    }

    @Override
    public ResponseEntity<ActionResponse<UserDto>> updateWithResponse(UserDto user) {
       User updatedUser = userRepository.updateById(user.getId(), userMapper.dtoUserToEntity(user));
       UserDto updatedUserDto = userMapper.entityUserToDto(updatedUser);
       ActionResponse<UserDto> response = actionResponseMapper.toResponse(updatedUserDto, CrudAction.UPDATE);
       return ResponseEntity.ok(response);
    }

    @Override
    public User updateUserFields(User user, Ticket ticket) {
        return userRepository.updateUserFields(user, ticket);
    }

    @Override
    public Optional<User> findByPersonalKey(String personalKey) {
        return Optional.of(userRepository.findByPersonalKey(personalKey));
    }
}
