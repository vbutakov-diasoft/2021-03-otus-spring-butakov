package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    public Question getByNumber(int number) {
        return dao.findByNumber(number);
    }
    public int getCountQuestion() {
        return dao.getCountQuestion();
    }
}
