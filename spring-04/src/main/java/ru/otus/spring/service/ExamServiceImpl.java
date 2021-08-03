package ru.otus.spring.service;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;
import ru.otus.spring.exception.FileLoadingException;
import ru.otus.spring.exception.QuestionsLoadingException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    private final QuestionService questionService;
    private final QuestionsProcessingService questionsProcessingService;
    private final StudentService studentService;
    private final ExamProcessingService examProcessingService;

    @Autowired
    public ExamServiceImpl(QuestionService questionService, QuestionsProcessingService questionsProcessingService, StudentService studentService, ExamProcessingService examProcessingService) {
        this.questionService = questionService;
        this.questionsProcessingService = questionsProcessingService;
        this.studentService = studentService;
        this.examProcessingService = examProcessingService;
    }

    public void testing() throws QuestionsLoadingException, FileLoadingException {
        Student student = studentService.askStudentName();
        List<Question> questions = questionService.findAll();
        int res = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questionsProcessingService.askQuestion(questions.get(i))) {
                res++;
            }
        }

        examProcessingService.printTestResult(student.getName(), res);
    }
}