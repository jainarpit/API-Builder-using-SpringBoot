package com.example.userservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ApplicationRunner.class)
public class SpringBootJPAIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void givenUserRepositoryWhenSaveAndRetrieveEntityThenStatusShouldBeOK() {
        UserData userData = userRepository.save(new UserData("username", "oldPassword", "newPassword"));
        Optional<UserData> foundEntity = userRepository.findById(userData.getId());
        assertNotNull(foundEntity);
        assertEquals(userData.getId(), foundEntity.get().getId());
    }
}
