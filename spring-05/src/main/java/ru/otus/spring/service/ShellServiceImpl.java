package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.QuestionsLoadingException;
import ru.otus.spring.exception.SourceLoadingException;

@Service
public class ShellServiceImpl implements ShellService {
    private final ExamService exam;

    public ShellServiceImpl(ExamService exam) {
        this.exam = exam;
    }

    @Override
    public void testStart() throws QuestionsLoadingException, SourceLoadingException {
        exam.testing();
    }
}
