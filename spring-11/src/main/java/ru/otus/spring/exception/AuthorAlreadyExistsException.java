package ru.otus.spring.exception;

public class AuthorAlreadyExistsException extends Exception{
    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
