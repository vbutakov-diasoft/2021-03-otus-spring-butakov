package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.LinkedList;

public interface QuestionService {

    LinkedList<Question> findAll();
}
