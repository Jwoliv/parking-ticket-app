package com.example.parkingticketapp.infrastructure.rest;

import com.example.parkingticketapp.service.interfaces.UserService;
import com.example.parkingticketapp.shared.dto.UserDto;
import com.example.parkingticketapp.shared.response.ActionResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Setter(onMethod = @__(@Autowired))
    private UserService userService;

    @PostMapping
    private ResponseEntity<ActionResponse<UserDto>> saveNewUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping
    private ResponseEntity<ActionResponse<UserDto>> updateUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.updateWithResponse(user));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ActionResponse<UserDto>> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }
}
