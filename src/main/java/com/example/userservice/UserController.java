package com.example.userservice;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final UserPasswordService userPasswordService;

    UserController(UserRepository userRepository, UserPasswordService userPasswordService) {
        this.userRepository = userRepository;
        this.userPasswordService = userPasswordService;
    }

    @GetMapping("/users")
    List<UserData> all() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    UserData oneUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    boolean updatePassword(@RequestBody @Valid UserData userData, @PathVariable Long id) {
        String oldPassword = userData.getCurrentPassword();
        String newPassword = userData.getNewPassword();
        Optional<UserData> user = userRepository.findById(id);
        boolean isUserPresent = userRepository.findById(id).isPresent();
        if (!isUserPresent) {
            userData.setId(id);
            userData.setNewPassword(null);
            userRepository.save(userData);
            return true;
        }
        String oldPasswordInDb = user.get().getCurrentPassword();
        if (oldPassword.equals(oldPasswordInDb)) {
            return userPasswordService.changePassword(oldPassword, newPassword, id);
        } else
            return false;
    }
}
