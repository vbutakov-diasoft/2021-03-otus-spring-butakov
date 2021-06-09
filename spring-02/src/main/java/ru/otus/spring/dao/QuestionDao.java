package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.util.LinkedList;

public interface QuestionDao {

    LinkedList<Question> findAll();
}
