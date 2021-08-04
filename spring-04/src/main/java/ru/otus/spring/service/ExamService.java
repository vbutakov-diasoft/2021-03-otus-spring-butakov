package ru.otus.spring.service;

import ru.otus.spring.exception.SourceLoadingException;
import ru.otus.spring.exception.QuestionsLoadingException;

public interface ExamService {

    void testing() throws QuestionsLoadingException, SourceLoadingException;
}
