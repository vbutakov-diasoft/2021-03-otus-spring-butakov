package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.exception.SourceLoadingException;
import ru.otus.spring.exception.QuestionsLoadingException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws QuestionsLoadingException, SourceLoadingException {
        ApplicationContext context = SpringApplication.run(Main.class, args);
    }
}