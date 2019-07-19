package com.example.userservice;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    List<UserData> all() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    UserData oneUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    boolean changePassword(@RequestBody @Valid UserData userData, @PathVariable Long id) {
        return true; //TODO:: add a service method call to do password validation
    }
}
