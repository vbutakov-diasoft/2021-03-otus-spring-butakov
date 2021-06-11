package ru.otus.spring.service;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.util.LinkedList;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;
    private final InputOutputService ioService;

    @Autowired
    public QuestionServiceImpl(QuestionDao dao,InputOutputService ioService) {
        this.dao = dao;
        this.ioService = ioService;
    }
    public LinkedList<Question> findAll() throws IOException, CsvValidationException {
        return dao.findAll();
    }

    public boolean askQuestion( Question question, int number){
        ioService.printOut("Question №: {"+question.getQuestionNumber()+"}");
        ioService.printOut(question.getQuestion());

        for (int j = 0; j < question.getPossibleAnswer().length; j++) {
            ioService.printOut(String.valueOf(j+1) + " - " + question.getPossibleAnswer()[j]);
        }

        int answer = ioService.readInt();
        return (answer == question.getRightAnswerNumber());
    }
}
