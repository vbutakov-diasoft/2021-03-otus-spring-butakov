package ru.otus.spring.service;

import ru.otus.spring.domain.Student;

public interface StudentService {
    Student askStudentName();
    void printTestResult(String name, int rightAnswer) ;
}
