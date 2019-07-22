package com.example.userservice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/user")
    List<UserData> all() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    UserData oneUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/user")
    ResponseEntity<Object> newUser(@RequestBody UserData userData) {
        HttpHeaders head = new HttpHeaders();
        String id = (userRepository.save(userData).getId()).toString();
        head.set("id", id);
        return ResponseEntity.ok()
                .headers(head)
                .body("");
    }

    @PutMapping("/user/{id}")
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

    @DeleteMapping("/user/{id}")
    void deleteEmployee(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
