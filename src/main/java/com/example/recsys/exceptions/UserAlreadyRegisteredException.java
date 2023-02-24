package com.example.recsys.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String nickname) {
        super("User with nickname: " + nickname + " already exists!");
    }
}
