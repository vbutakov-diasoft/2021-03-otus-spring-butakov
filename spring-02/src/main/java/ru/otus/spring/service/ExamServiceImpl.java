package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;

import java.util.LinkedList;

@Service
public class ExamServiceImpl implements ExamService {

    private final QuestionService questionService;
    private final AskService askService;
    private final ResultService resultService;
    private LinkedList<Question> questions;

    @Autowired
    public ExamServiceImpl(QuestionService questionService, AskService askService, ResultService resultService) {
        this.questionService = questionService;
        this.askService = askService;
        this.resultService = resultService;
    }

    public void testing() {
        Student student = new Student();
        student = askService.askStudentName();
        this.questions = questionService.findAll();
        int res = 0;
        for (int i = 0; i < this.questions.size(); i++) {
            if (askService.askQuestion(this.questions.get(i), i + 1)) {
                res++;
            }
        }
        resultService.printResult(student.getName(), res);
    }
}
