package ru.otus.spring.exception;

public class GenreNotFoundException extends Exception {
    public GenreNotFoundException(String message) {
        super(message);
    }
}
