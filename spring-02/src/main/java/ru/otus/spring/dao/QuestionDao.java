package ru.otus.spring.dao;

import com.opencsv.exceptions.CsvValidationException;
import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.util.LinkedList;

public interface QuestionDao {

    LinkedList<Question> findAll() throws IOException, CsvValidationException;
}
