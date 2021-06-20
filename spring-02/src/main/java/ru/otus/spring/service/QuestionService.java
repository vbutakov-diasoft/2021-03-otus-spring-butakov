package ru.otus.spring.service;

import com.opencsv.exceptions.CsvValidationException;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionsLoadingException;

import java.io.IOException;
import java.util.List;

interface QuestionService {

    List<Question> findAll() throws IOException, CsvValidationException, QuestionsLoadingException;
}
