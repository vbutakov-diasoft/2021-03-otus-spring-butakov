package ru.otus.spring.exception;

public class QuestionsLoadingException extends Exception{
    public QuestionsLoadingException(String message) {
        super(message);
    }
}
