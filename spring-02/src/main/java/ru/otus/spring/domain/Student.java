package ru.otus.spring.domain;

import org.springframework.stereotype.Component;

@Component
public class Student {

    private final String name;

    public Student() {
        this.name = "";
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
