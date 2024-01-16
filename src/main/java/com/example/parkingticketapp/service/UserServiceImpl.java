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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private static final Float TEN_PERCENTAGE = 0.1F;

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
    public ActionResponse<UserDto> save(UserDto user) {
        setUpFieldsBeforeFirstSave(user);
        User savedUser = userRepository.save(userMapper.dtoUserToEntity(user)).orElseThrow();
        UserDto savedUserDto = userMapper.entityUserToDto(savedUser);
        return actionResponseMapper.toResponse(savedUserDto, CrudAction.CREATE);
    }

    @Override
    public void update(UserDto user) {
        userRepository.updateById(user.getId(), userMapper.dtoUserToEntity(user));
    }

    @Override
    public ActionResponse<UserDto> updateWithResponse(UserDto user) {
       User updatedUser = userRepository.updateById(user.getId(), userMapper.dtoUserToEntity(user));
       UserDto updatedUserDto = userMapper.entityUserToDto(updatedUser);
       return actionResponseMapper.toResponse(updatedUserDto, CrudAction.UPDATE);
    }

    @Override
    public User updateUserFields(User user, Ticket ticket) {
        return setUserFields(user, ticket);
    }

    @Override
    public ActionResponse<UserDto> deleteById(Long id) {
        User deletedUser = userRepository.deleteById(id);
        UserDto deletedUserDto = userMapper.entityUserToDto(deletedUser);
        return actionResponseMapper.toResponse(deletedUserDto, CrudAction.DELETE);
    }

    @Override
    public Optional<User> findByPersonalKey(String personalKey) {
        return Optional.of(userRepository.findByPersonalKey(personalKey));
    }

    public User setUserFields(User user, Ticket ticket) {
        user.setChange(generateChange(user, ticket));
        user.setBonusMoney(generateBonusMoney(user, ticket));
        user.setTickets(addTicketToUser(user, ticket));
        return user;
    }

    private Float generateChange(User user, Ticket ticket) {
        Float ticketChange = ticket.getChange();
        return user.getChange() + ticketChange;
    }

    private Float generateBonusMoney(User user, Ticket ticket) {
        return user.getBonusMoney() + (ticket.getAmountBonusMoney() * TEN_PERCENTAGE);
    }

    private List<Ticket> addTicketToUser(User user, Ticket ticket) {
        List<Ticket> updatedTickets = user.getTickets();
        updatedTickets.add(ticket);
        return updatedTickets;
    }

    private void setUpFieldsBeforeFirstSave(UserDto user) {
        user.setPersonalKey(UniqueKeyGenerator.generateTicketKey());
        user.setChange(0.0F);
        user.setBonusMoney(0.0F);
    }
}
