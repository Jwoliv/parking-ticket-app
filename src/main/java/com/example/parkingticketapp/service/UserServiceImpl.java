package com.example.parkingticketapp.service;

import com.example.parkingticketapp.model.User;
import com.example.parkingticketapp.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Optional<User> findByPersonalKey(String personalKey) {
        return Optional.empty();
    }
}
