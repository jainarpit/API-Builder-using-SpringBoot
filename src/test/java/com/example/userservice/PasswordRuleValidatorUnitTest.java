package com.example.userservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordRuleValidatorUnitTest {

    PasswordRuleValidator passwordRuleValidator = new PasswordRuleValidator();
    @Test
    void verifyPasswordIsValid() {
        String newPassword = "abcd1234EFGH@$%aasd";
        boolean actual = passwordRuleValidator.isValid(newPassword);
                assertEquals(true,actual);
    }

    @Test
    void verifyPasswordIsInvalidIfLengthCriteriaDoesNotMeet(){
        String password = "@bcDEFG67";
        boolean actual = passwordRuleValidator.isValid(password);
        assertEquals(false,actual);
    }

    @Test
    void verifyPasswordIsInvalidIfUpperCaseCriteriaDoesNotMeet(){
        String password = "@bcdefghihjklmnop67";
        boolean actual = passwordRuleValidator.isValid(password);
        assertEquals(false,actual);
    }

    @Test
    void verifyPasswordIsInvalidIfLowerCaseCriteriaDoesNotMeet(){
        String password = "@BCDEFGHIJKLMNOP67";
        boolean actual = passwordRuleValidator.isValid(password);
        assertEquals(false,actual);
    }

    @Test
    void verifyPasswordIsInvalidIfSpecialCharacterCriteriaDoesNotMeet(){
        String password = "AbcDEFGHIJKLMNOP67";
        boolean actual = passwordRuleValidator.isValid(password);
        assertEquals(false,actual);
    }

    @Test
    void verifyPasswordIsInvalidIfNumberIsNotPresent(){
        String password = "AbcDEFGHIJKLMNOP@@";
        boolean actual = passwordRuleValidator.isValid(password);
        assertEquals(false,actual);
    }
}
