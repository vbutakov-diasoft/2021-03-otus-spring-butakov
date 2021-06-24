package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class ExamProcessingServiceImpl implements ExamProcessingService {

    private final InputOutputService ioService;
    private static final int COUNT_OF_RIGHT_ANSERT_TO_PASS = 3;

    public ExamProcessingServiceImpl(InputOutputService ioService) {
        this.ioService = ioService;
    }

    public void printTestResult(String studentName, int countRightAnswer) {
        ioService.printOut("Student's Name:");
        ioService.printOut(studentName);
        ioService.printOut("Quantity of the correct answers:");
        ioService.printOut(String.valueOf(countRightAnswer));
        if (countRightAnswer>=COUNT_OF_RIGHT_ANSERT_TO_PASS)
            ioService.printOut("exam passed");
        else
            ioService.printOut("exam failed");
    }
}
