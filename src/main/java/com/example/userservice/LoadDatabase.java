package com.example.userservice;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            userRepository.save(new UserData("Bilbo Baggins", "burglar",null));
            userRepository.save(new UserData("Frodo Baggins", "thief",null));
        };
    }
}
