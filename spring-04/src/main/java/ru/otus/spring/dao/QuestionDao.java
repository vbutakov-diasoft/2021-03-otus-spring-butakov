package ru.otus.spring.dao;

import com.opencsv.exceptions.CsvValidationException;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionsLoadingException;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

public interface QuestionDao {

    List<Question> findAll() throws QuestionsLoadingException;
}
