package com.example.parkingticketapp.repository.interfaces;

import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.model.User;

import java.util.Optional;

public interface UserRepository {
    User findById(Long id);
    Optional<User> save(User user);
    User deleteById(Long id);
    User updateById(Long id, User user);
    User findByPersonalKey(String personalKey);
}
