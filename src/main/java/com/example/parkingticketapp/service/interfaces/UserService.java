package com.example.parkingticketapp.service.interfaces;

import com.example.parkingticketapp.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findByPersonalKey(String personalKey);
}
