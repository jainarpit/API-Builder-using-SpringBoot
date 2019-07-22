package com.example.userservice;

import org.passay.*;
public class PasswordRuleValidator {
    PasswordValidator passwordValidator = new PasswordValidator(
            new LengthRule(18, 100),
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            new CharacterRule(EnglishCharacterData.Digit, 1),
            new CharacterRule(EnglishCharacterData.Special, 1),
            new RepeatCharacterRegexRule(5)
    );

    public boolean isValid(String newPassword) {
        return passwordValidator.validate(new PasswordData(newPassword)).isValid();
    }
}
