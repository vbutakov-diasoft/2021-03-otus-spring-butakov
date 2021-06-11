package ru.otus.spring.service;

import com.opencsv.exceptions.CsvValidationException;
import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.util.LinkedList;

interface QuestionService {

    LinkedList<Question> findAll() throws IOException, CsvValidationException;
    boolean askQuestion(Question question, int number);
}
