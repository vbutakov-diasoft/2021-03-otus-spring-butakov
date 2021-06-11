package ru.otus.spring.service;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public interface ExamService {

    void testing() throws IOException, CsvValidationException;
}
