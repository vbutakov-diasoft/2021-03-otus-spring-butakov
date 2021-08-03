package ru.otus.spring.service;

import com.opencsv.exceptions.CsvValidationException;
import ru.otus.spring.exception.FileLoadingException;
import ru.otus.spring.exception.QuestionsLoadingException;

import java.io.IOException;

public interface ExamService {

    void testing() throws QuestionsLoadingException, FileLoadingException;
}
