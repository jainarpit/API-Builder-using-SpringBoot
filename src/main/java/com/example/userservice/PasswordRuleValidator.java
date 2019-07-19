package com.example.userservice;

import org.passay.*;


public class PasswordRuleValidator {
    PasswordValidator passwordValidator = new PasswordValidator(
            new LengthRule(5, 20),
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            new CharacterRule(EnglishCharacterData.Digit, 1),
            new CharacterRule(EnglishCharacterData.Special, 1),
            new RepeatCharacterRegexRule(5)
    );

    public boolean isValid(String password) {
        return passwordValidator.validate(new PasswordData(password)).isValid();
    }
}