package ru.otus.spring.exception;

public class BookCommentNotFoundException extends Exception  {
    public BookCommentNotFoundException(String message) {
        super(message);
    }
}
