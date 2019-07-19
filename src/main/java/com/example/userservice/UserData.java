package com.example.userservice;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class UserData {

    private @Id
    @GeneratedValue
    Long id;
    private String username;
    private String password;

    UserData() {}

    UserData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
