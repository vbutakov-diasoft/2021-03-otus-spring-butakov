package ru.otus.spring.exception;

public class GenreAlreadyExistsException extends Exception {
    public GenreAlreadyExistsException(String message) {
        super(message);
    }
}
