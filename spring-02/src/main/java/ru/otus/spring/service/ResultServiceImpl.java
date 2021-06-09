package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService{

    private final InputOutputService ioService;

    @Autowired
    public ResultServiceImpl (InputOutputService ioService) {
        this.ioService = ioService;
    }

    public void printResult(String name, int rightAnswer) {
        ioService.printOut("Student's Name:");
        ioService.printOut(name);
        ioService.printOut("Quantity of the correct answers:");
        ioService.printOut(String.valueOf(rightAnswer));
        if (rightAnswer>=3)
            ioService.printOut("exam passed");
        else
            ioService.printOut("exam failed");
    }
}
