package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppSettings;

@Service
public class ExamProcessingServiceImpl implements ExamProcessingService {

    private final InputOutputService ioService;
    private final MessageSource messageSource;

    @Autowired
    private AppSettings settings;

    private static final int COUNT_OF_RIGHT_ANSERT_TO_PASS = 3;

    public ExamProcessingServiceImpl(InputOutputService ioService, MessageSource messageSource) {
        this.ioService = ioService;
        this.messageSource = messageSource;
    }

    public void printTestResult(String studentName, int countRightAnswer) {
        ioService.printOut(messageSource.getMessage("exam.name", null, settings.locale));
        ioService.printOut(studentName);
        ioService.printOut(messageSource.getMessage("exam.result", null, settings.locale));
        ioService.printOut(String.valueOf(countRightAnswer));
        if (countRightAnswer >= COUNT_OF_RIGHT_ANSERT_TO_PASS)
            ioService.printOut(messageSource.getMessage("exam.passed", null, settings.locale));
        else
            ioService.printOut(messageSource.getMessage("exam.failed", null, settings.locale));
    }
}
