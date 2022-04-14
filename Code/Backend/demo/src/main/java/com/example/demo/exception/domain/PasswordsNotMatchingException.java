package com.example.demo.exception.domain;

public class PasswordsNotMatchingException extends Exception{

    public PasswordsNotMatchingException(String message)
    {
        super(message);
    }
}
