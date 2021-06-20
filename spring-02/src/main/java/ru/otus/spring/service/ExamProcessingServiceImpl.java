package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class ExamProcessingServiceImpl implements ExamProcessingService {

    private final InputOutputService ioService;

    public ExamProcessingServiceImpl(InputOutputService ioService) {
        this.ioService = ioService;
    }

    public void printTestResult(String studentName, int countRightAnswer) {
        ioService.printOut("Student's Name:");
        ioService.printOut(studentName);
        ioService.printOut("Quantity of the correct answers:");
        ioService.printOut(String.valueOf(countRightAnswer));
        if (countRightAnswer>=3)
            ioService.printOut("exam passed");
        else
            ioService.printOut("exam failed");
    }
}
