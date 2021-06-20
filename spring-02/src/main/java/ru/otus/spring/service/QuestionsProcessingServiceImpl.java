package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;

@Service
public class QuestionsProcessingServiceImpl implements QuestionsProcessingService{

    private final InputOutputService ioService;

    public QuestionsProcessingServiceImpl(InputOutputService ioService) {
        this.ioService = ioService;
    }

    public boolean askQuestion(Question question, int number){
        ioService.printOut("Question №: {"+question.getQuestionNumber()+"}");
        ioService.printOut(question.getQuestion());

        for (int j = 0; j < question.getPossibleAnswer().length; j++) {
            ioService.printOut(String.valueOf(j+1) + " - " + question.getPossibleAnswer()[j]);
        }

        int answer = ioService.readInt();
        return (answer == question.getRightAnswerNumber());
    }
}
