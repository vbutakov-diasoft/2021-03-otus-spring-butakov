package ru.otus.spring.service;

import ru.otus.spring.exception.QuestionsLoadingException;
import ru.otus.spring.exception.SourceLoadingException;

public interface ShellService {

    void testStart() throws QuestionsLoadingException, SourceLoadingException;
}
