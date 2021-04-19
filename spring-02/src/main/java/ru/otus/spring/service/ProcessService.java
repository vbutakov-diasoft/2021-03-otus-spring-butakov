package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;

public interface ProcessService {

    Student askStudentName();
    boolean askQuestion(Question question, int number);
    void printResult(String name, int rightAnswer) ;
}
