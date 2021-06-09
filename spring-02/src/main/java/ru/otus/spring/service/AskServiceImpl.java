package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;

@Service
public class AskServiceImpl implements AskService{

    private final InputOutputService ioService;

    @Autowired
    public AskServiceImpl (InputOutputService ioService) {
        this.ioService = ioService;
    }

    public Student askStudentName () {
        ioService.printOut("Student's Name:");
        return new Student(ioService.readString());
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
