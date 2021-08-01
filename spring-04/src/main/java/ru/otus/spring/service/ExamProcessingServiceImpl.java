package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppSettings;

@Service
public class ExamProcessingServiceImpl implements ExamProcessingService {

    private final InputOutputService ioService;

    private static final int COUNT_OF_RIGHT_ANSERT_TO_PASS = 3;

    public ExamProcessingServiceImpl(InputOutputService ioService) {
        this.ioService = ioService;
    }

    public void printTestResult(String studentName, int countRightAnswer) {
        ioService.printLocalOut("exam.name");
        ioService.printOut(studentName);
        ioService.printLocalOut("exam.result");
        ioService.printOut(String.valueOf(countRightAnswer));
        if (countRightAnswer >= COUNT_OF_RIGHT_ANSERT_TO_PASS)
            ioService.printLocalOut("exam.passed");
        else
            ioService.printLocalOut("exam.failed");
    }
}
