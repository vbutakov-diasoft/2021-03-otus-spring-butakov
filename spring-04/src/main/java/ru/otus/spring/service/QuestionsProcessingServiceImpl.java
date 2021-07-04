package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppSettings;
import ru.otus.spring.domain.Question;

@Service
public class QuestionsProcessingServiceImpl implements QuestionsProcessingService{

    private final InputOutputService ioService;
    private final MessageSource messageSource;

    @Autowired
    private AppSettings settings;

    public QuestionsProcessingServiceImpl(InputOutputService ioService, MessageSource messageSource) {
        this.ioService = ioService;
        this.messageSource = messageSource;
    }

    public boolean askQuestion(Question question){
        ioService.printOut(messageSource.getMessage("exam.question",new String [] {String.valueOf(question.getQuestionNumber())}, settings.getLocale()));
        ioService.printOut(question.getQuestion());

        for (int j = 0; j < question.getPossibleAnswer().length; j++) {
            ioService.printOut(String.valueOf(j+1) + " - " + question.getPossibleAnswer()[j]);
        }

        int answer = ioService.readInt();
        return (answer == question.getRightAnswerNumber());
    }
}
