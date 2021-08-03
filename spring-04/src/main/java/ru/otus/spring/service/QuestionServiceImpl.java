package ru.otus.spring.service;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.FileLoadingException;
import ru.otus.spring.exception.QuestionsLoadingException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    @Autowired
    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }
    public List<Question> findAll() throws QuestionsLoadingException, FileLoadingException {
        return dao.findAll();
    }
}
