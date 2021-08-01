package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppSettings;
import ru.otus.spring.domain.Question;

@Service
public class QuestionsProcessingServiceImpl implements QuestionsProcessingService{

    private final InputOutputService ioService;

    @Autowired
    public QuestionsProcessingServiceImpl(InputOutputService ioService) {
        this.ioService = ioService;
    }

    public boolean askQuestion(Question question){
        ioService.printLocalOut("exam.question");
        ioService.printOut(question.getQuestion());

        for (int j = 0; j < question.getPossibleAnswer().length; j++) {
            ioService.printOut(String.valueOf(j+1) + " - " + question.getPossibleAnswer()[j]);
        }

        int answer = ioService.readInt();
        return (answer == question.getRightAnswerNumber());
    }
}
