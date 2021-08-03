package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppSettings;
import ru.otus.spring.domain.Student;

@Service
public class StudentServiceImpl implements StudentService{

    private final InputOutputLocalizationService ioService;

    @Autowired
    public StudentServiceImpl(InputOutputLocalizationService ioService) {
        this.ioService = ioService;
    }

    public Student askStudentName () {
        ioService.printLocalOut("exam.name");
        return new Student(ioService.readString());
    }

}
