package com.example.recsys.exceptions;

public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException() {
        super("Username or password is incorrect");
    }
}
