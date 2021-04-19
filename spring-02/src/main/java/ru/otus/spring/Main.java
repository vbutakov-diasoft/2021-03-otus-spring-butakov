package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.ExamService;
import java.io.*;

@PropertySource("classpath:application.properties")
@ComponentScan
public class Main {

    public static void main(String[] args)  {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        ExamService exam = context.getBean(ExamService.class);
        exam.testing();
    }
}