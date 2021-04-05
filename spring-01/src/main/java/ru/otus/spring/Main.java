package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionService;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService serviceQuestion = context.getBean(QuestionService.class);
        for (int i = 0; i < serviceQuestion.getCountQuestion(); i++){
            System.out.println("----------------------------");
            Question someQuestion = serviceQuestion.getByNumber(i);
            System.out.println("Вопрос №: " + (i+1));
            System.out.println(someQuestion.getQuestion());
            for (int j = 0; j < someQuestion.getPossibleAnswer().length; j++) {
                System.out.println(String.valueOf(j+1) + " - " + someQuestion.getPossibleAnswer()[j]);
            }
            System.out.print("Ваш вариант ответа ");
            int answer = Integer.parseInt(reader.readLine());
            if (answer == someQuestion.getRightAnswerNumber())
                System.out.println("Верно ");
            else
                System.out.println("Неверно, правильный вариант "+someQuestion.getRightAnswerNumber());

            }
        context.close();

    }
}
