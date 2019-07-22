package com.example.userservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class UserPasswordServiceTest {
    private UserPasswordService userPasswordService;

    @MockBean
    UserRepository mockUserRepository;

    @InjectMocks
    PasswordRuleValidator passwordRuleValidator;

    @BeforeEach
    void setUp() {
        userPasswordService = new UserPasswordService(mockUserRepository, passwordRuleValidator);
    }

    @Test
    void verifyOldAndNewPasswordsAreSimilar() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String oldPassword = "53Rupees@KFcBurger";
        String newPassword = "Burger24MacDonalds";
        Method method = UserPasswordService.class.getDeclaredMethod("isPasswordDifferent", String.class, String.class);
        method.setAccessible(true);
        boolean actual = (boolean) method.invoke(userPasswordService, oldPassword, newPassword);
        assertEquals(true, actual);
    }

    @Test
    void verifyOldAndNewPasswordsAreDifferent() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String oldPassword = "McDonald24Burger";
        String newPassword = "KFcBurger@53Rupees";
        Method method = UserPasswordService.class.getDeclaredMethod("isPasswordDifferent", String.class, String.class);
        method.setAccessible(true);
        boolean actual = (boolean) method.invoke(userPasswordService, oldPassword, newPassword);
        assertEquals(true, actual);
    }

    @Test
    void verifyPasswordCanBeChanged() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String oldPassword = "somtH12";
        String newPassword = "KFcBurger@53Rupees";
        Method method = UserPasswordService.class.getDeclaredMethod("canChangePassword", String.class, String.class);
        method.setAccessible(true);
        boolean actual = (boolean) method.invoke(userPasswordService, oldPassword, newPassword);
        assertEquals(true, actual);
    }

    @Test
    void verifySimilarPasswordsCannotBeChanged() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String oldPassword = "53Rupees@KFcBurger";
        String newPassword = "KFcBurge";
        Method method = UserPasswordService.class.getDeclaredMethod("canChangePassword", String.class, String.class);
        method.setAccessible(true);
        boolean actual = (boolean) method.invoke(userPasswordService, oldPassword, newPassword);
        assertEquals(false, actual);
    }

}
