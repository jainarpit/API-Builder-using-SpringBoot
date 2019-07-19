package com.example.userservice;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Cannot find this User");
    }
}
