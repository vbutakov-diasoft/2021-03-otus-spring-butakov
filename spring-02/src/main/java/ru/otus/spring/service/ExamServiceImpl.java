package ru.otus.spring.service;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.Student;

import java.io.IOException;
import java.util.LinkedList;

@Service
public class ExamServiceImpl implements ExamService {

    private final QuestionService questionService;
    private final StudentService studentService;

    @Autowired
    public ExamServiceImpl(QuestionService questionService, StudentService studentService) {
        this.questionService = questionService;
        this.studentService = studentService;
    }

    public void testing() throws IOException, CsvValidationException {
        LinkedList<Question> questions;

        Student student = studentService.askStudentName();
        questions = questionService.findAll();
        int res = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questionService.askQuestion(questions.get(i), i + 1)) {
                res++;
            }
        }
        studentService.printTestResult(student.getName(), res);
    }
}
