package com.example.userservice;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public PasswordRuleValidator passwordRuleValidator() {
        return new PasswordRuleValidator();
    }
}
