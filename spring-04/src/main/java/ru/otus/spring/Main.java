package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.exception.QuestionsLoadingException;
import ru.otus.spring.service.ExamService;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws QuestionsLoadingException {
        ApplicationContext context = SpringApplication.run(Main.class, args);

        ExamService exam = context.getBean(ExamService.class);
        exam.testing();
    }
}
