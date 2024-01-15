package com.example.parkingticketapp.service.interfaces;

import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.model.User;
import com.example.parkingticketapp.shared.dto.UserDto;
import com.example.parkingticketapp.shared.response.ActionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findByPersonalKey(String personalKey);
    Optional<User> findById(Long id);
    ResponseEntity<ActionResponse<UserDto>> save(UserDto user);
    void update(UserDto user);
    ResponseEntity<ActionResponse<UserDto>> updateWithResponse(UserDto user);
    User updateUserFields(User user, Ticket ticket);
}
