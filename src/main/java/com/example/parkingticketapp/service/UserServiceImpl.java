package com.example.parkingticketapp.service;

import com.example.parkingticketapp.model.User;
import com.example.parkingticketapp.repository.interfaces.UserRepository;
import com.example.parkingticketapp.service.interfaces.UserService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Setter(onMethod = @__(@Autowired))
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(userRepository.findById(id));
    }

    @Override
    public Optional<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.of(userRepository.updateById(user.getId(), user));
    }

    @Override
    public Optional<User> findByPersonalKey(String personalKey) {
        return Optional.of(userRepository.findByPersonalKey(personalKey));
    }
}
